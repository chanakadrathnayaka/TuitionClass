<%@ page import="com.achini.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
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
        <div class="col-4">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    Edit your profile
                </div>
                <div class="card-body">
                    <form action="sign-up" method="POST">
                        <div class="form-group">
                            <label for="updateName">Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateName"
                                   name="updateName" placeholder="Enter name" required>
                        </div>
                        <div class="form-group">
                            <label for="updateEmail">Email address</label>
                            <input type="email" class="form-control form-control-sm" id="updateEmail"
                                   name="updateEmail" aria-describedby="emailHelp" placeholder="Enter email" required>
                        </div>
                        <div class="form-group">
                            <label for="updateUserName">User Name</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserName"
                                   name="updateUserName" placeholder="Enter user name" required>
                        </div>
                        <div class="form-group">
                            <label for="updatePassword">Password</label>
                            <input type="password" class="form-control form-control-sm" id="updatePassword"
                                   name="updatePassword" placeholder="Enter Password" required>
                        </div>
                        <div class="form-group">
                            <label for="updateBirthDate">Birth Date</label>
                            <input type="date" class="form-control form-control-sm" id="updateBirthDate"
                                   name="updateBirthDate" placeholder="Enter Birth Date" required>
                        </div>
                        <div class="form-group d-none">
                            <label for="updateUserId">User Id</label>
                            <input type="text" class="form-control form-control-sm" id="updateUserId"
                                   name="updateUserId" readonly value="${requestScope.signUpUserId}">
                        </div>
                        <div class="form-group">
                            <label for="updateHighestQualification">Highest Qualification </label>
                            <small class="text-secondary">(Certifications will be required)</small>
                            <input type="text" class="form-control form-control-sm" id="updateHighestQualification"
                                   name="updateHighestQualification" placeholder="Enter highest qualification">
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
                                            name="updateClassType">
                                        <option selected value="INDIVIDUAL">Individual</option>
                                        <option value="GROUP">Group</option>
                                        <option value="MASS">Mass</option>
                                    </select>
                                    <input type="number" class="form-control form-control-sm form-control-right w-20"
                                           name="updateFee" placeholder="Fee" required>
                                </div>
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
</body>
</html>