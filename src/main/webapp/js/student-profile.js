'use strict';

(function () {
    let tutorForGrade;
    $("button#addSubjectBtn").click(function () {
        let template = $('div#subjectTemplate').html();
        $('div#subjectContainer').append('<div class="form-inline">' + template + '</div>');
        setSubjectsForGrade();
    });

    $("select#updateGrade").on('change', getTutorsForGrade);

    function getTutorsForGrade() {
        jQuery.getJSON(`/tutors/grade/${$("select#updateGrade").val()}`,
            undefined, function (data) {
                tutorForGrade = data;
            });
    }

    function setSubjectsForGrade() {
        let selectSubjectElement = $("div#subjectContainer select#updateSubjects");
        for (let subject of Object.keys(tutorForGrade)) {
            let subjectId = subject.split('~')[0];
            let subjectName = subject.split('~')[1];
            selectSubjectElement.append(`<option key="${subject}" value="${subjectId}">${subjectName}</option>`);
        }
        selectSubjectElement.on('change', setClassTypesForSubject);
    }

    function setClassTypesForSubject() {
        let selectSubjectElement = $('div#subjectContainer select#updateSubjects');
        let value = selectSubjectElement.val();
        let key = $(`div#subjectContainer select#updateSubjects option[value=${value}]`).attr('key');
        let selectClassTypeElement = $("div#subjectContainer select#updateClassType");
        for (let classType of Object.keys(tutorForGrade[key])) {
            selectClassTypeElement.append(`<option value="${classType}">${classType}</option>`);
        }

        selectClassTypeElement.on('change', setTutorsForClassType);
    }

    function setTutorsForClassType() {
        let selectSubjectElement = $('div#subjectContainer select#updateSubjects');
        let value = selectSubjectElement.val();
        let firstKey = $(`div#subjectContainer select#updateSubjects option[value=${value}]`).attr('key');
        let secondKey = $('div#subjectContainer select#updateClassType').val();
        let selectTutorElement = $("div#subjectContainer select#updateTutor");
        for (let tutor of tutorForGrade[firstKey][secondKey]) {
            selectTutorElement
                .append(`<option value="${tutor['tutorId']}">${tutor['name']}-Rs ${tutor['subjects'][0]['fee']['amount']}</option>`);
        }

        selectTutorElement.on('change', setFeeForTutors);
    }

    function setFeeForTutors() {
        let selectSubjectElement = $('div#subjectContainer select#updateSubjects');
        let value = selectSubjectElement.val();
        let firstKey = $(`div#subjectContainer select#updateSubjects option[value=${value}]`).attr('key');
        let secondKey = $('div#subjectContainer select#updateClassType').val();
        let tutorId = $('div#subjectContainer select#updateTutor').val();
        let tutor = tutorForGrade[firstKey][secondKey].find((a) => a['tutorId'] == tutorId);

        if (tutor) {
            $('div#subjectContainer input#updateFeeId').val(tutor['subjects'][0]['fee']['feeId']);
            $('div#subjectContainer input#updateFee').val(tutor['subjects'][0]['fee']['amount']);
        } else {
            $('div#subjectContainer input#updateFeeId').val(0);
            $('div#subjectContainer input#updateFee').val(0);
        }
    }
})();