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
                        <div class="form-group d-none">
                            <label for="signUpUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="signUpUserId"
                                   name="signUpUserId" readonly value="${requestScope.signUpUserId}">
                        </div>
                        <div class="form-group">
                            <label for="signUpGrade">Grade</label>
                            <select title="Select grade"
                                    class="custom-select custom-select-sm my-2"
                                    name="signUpGrade" id="signUpGrade">
                                <option selected value="-1">-Select-</option>
                                <%
                                    for (int grade : (int[]) request.getAttribute("supportedGrades"))
                                        out.println("<option value=\"" + grade + "\">" + grade + "</option>");
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="signUpSchool">School</label>
                            <input type="text" class="form-control form-control-sm" id="signUpSchool"
                                   name="signUpSchool" placeholder="Enter school name">
                        </div>
                        <div class="form-group">
                            <label>Subjects</label>
                            <small class="text-secondary"> You can not edit these later</small>
                            <button id="addSubjectBtn" type="button" title="Add a subject"
                                    class="btn btn-sm btn-outline-primary rounded-circle float-right"><i
                                    class="fas fa-plus"></i></button>
                            <div id="subjectContainer">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-sm btn-primary float-right w-25">Save Details</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="utils/footer.jsp" %>
</div>
<div id="subjectTemplate" class="form-inline d-none">
    <select title="Select subject"
            class="custom-select custom-select-sm my-2 custom-select-left w-25"
            name="signUpSubjects" id="signUpSubjects">
        <option selected value="-1">-Select-</option>
    </select>
    <select title="Select Class Type"
            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
            name="signUpClassType" id="signUpClassType">
        <option selected value="-1">-Select-</option>
    </select>
    <select title="Select tutor"
            class="custom-select custom-select-sm my-2 custom-select-right w-50"
            name="signUpTutor" id="signUpTutor">
        <option selected value="-1">-Select-</option>
    </select>
    <input type="number" class="form-control form-control-sm form-control-right d-none"
           name="signUpFeeId" id="signUpFeeId">
    <input type="number" class="form-control form-control-sm form-control-right d-none"
           name="signUpFee" id="signUpFee">
</div>
<style type="text/css">
    select.custom-select-left {
        border-bottom-right-radius: 0 !important;
        border-top-right-radius: 0 !important;
        border-right-color: white;
    }

    select.custom-select-middle {
        border-right-color: white;
    }

    select.custom-select-right {
        border-bottom-left-radius: 0 !important;
        border-top-left-radius: 0 !important;
    }

    .w-50 {
        width: 50% !important;
    }
</style>
<script type="application/javascript" src="js/student-sign-up.js"></script>
</body>
</html>