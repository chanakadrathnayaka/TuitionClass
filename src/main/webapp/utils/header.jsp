<%--
  Created by IntelliJ IDEA.
  User: chanaka
  Date: 8/11/18
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row bg-primary justify-content-between">
    <div class="col-5">
        <h1 class="text-white">Tuition Class</h1>
    </div>
    <div class="col-2">
        <form class="d-inline float-right" action="profile" method="GET">
            <input type="hidden" name="user" value="<%=request.getParameter("user")%>">
            <button type="submit" class="btn btn-sm btn-light my-2 ml-2">Go to profile</button>
        </form>
        <form class="form-inline float-right"  action="sign-out" method="POST">
            <button type="submit" class="btn btn-sm btn-light my-2 ml-2">Sign Out</button>
        </form>
    </div>
</div>
