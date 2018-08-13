'use strict';

(function () {
    $("button#addSubjectBtn").click(function () {
        let template = $('div#subjectTemplate').html();
        $('div#subjectContainer').append('<div class="form-inline">' + template + '</div>');
    });
})();