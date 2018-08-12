<!DOCTYPE html>
<html lang="en">
<%@include file="utils/headContent.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="utils/loggin-header.jsp" %>
    <div class="row my-2">
        <div class="col-8">
            <div class="jumbotron h-100 align-middle">
                <h1 class="display-4">Welcome to tuition class</h1>
                <p class="lead">Put a nice sentence</p>
                <hr class="my-4">
                <p>Put a description</p>
                <a class="btn btn-primary btn-lg" href="#" role="button">Join Now</a>
            </div>
        </div>
        <div class="col-4">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    Sign Up
                </div>
                <div class="card-body">
                    <form action="sign-up" method="POST">
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="signUpUserStudent" name="signUpUserType"
                                   checked class="custom-control-input" value="STUDENT">
                            <label class="custom-control-label" for="signUpUserStudent">Student</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="signUpUserTutor" name="signUpUserType"
                                   class="custom-control-input" value="TUTOR">
                            <label class="custom-control-label" for="signUpUserTutor">Tutor</label>
                        </div>
                        <div class="form-group">
                            <label for="signUpName">Name</label>
                            <input  type="text" class="form-control form-control-sm" id="signUpName"
                                   name="signUpName" placeholder="Enter name" required>
                        </div>
                        <div class="form-group">
                            <label for="signUpEmail">Email address</label>
                            <input type="email" class="form-control form-control-sm" id="signUpEmail"
                                   name="signUpEmail" aria-describedby="emailHelp" placeholder="Enter email" required>
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                                else.
                            </small>
                        </div>
                        <div class="form-group">
                            <label for="signUpUserName">User Name</label>
                            <input type="text" class="form-control form-control-sm" id="signUpUserName"
                                   name="signUpUserName" placeholder="Enter user name" required>
                        </div>
                        <div class="form-group">
                            <label for="signUpPassword">Password</label>
                            <input type="password" class="form-control form-control-sm" id="signUpPassword"
                                   name="signUpPassword" placeholder="Enter Password" required>
                        </div>
                        <div class="form-group">
                            <label for="signUpBirthDate">Birth Date</label>
                            <input type="date" class="form-control form-control-sm" id="signUpBirthDate"
                                   name="signUpBirthDate" placeholder="Enter Birth Date" required>
                        </div>
                        <button type="submit" class="btn btn-sm btn-primary float-right w-25">Join Now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="utils/footer.jsp" %>
</div>
</body>
</html>