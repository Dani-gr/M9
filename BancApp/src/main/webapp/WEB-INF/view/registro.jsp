<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getAttribute("usuario");
%>
<html>
<head>
    <title>Banco M9</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<!-- TODO Agregar botón para volver al login -->
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
        <h4 class="card-title">Registrarse</h4>
        <!-- TODO Añadir control de errores (máximo/mínimo de caracteres) -->
        <form action="/registro" method="post" class="text-start">
            <jsp:useBean id="cifEmpresa" scope="request" type="java.lang.String"/>
            <c:choose>
                <c:when test="${\"empresa\".equals(entidad)}">
                    Datos de la empresa: <br>
                    <label for="cif" class="form-label">CIF</label>
                    <input type="text" id="cif" name="cifEmpresa" class="form-control"
                            value="<%=cifEmpresa == null ? "" : cifEmpresa%>" maxlength="9" minlength="9" size="9"/>
                    <br/>

                    Datos del
                    <label for="socio" class="form-label">socio</label>
                        <input id="socio" type="radio" name="rol" value="socio" checked>

                    <label for="autorizado" class="form-label">autorizado</label>
                    <input id="autorizado" type="radio" name="rol" value="autorizado">
                    <br>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <label for="userNIF" class="form-label">ID/NIF</label>
            <input type="text" id="userNIF" name="userNIF" class="form-control"
                   value=""/>
            <br/>

            <label for="userPNombre" class="form-label">Primer nombre</label>
            <input type="text" id="userPNombre" name="userNombre" class="form-control"
                   value=""/>
            <br/>

            <label for="userSNombre" class="form-label">Segundo nombre</label>
            <input type="text" id="userSNombre" name="userNombreSegundo" class="form-control"
                   value=""/>
            <br/>

            <label for="userPApellido" class="form-label">Primer apellido</label>
            <input type="text" id="userPApellido" name="userApellidoPrimero" class="form-control"
                   value=""/>
            <br/>

            <label for="userSApellido" class="form-label">Segundo apellido</label>
            <input type="text" id="userSApellido" name="userApellidoSegundo" class="form-control"
                   value=""/>
            <br/>

            <label for="userFechaNacimiento" class="form-label">Fecha de nacimiento</label>
            <input type="date" id="userFechaNacimiento" name="userFechaNacimiento" class="form-control"
                   value=""/>
            <br/>

            <label for="userEmail" class="form-label">Correo electr&oacute;nico</label>
            <input type="email" id="userEmail" name="userEmail" placeholder="hello@example.taw" class="form-control"
                   value=""/>
            <br/>

            <label for="password" class="form-label">Contrase&ntilde;a</label>
            <input type="password" id="password" name="userPassword" class="form-control"/>
            <br/>

            Dirección: <br>
            <label for="direccionCalle" class="form-label">Calle</label>
            <input type="test" id="direccionCalle" name="direccionCalle" class="form-control"/>
            <br/>

            <label for="direccionNumero" class="form-label">Número</label>
            <input type="test" id="direccionNumero" name="direccionNumero" class="form-control"/>
            <br/>

            <label for="direccionPlanta" class="form-label">Planta/Puerta/Oficina</label>
            <input type="test" id="direccionPlanta" name="direccionPlanta" class="form-control"/>
            <br/>

            <label for="direccionCiudad" class="form-label">Ciudad</label>
            <input type="test" id="direccionCiudad" name="direccionCiudad" class="form-control"/>
            <br/>

            <label for="direccionRegion" class="form-label">Región</label>
            <input type="test" id="direccionRegion" name="direccionRegion" class="form-control"/>
            <br/>

            <label for="direccionPais" class="form-label">País</label>
            <input type="test" id="direccionPais" name="direccoinPais" class="form-control"/>
            <br/>

            <label for="direccionCodPostal" class="form-label">Código Postal</label>
            <input type="test" id="direccionCodPostal" name="direccionPostal" class="form-control"/>
            <br/>
            <button class="btn btn-primary">Registrarme</button>
            <c:choose>
                <c:when test="${\"empresa\".equals(entidad)}">
                    <button class="btn btn-primary">
                        <a style="color: white" href="/registro">Registrar otro socio/autorizado</a>
                    </button>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <div class="card-footer">
        <a href="/registro?entidad=<%=entidad%>" class="btn btn-secondary">
            ¿Ya tienes una cuenta? Inicar sesi&oacute;n
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
