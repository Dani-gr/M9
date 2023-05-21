<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // Nuria Rodríguez Tortosa
%>
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
        <h4 class="card-title">Registrarse</h4>
        <jsp:useBean id="error" scope="request" type="java.lang.String"/>
        <c:if test="${!error.blank}">
            <p style="color:red;">
                    ${error}
            </p>
        </c:if>
        <form action="/registro" method="post" class="text-start">
            <jsp:useBean id="cifEmpresa" scope="request" type="java.lang.String"/>
            <c:choose>
                <c:when test="${\"empresa\".equals(entidad)}">
                    <h4>Datos de la empresa:</h4> <br>
                    <label for="cif" class="form-label">CIF</label>
                    <input type="text" id="cif" name="cifEmpresa" class="form-control"
                           value="<%=cifEmpresa == null ? "" : cifEmpresa%>" maxlength="9" minlength="9" size="9"/>
                    <br/>

                    <h5>Dirección de la empresa:</h5> <br>
                    <label for="direccionCalleEmpresa" class="form-label">Calle</label>
                    <input type="text" id="direccionCalleEmpresa" name="direccionCalleEmpresa" class="form-control"
                           value="" maxlength="30" minlength="1" size="30"/>
                    <br/>

                    <label for="direccionNumeroEmpresa" class="form-label">Número</label>
                    <input type="text" id="direccionNumeroEmpresa" name="direccionNumeroEmpresa" class="form-control"
                           value="" maxlength="10" minlength="1" size="10"/>
                    <br/>

                    <label for="direccionPlantaEmpresa" class="form-label">Planta/Puerta/Oficina</label>
                    <input type="text" id="direccionPlantaEmpresa" name="direccionPlantaEmpresa" class="form-control"
                           value="" maxlength="30" minlength="1" size="30"/>
                    <br/>

                    <label for="direccionCiudadEmpresa" class="form-label">Ciudad</label>
                    <input type="text" id="direccionCiudadEmpresa" name="direccionCiudadEmpresa" class="form-control"
                           value="" maxlength="20" minlength="1" size="20"/>
                    <br/>

                    <label for="direccionRegionEmpresa" class="form-label">Región</label>
                    <input type="text" id="direccionRegionEmpresa" name="direccionRegionEmpresa" class="form-control"
                           value="" maxlength="20" minlength="1" size="20"/>
                    <br/>

                    <label for="direccionPaisEmpresa" class="form-label">País</label>
                    <input type="text" id="direccionPaisEmpresa" name="direccoinPaisEmpresa" class="form-control"
                           value="" maxlength="20" minlength="1" size="20"/>
                    <br/>

                    <label for="direccionCodPostalEmpresa" class="form-label">Código Postal</label>
                    <input type="text" id="direccionCodPostalEmpresa" name="direccionPostalEmpresa" class="form-control"
                           maxlength="5" minlength="5" size="5"/>
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
                   value="" maxlength="9" minlength="9" size="9"/>
            <br/>

            <label for="userPNombre" class="form-label">Primer nombre</label>
            <input type="text" id="userPNombre" name="userNombre" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="userSNombre" class="form-label">Segundo nombre</label>
            <input type="text" id="userSNombre" name="userNombreSegundo" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="userPApellido" class="form-label">Primer apellido</label>
            <input type="text" id="userPApellido" name="userApellidoPrimero" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="userSApellido" class="form-label">Segundo apellido</label>
            <input type="text" id="userSApellido" name="userApellidoSegundo" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
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
            <input type="text" id="direccionCalle" name="direccionCalle" class="form-control"
                   value="" maxlength="30" minlength="1" size="30"/>
            <br/>

            <label for="direccionNumero" class="form-label">Número</label>
            <input type="text" id="direccionNumero" name="direccionNumero" class="form-control"
                   value="" maxlength="10" minlength="1" size="10"/>
            <br/>

            <label for="direccionPlanta" class="form-label">Planta/Puerta/Oficina</label>
            <input type="text" id="direccionPlanta" name="direccionPlanta" class="form-control"
                   value="" maxlength="30" minlength="1" size="30"/>
            <br/>

            <label for="direccionCiudad" class="form-label">Ciudad</label>
            <input type="text" id="direccionCiudad" name="direccionCiudad" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="direccionRegion" class="form-label">Región</label>
            <input type="text" id="direccionRegion" name="direccionRegion" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="direccionPais" class="form-label">País</label>
            <input type="text" id="direccionPais" name="direccoinPais" class="form-control"
                   value="" maxlength="20" minlength="1" size="20"/>
            <br/>

            <label for="direccionCodPostal" class="form-label">Código Postal</label>
            <input type="text" id="direccionCodPostal" name="direccionPostal" class="form-control"
                   maxlength="5" minlength="5" size="5"/>
            <br/>

            <input type="hidden" aria-hidden="true" name="entidad" value="<%=entidad%>"/>

            <button name="btnRegistro" value="registrarme" class="btn btn-primary">Registrarme</button>
            <c:choose>
                <c:when test="${\"empresa\".equals(entidad)}">
                    <button name="btnRegistro" value="registrarSocio" class="btn btn-primary">
                        Registrar otro socio/autorizado
                    </button>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </form>
    </div>
    <div class="card-footer">
        <a href="/?entidad=<%=entidad%>" class="btn btn-secondary">
            ¿Ya tienes una cuenta? Iniciar sesi&oacute;n
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
