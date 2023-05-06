<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="usuario" scope="session" type="es.proyectotaw.banca.bancapp.entity.UsuarioEntity"/>
<c:if test="${empty usuario}">
    <jsp:forward page="/" />
</c:if>
<nav class="navbar navbar-custom py-3 fixed-top shadow-sm">
    <div class="col-1">
        <button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-secondary"
                onclick="window.location.href = '/menu';">
            Volver
        </button>
    </div>
    <div class="col">
        <h3 id="saludo">Bienvenido, ${usuario.getPrimerNombre()} ${usuario.getSegundoNombre()}</h3>
    </div>
    <div class="col-1">
        <button style="width: 60px" class="btn btn-danger"
                onclick="window.location.href = '/logout/datosUsuario';">
            Salir
        </button>
    </div>
</nav>