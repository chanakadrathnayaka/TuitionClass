<%@ page import="com.achini.models.Student" %>
<%@ page import="com.achini.models.Subject" %>
<%@ page import="com.achini.models.Tutor" %>
<%--
  Created by IntelliJ IDEA.
  User: chanaka
  Date: 8/12/18
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="utils/headContent.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="utils/header.jsp" %>
    <div class="row my-2 justify-content-center">
        <div class="col-8">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    Edit your profile
                </div>
                <div class="card-body">
                    <%Student student = (Student) request.getAttribute("student");%>
                    <form action="profile" method="POST">
                        <div class="form-group">
                            <label for="updateName">Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateName" name="updateName"
                                   placeholder="Enter name" required value="<%=student.getName()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateEmail">Email address</label>
                            <input type="email" class="form-control form-control-sm" id="updateEmail" name="updateEmail"
                                   aria-describedby="emailHelp" placeholder="Enter email" required
                                   value="<%=student.getEmail()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateUserName">User Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserName"
                                   name="updateUserName"
                                   placeholder="Enter user name" required value="<%=student.getUsername()%>">
                        </div>
                        <div class="form-group">
                            <label for="updatePassword">Password</label>
                            <input type="password" class="form-control form-control-sm" id="updatePassword"
                                   name="updatePassword" placeholder="Enter Password" required
                                   value="<%=request.getAttribute("password")%>">
                        </div>
                        <div class="form-group">
                            <label for="updateBirthDate">Birth Date</label>
                            <input type="date" class="form-control form-control-sm" id="updateBirthDate"
                                   name="updateBirthDate"
                                   placeholder="Enter Birth Date" required value="<%=student.getBirthDate()%>">
                        </div>
                        <div class="form-group d-none">
                            <label for="updateUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserId"
                                   name="updateUserId" readonly value="<%=student.getUserId()%>">
                        </div>
                        <div class="form-group d-none">
                            <label for="updateStudentId">Student Id</label>
                            <input type="text" class="form-control form-control-sm" id="updateStudentId"
                                   name="updateStudentId" readonly value="<%=student.getStudentId()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateGrade">Grade</label>
                            <select title="Select grade"
                                    class="custom-select custom-select-sm my-2"
                                    name="updateGrade" id="updateGrade">
                                <option selected value="-1">-Select-</option>
                                <%
                                    for (int grade : (int[]) request.getAttribute("supportedGrades"))
                                        out.println("<option" + (grade == student.getGrade() ? " selected" : "") + " value=\"" + grade + "\">" + grade + "</option>");
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="updateSchool">School</label>
                            <input type="text" class="form-control form-control-sm" id="updateSchool"
                                   name="updateSchool" placeholder="Enter school name" value="<%=student.getSchool()%>">
                        </div>
                        <div class="form-group">
                            <label>Subjects</label>
                            <small class="text-secondary"> You can not edit these later</small>
                            <button id="addSubjectBtn" type="button" title="Add a subject"
                                    class="btn btn-sm btn-outline-primary rounded-circle float-right"><i
                                    class="fas fa-plus"></i></button>
                            <div id="subjectContainer">
                                <%
                                    if (student.getTutors() != null) {
                                        for (Tutor tutor : student.getTutors()) {
                                            for (Subject subject : tutor.getSubjects()) {

                                %>
                                <div class="form-inline">
                                    <select title="Select subject"
                                            class="custom-select custom-select-sm my-2 custom-select-left w-25"
                                            disabled>
                                        <option selected value="<%=subject.getSubjectId()%>"><%=subject.getName()%>
                                        </option>
                                    </select>
                                    <select title="Select Class Type"
                                            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
                                            disabled>
                                        <option selected
                                                value="<%=subject.getFee().getClassType()%>"><%=subject.getFee().getClassType()%>
                                        </option>
                                    </select>
                                    <select title="Select tutor"
                                            class="custom-select custom-select-sm my-2 custom-select-right w-50"
                                            disabled>
                                        <option selected value="<%=tutor.getTutorId()%>"><%=tutor.getName()%>
                                        </option>
                                    </select>
                                </div>
                                <% }
                                }
                                }%>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-sm btn-primary float-right w-25">Save</button>
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
            name="updateSubjects" id="updateSubjects">
        <option selected value="-1">-Select-</option>
    </select>
    <select title="Select Class Type"
            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
            name="updateClassType" id="updateClassType">
        <option selected value="-1">-Select-</option>
    </select>
    <select title="Select tutor"
            class="custom-select custom-select-sm my-2 custom-select-right w-50"
            name="updateTutor" id="updateTutor">
        <option selected value="-1">-Select-</option>
    </select>
    <input type="number" class="form-control form-control-sm form-control-right d-none"
           name="updateFeeId" id="updateFeeId">
    <input type="number" class="form-control form-control-sm form-control-right d-none"
           name="updateFee" id="updateFee">
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
<script type="application/javascript" src="js/student-profile.js"></script>
</body>
</html>