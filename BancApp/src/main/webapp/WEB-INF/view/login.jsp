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
<body class="bg-gradient bg-dark">
<div class="card text-center w-50" style="margin: 5% auto auto;">
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
        <h4 class="card-title">Iniciar sesión</h4>
        <jsp:useBean id="error" scope="request" type="java.lang.String"/>
        <c:if test="${!error.blank}">
            <p style="color:red;">
                    ${error}
            </p>
        </c:if>
        <form action="/" method="post" class="text-start">
            <jsp:useBean id="cifEmpresa" scope="request" type="java.lang.String"/>
            <jsp:useBean id="user" scope="request" type="java.lang.String"/>
            <c:choose>
                <c:when test="${\"empresa\".equals(entidad)}">
                    <label for="cif" class="form-label">CIF de la empresa</label>
                    <input type="text" class="form-control" name="cifEmpresa" id="cif"
                           value="<%=cifEmpresa == null ? "" : cifEmpresa%>" maxlength="9" minlength="9" size="9"/>
                    <br/>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="cifEmpresa" id="cif" aria-hidden="true"/>
                </c:otherwise>
            </c:choose>
            <label for="userEmail" class="form-label">Correo electr&oacute;nico</label>
            <input type="email" id="userEmail" name="user" placeholder="hello@example.taw" class="form-control"
                   value="<%=user == null ? "" : user%>"/>
            <br/>
            <label for="password" class="form-label">Contrase&ntilde;a</label>
            <input type="password" id="password" name="pass" class="form-control"/>
            <br/>
            <input type="hidden" aria-hidden="true" name="entidad" value="<%=entidad%>"/>
            <button class="btn btn-primary">Iniciar sesi&oacute;n</button>
        </form>

    </div>
    <div class="card-footer">
        <a href="/registro?entidad=<%=entidad%>" class="btn btn-secondary disabled" disabled="true"
           aria-disabled="true"> <!-- TODO: Habilitar -->
            ¿No tienes cuenta? Reg&iacute;strate
        </a>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
