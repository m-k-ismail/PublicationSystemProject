<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../HTML/navBar.html" %>

<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../CSS/licenseControl.css">
</head>
<body class="dark-overlay">

<div class="jumbotron">
</div>

<div class="container">
    <ul class="list-group">
        <c:forEach var="temp" items="${doi}" varStatus="status">
            <li class="list-group-item">${temp}
                <form action="/action/UpdateLicense?doi=${temp}" method="post">
                    <div class="custom-control custom-checkbox float-right">
                        <c:choose>
                            <c:when test="${license[status.index]==1}">
                                <label class="switch">
                                    <input type="checkbox" class="custom-control-input" name="license" value="true"
                                           onChange="this.form.submit()" id="${temp}">
                                    <span class="slider round"></span>
                                </label>
                            </c:when>
                            <c:otherwise>
                                <label class="switch">
                                    <input type="checkbox" class="custom-control-input" name="license" value="true"
                                           onChange="this.form.submit()" id="${temp}" checked="checked">
                                    <span class="slider round"></span>
                                </label>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
