<!DOCTYPE html>
<html lang="en">
<%@include file="utils/headContent.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="utils/loggin-header.jsp" %>
    <div class="row my-2 justify-content-center">
        <div class="col-4">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    Almost there!
                </div>
                <div class="card-body">
                    <form action="student-sign-up" method="POST">
                        <div class="form-group">
                            <label for="signUpUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="signUpUserId"
                                   name="signUpUserId" readonly value="${requestScope.signUpUserId}">
                        </div>
                        <div class="form-group">
                            <label for="signUpGrade">Grade</label>
                            <input type="number" class="form-control form-control-sm" id="signUpGrade"
                                   name="signUpGrade" placeholder="Enter grade you study">
                        </div>
                        <div class="form-group">
                            <label for="signUpSchool">School</label>
                            <input type="text" class="form-control form-control-sm" id="signUpSchool"
                                   name="signUpSchool" placeholder="Enter school name">
                        </div>
                        <div class="form-group">
                            <label for="signUpSubjects">Subjects</label>
                            <button type="button" title="Add a subject" class="btn btn-sm btn-outline-primary rounded float-right"><i class="fas fa-plus"></i></button>
                            <div class="form-inline">
                                <select title="Select subject"
                                        class="custom-select custom-select-sm my-2 w-50 custom-select-left border-right-0"
                                        name="signUpSubjects" id="signUpSubjects">
                                    <option selected value="1">Maths</option>
                                    <option value="2">Science</option>
                                    <option value="3">English</option>
                                    <option value="4">Commerce</option>
                                </select>
                                <select title="Select subject"
                                        class="custom-select custom-select-sm my-2 w-50 custom-select-right"
                                        name="signUpTutors" id="signUpTutors">
                                    <option selected value="1">Tutor A</option>
                                    <option value="2">Tutor B</option>
                                    <option value="3">Tutor C</option>
                                    <option value="4">Tutor D</option>
                                </select></div>
                        </div>
                        <button type="submit" class="btn btn-sm btn-primary float-right w-25">Save Details</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="utils/footer.jsp" %>
</div>

<style type="text/css">
    select.custom-select-left {
        border-bottom-right-radius: 0 !important;
        border-top-right-radius: 0 !important;
    }

    select.custom-select-right {
        border-bottom-left-radius: 0 !important;
        border-top-left-radius: 0 !important;
    }
</style>
</body>
</html>