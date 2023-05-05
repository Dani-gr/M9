<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.RolEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
    @SuppressWarnings("unchecked")
    List<RolEntity> roles = (List<RolEntity>) session.getAttribute("roles");
    /*List<String> nombresRoles = new ArrayList<>();
    for (RolEntity rol :
            roles) {
        nombresRoles.add(rol.getNombre());
    }*/
    List<String> nombresRoles = roles.stream().map(RolEntity::getNombre).toList();
%>
<html>
<head>
    <title>Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabecera.jsp"/>
<div class="m-3">&nbsp;</div>

<div class="d-flex justify-content-center">
    <div class="card text-center w-75" style="margin: 5% auto auto;">
        <div class="card-header">
            <div class="row mt-3">
                <div class="col col-4">
                    <div class="btn btn-lg btn-outline-secondary">Transferencia</div>
                </div>
                <div class="col col-4">
                    <div class="btn btn-lg btn-outline-secondary">Cambio de divisa</div>
                </div>
                <div class="col">
                    <div class="btn btn-lg btn-outline-secondary">Cajero</div>
                </div>
            </div>
            <c:if test="${nombresRoles.contains(\"gestor\")}">
                <div class="row">
                    <div class="col pt-3">
                        <div class="btn btn-lg btn-outline-secondary">Gestión de socios y autorizados</div>
                    </div>
                </div>
            </c:if>

            <div class="row m-3"></div>
            <div class="row">
                <div class="col">
                    <p>¿Necesitas ayuda?</p>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <div class="btn btn-lg btn-outline-secondary">Busca chat con un asistente</div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="mt-5">

</div>
<div class="mt-5">
    &nbsp;
</div>
<div class="mt-5">
    &nbsp;
</div>
<div class="mt-5">
    &nbsp;
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>