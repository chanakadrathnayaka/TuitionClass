<%@ page import="com.achini.models.Subject" %>
<%@ page import="com.achini.models.Tutor" %><%--
  Created by IntelliJ IDEA.
  User: chanaka
  Date: 8/8/18
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="utils/headContent.jsp" %>
<body>
<div class="container-fluid">
    <%@include file="utils/header.jsp" %>
    <div class="row my-2">
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white h4">
                    <%Tutor tutor = (Tutor) request.getAttribute("tutor");%>
                    <%=tutor.getName()%>
                </div>
                <div class="card-body">
                    <table class="table table-sm">
                        <thead>
                        <tr class="bg-secondary text-white">
                            <th scope="col">Subject</th>
                            <th scope="col">Grade</th>
                            <th scope="col">Class</th>
                            <th scope="col">Class Fee</th>
                        </tr>
                        </thead>
                        <tbody class="text-secondary">
                        <%
                            for (Subject subject : tutor.getSubjects()) {
                        %>
                        <tr>
                            <td><%=subject.getName()%>
                            </td>
                            <td><%=subject.getGrade()%>
                            </td>
                            <td><%=subject.getFee().getClassType()%>
                            </td>
                            <td><%=subject.getFee().getAmount()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@include file="utils/footer.jsp" %>
</div>
</body>
</html>