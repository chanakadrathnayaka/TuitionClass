<%@ page import="com.achini.models.Subject" %>
<%@ page import="com.achini.models.Tutor" %>
<%@ page import="com.achini.models.types.ClassType" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
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
                    <%Tutor tutor = (Tutor) request.getAttribute("tutor");%>
                    <form action="profile" method="POST">
                        <div class="form-group">
                            <label for="updateName">Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateName" name="updateName"
                                   placeholder="Enter name" required value="<%=tutor.getName()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateEmail">Email address</label>
                            <input type="email" class="form-control form-control-sm" id="updateEmail" name="updateEmail"
                                   aria-describedby="emailHelp" placeholder="Enter email" required
                                   value="<%=tutor.getEmail()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateUserName">User Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserName"
                                   name="updateUserName"
                                   placeholder="Enter user name" required value="<%=tutor.getUsername()%>">
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
                                   placeholder="Enter Birth Date" required value="<%=tutor.getBirthDate()%>">
                        </div>
                        <div class="form-group d-none">
                            <label for="updateUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserId"
                                   name="updateUserId" readonly value="<%=tutor.getUserId()%>">
                        </div>
                        <div class="form-group d-none">
                            <label for="updateTutorId">Tutor Id</label>
                            <input type="text" class="form-control form-control-sm" id="updateTutorId"
                                   name="updateTutorId" readonly value="<%=tutor.getTutorId()%>">
                        </div>
                        <div class="form-group">
                            <label for="updateHighestQualification">Highest Qualification </label>
                            <small class="text-secondary">(Certifications will be required)</small>
                            <input type="text" class="form-control form-control-sm" id="updateHighestQualification"
                                   name="updateHighestQualification" placeholder="Enter highest qualification"
                                   value="<%=tutor.getHighestQualification()%>">
                        </div>
                        <div class="form-group">
                            <label>Subjects</label>
                            <button id="addSubjectBtn" type="button" title="Add a subject"
                                    class="btn btn-sm btn-outline-primary rounded-circle float-right">
                                <i class="fas fa-plus"></i></button>
                            <div id="subjectContainer">
                                <%for (Subject selectedSubject : tutor.getSubjects()) { %>
                                <div class="form-inline">
                                    <select title="Select subject"
                                            class="custom-select custom-select-sm my-2 custom-select-left w-40"
                                            name="updateSubjects" disabled>
                                        <%
                                            out.println("<option selected  value=\"" + selectedSubject.getSubjectId() + "\">" + selectedSubject.getName() + "</option>");
                                            out.println("<input type=\"hidden\" name=\"updateSubjects\"  value=\"" + selectedSubject.getSubjectId() + "\">");
                                        %>
                                    </select>
                                    <select title="Select grade"
                                            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-15"
                                            name="updateGrade" disabled>
                                        <%
                                            out.println("<option selected value=\"" + selectedSubject.getGrade() + "\">" + selectedSubject.getGrade() + "</option>");
                                            out.println("<input type=\"hidden\" name=\"updateGrade\"  value=\"" + selectedSubject.getGrade() + "\">");
                                        %>
                                    </select>
                                    <select title="Select Class Type"
                                            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
                                            name="updateClassType" disabled>
                                        <%
                                            out.println("<option selected value=\"" + selectedSubject.getFee().getClassType() + "\">" + selectedSubject.getFee().getClassType() + "</option>");
                                            out.println("<input type=\"hidden\" name=\"updateClassType\"  value=\"" + selectedSubject.getFee().getClassType() + "\">");
                                        %>
                                    </select>
                                    <input type="number" class="form-control form-control-sm form-control-right w-20"
                                           name="updateFee" placeholder="Fee" required
                                           value="<%=selectedSubject.getFee().getAmount()%>">
                                </div>
                                <%}%>
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
            class="custom-select custom-select-sm my-2 custom-select-left w-40"
            name="updateSubjects">
        <option selected value="-1">Subject</option>
        <%
            List<Subject> result = (List<Subject>) request.getAttribute("subjects");
            Iterator<Subject> it = result.iterator();
            while (it.hasNext()) {
                Subject subject = it.next();
                out.println("<option value=\"" + subject.getSubjectId() + "\">" + subject.getName() + "</option>");
            }
        %>
    </select>
    <select title="Select grade"
            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-15"
            name="updateGrade">
        <%
            for (int grade : (int[]) request.getAttribute("supportedGrades"))
                out.println("<option value=\"" + grade + "\">" + grade + "</option>");
        %>
    </select>
    <select title="Select Class Type"
            class="custom-select custom-select-sm my-2 border-right-0 rounded-0 custom-select-middle w-25"
            name="updateClassType">
        <%
            for (ClassType type : (ClassType[]) request.getAttribute("classTypes"))
                out.println("<option value=\"" + type + "\">" + type + "</option>");
        %>
    </select>
    <input type="number" class="form-control form-control-sm form-control-right w-20"
           name="updateFee" placeholder="Fee" required>
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
<script type="application/javascript" src="js/tutor-profile.js"></script>
</body>
</html>