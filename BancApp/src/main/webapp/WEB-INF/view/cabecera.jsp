<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 02/05/2023
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="usuario" scope="request" type="es.proyectotaw.banca.bancapp.entity.UsuarioEntity"/>
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
        <button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-close"
                onclick="window.location.href = '/logout';">
            Salir
        </button>
    </div>
</nav>