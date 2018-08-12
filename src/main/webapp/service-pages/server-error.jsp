<%--
  Created by IntelliJ IDEA.
  User: chanaka
  Date: 8/12/18
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../utils/headContent.jsp" %>
<body>
<div class="container-fluid">
    <div class="row bg-primary">
        <div class="col-5">
            <h1 class="text-white">Tuition Class</h1>
        </div>
    </div>
    <div class="row my-5 justify-content-center">
        <div class="col-3 text-center">
            <span class="text-primary">
                <i class="fas fa-cogs huge-font"></i>
            </span>
            <h3>Server Error</h3>
            <a class="btn btn-primary" href="/index.jsp">Go Back to Home</a>
        </div>
    </div>
    <%@include file="../utils/footer.jsp" %>
</div>
<style type="text/css">
    .huge-font {
        font-size: 4em;
    }
</style>
</body>
</html>
