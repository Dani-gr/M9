<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 01/05/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Banco M9</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="card text-center">
    <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
            <jsp:useBean id="entidad" scope="request" type="java.lang.String"/>
            <c:choose>
                <c:when test="${\"persona\".equals(entidad)}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="true" href="?entidad=persona">Personas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?entidad=empresa">Empresas</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="?entidad=persona">Personas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="true" href="?entidad=empresa">Empresas</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <div class="card-body">
        <a href="#" class="btn btn-primary">Registrarse</a>
        <a href="#" class="btn btn-primary">Iniciar sesión</a>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
