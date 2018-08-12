<%--
  Created by IntelliJ IDEA.
  User: chanaka
  Date: 8/11/18
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row bg-primary">
    <div class="col-5">
        <h1 class="text-white">Tuition Class</h1>
    </div>
    <div class="col-7">
        <form class="form-inline float-right" action="sign-in" method="POST">
            <div class="input-group input-group-sm my-2 ml-2">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fas fa-user"></i></div>
                </div>
                <input type="text" class="form-control" id="loginUserName" name="loginUserName" placeholder="Username" required>
            </div>
            <div class="input-group input-group-sm my-2 ml-2">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fas fa-key"></i></div>
                </div>
                <input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="Password" required>
            </div>

            <button type="submit" class="btn btn-sm btn-light my-2 ml-2">Sign In</button>
        </form>
    </div>
</div>
