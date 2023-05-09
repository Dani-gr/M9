<%@ page import="es.proyectotaw.banca.bancapp.entity.RolEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="usuario" scope="session" type="es.proyectotaw.banca.bancapp.entity.UsuarioEntity"/>
<jsp:useBean id="nombresRoles" scope="session" type="java.util.List<java.lang.String>"/>

<c:if test="${empty usuario}">
    <jsp:forward page="/"/>
</c:if>

<jsp:useBean id="menu" scope="session" type="java.lang.String"/>
<nav class="navbar navbar-custom py-3 fixed-top shadow-sm">
    <div class="col-3">
        <c:if test="${!nombresRoles.contains(\"gestor\") && !nombresRoles.contains(\"asistente\")}">
            <form method="post" action="/menu" style="margin: auto 15px;">
                <button type="submit" class="btn btn-warning ">
                    Cambiar a modo <%="cajero".equalsIgnoreCase(menu) ? "web" : "cajero"%>
                </button>
            </form>
        </c:if>
    </div>
    <div class="col text-center">
        <h3 id="saludo" style="margin: auto 15px;">
            Bienvenido, ${usuario.getPrimerNombre()} ${usuario.getSegundoNombre()}
        </h3>
    </div>
    <div class="col-2" aria-hidden="true"></div>
    <div class="col-1">
        <button style="width: 100px;" class="btn btn-info"
            onclick="window.location.href = '/cliente/datosUsuario';">
            Mis datos
        </button>
    </div>
    <div class="col-1">
        <button style="width: 100px;" class="btn btn-info"
                onclick="window.location.href = '/empresa/datosEmpresa';">
            Datos de mi empresa
        </button>
    </div>

    <div class="col-1">
        <button style="width: 100px;" class="btn btn-info"
                onclick="window.location.href = '/empresa/operaciones';">
            Ver operaciones
        </button>
    </div>

    <div class="col-1">
        <button style="width: 60px;" class="btn btn-danger"
                onclick="window.location.href = '/logout';">
            Salir
        </button>
    </div>
</nav>