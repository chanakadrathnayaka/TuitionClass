'use strict';

(function () {
    let tutorForGrade;
    $("button#addSubjectBtn").click(function () {
        let templateElement = $('div#subjectTemplate');
        let containerElement = $('div#subjectContainer');
        containerElement.append(`<div subject="${containerElement.children().length + 1}" class="form-inline">${templateElement.html()}</div>`);

        $('div#subjectContainer div:last-child').find('select,input').each((index, element) => {
            let id = $(element).attr('id');
            $(element).attr('id', `${id}_${containerElement.children().length}`);
            $(element).attr('subject', containerElement.children().length);
        });
        setSubjectsForGrade(containerElement.children().length);
    });

    $("select#updateGrade").on('change', getTutorsForGrade);

    function getTutorsForGrade() {
        jQuery.getJSON(`/TuitionClass/tutors/grade/${$("select#updateGrade").val()}`,
            undefined, function (data) {
                tutorForGrade = data;
            });
    }

    function setSubjectsForGrade(rowNumber) {
        let selectSubjectElement = $(`select#updateSubjects_${rowNumber}`);
        for (let subject of Object.keys(tutorForGrade)) {
            let subjectId = subject.split('~')[0];
            let subjectName = subject.split('~')[1];
            selectSubjectElement.append(`<option key="${subject}" value="${subjectId}">${subjectName}</option>`);
        }
        selectSubjectElement.on('change', setClassTypesForSubject);
    }

    function setClassTypesForSubject(e) {
        let rowNumber = $(e.target).attr('subject');
        let selectSubjectElement = $(`select#updateSubjects_${rowNumber}`);
        let value = selectSubjectElement.val();
        let key = $(`select#updateSubjects_${rowNumber} option[value=${value}]`).attr('key');
        let selectClassTypeElement = $(`select#updateClassType_${rowNumber}`);
        for (let classType of Object.keys(tutorForGrade[key])) {
            selectClassTypeElement.append(`<option value="${classType}">${classType}</option>`);
        }

        selectClassTypeElement.on('change', setTutorsForClassType);
    }

    function setTutorsForClassType(e) {
        let rowNumber = $(e.target).attr('subject');
        let selectSubjectElement = $(`select#updateSubjects_${rowNumber}`);
        let value = selectSubjectElement.val();
        let firstKey = $(`select#updateSubjects_${rowNumber} option[value=${value}]`).attr('key');
        let secondKey = $(`select#updateClassType_${rowNumber}`).val();
        let selectTutorElement = $(`select#updateTutor_${rowNumber}`);
        for (let tutor of tutorForGrade[firstKey][secondKey]) {
            selectTutorElement
                .append(`<option value="${tutor['tutorId']}">${tutor['name']}-Rs ${tutor['subjects'][0]['fee']['amount']}</option>`);
        }

        selectTutorElement.on('change', setFeeForTutors);
    }

    function setFeeForTutors(e) {
        let rowNumber = $(e.target).attr('subject');
        let selectSubjectElement = $(`select#updateSubjects_${rowNumber}`);
        let value = selectSubjectElement.val();
        let firstKey = $(`select#updateSubjects_${rowNumber} option[value=${value}]`).attr('key');
        let secondKey = $(`select#updateClassType_${rowNumber}`).val();
        let tutorId = $(`select#updateTutor_${rowNumber}`).val();
        let tutor = tutorForGrade[firstKey][secondKey].find((a) => a['tutorId'] == tutorId);

        if (tutor) {
            $(`input#updateFeeId_${rowNumber}`).val(tutor['subjects'][0]['fee']['feeId']);
            $(`input#updateFee_${rowNumber}`).val(tutor['subjects'][0]['fee']['amount']);
        } else {
            $(`input#updateFeeId_${rowNumber}`).val(0);
            $(`input#updateFee_${rowNumber}`).val(0);
        }
    }

    getTutorsForGrade();
})();