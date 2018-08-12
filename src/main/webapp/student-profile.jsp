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
        <div class="col-4">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    Sign Up
                </div>
                <div class="card-body">
                    <form action="sign-up" method="POST">
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="updateUserStudent" name="updateUserType"
                                   checked class="custom-control-input" value="STUDENT">
                            <label class="custom-control-label" for="updateUserStudent">Student</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="updateUserTutor" name="updateUserType"
                                   class="custom-control-input" value="TUTOR">
                            <label class="custom-control-label" for="updateUserTutor">Tutor</label>
                        </div>
                        <div class="form-group">
                            <label for="updateName">Name</label>
                            <input  type="text" class="form-control form-control-sm" id="updateName"
                                    name="updateName" placeholder="Enter name" required>
                        </div>
                        <div class="form-group">
                            <label for="updateEmail">Email address</label>
                            <input type="email" class="form-control form-control-sm" id="updateEmail"
                                   name="updateEmail" aria-describedby="emailHelp" placeholder="Enter email" required>
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                                else.
                            </small>
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