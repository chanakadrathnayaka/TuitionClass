<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.achini.models.Subject" %>
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
                    <form action="tutor-sign-up" method="POST">
                        <div class="form-group">
                            <label for="signUpUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="signUpUserId"
                                   name="signUpUserId" readonly value="${requestScope.signUpUserId}">
                        </div>
                        <div class="form-group">
                            <label for="signUpHighestQualification">Highest Qualification </label>
                            <small class="text-secondary">(Certifications will be required)</small>
                            <input type="text" class="form-control form-control-sm" id="signUpHighestQualification"
                                   name="signUpHighestQualification" placeholder="Enter highest qualification">
                        </div>
                        <div class="form-group">
                            <label>Subjects</label>
                            <button id="addSubjectBtn" type="button" title="Add a subject"
                                    class="btn btn-sm btn-outline-primary rounded float-right">
                                <i class="fas fa-plus"></i></button>
                            <div id="subjectContainer">
                                <div id="subjectTemplate" class="form-inline">
                                    <select title="Select subject"
                                            class="custom-select custom-select-sm my-2 custom-select-left w-40"
                                            name="signUpSubjects">
                                        <option selected value="-1">Subject</option>
                                        <%
                                            List<Subject> result = (List<Subject>) request.getAttribute("subjects");
                                            Iterator<Subject> it = result.iterator();
                                            while (it.hasNext()) {
                                                Subject subject = it.next();
                                                out.println("<option value=\""+subject.getSubjectId() + "\">" + subject.getName() + "</option>");
                                            }
                                        %>
                                    </select>
                                    <select title="Select grade"
                                            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-15"
                                            name="signUpGrade">
                                        <option selected value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                    </select>
                                    <select title="Select Class Type"
                                            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
                                            name="signUpClassType">
                                        <option selected value="INDIVIDUAL">Individual</option>
                                        <option value="GROUP">Group</option>
                                        <option value="MASS">Mass</option>
                                    </select>
                                    <input type="number" class="form-control form-control-sm form-control-right w-20"
                                           name="signUpFee" placeholder="Fee" required>
                                </div>
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

<style type="text/css">
    select.custom-select-left {
        border-bottom-right-radius: 0 !important;
        border-top-right-radius: 0 !important;
        border-right-color: white;
    }

    select.custom-select-middle {
        border-right-color: white;
    }

    input.form-control-right {
        border-bottom-left-radius: 0 !important;
        border-top-left-radius: 0 !important;
    }

    .w-20 {
        width: 20% !important;
    }

    .w-40 {
        width: 40% !important;
    }

    .w-15 {
        width: 15% !important;
    }
</style>
<script type="application/javascript" src="js/tutor-sign-up.js"></script>
</body>
</html>