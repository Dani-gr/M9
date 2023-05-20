<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 20/05/2023
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-custom py-3 fixed-top shadow-sm">

  <div class="col text-center">
    <h3 style="margin: auto 15px;">
      Gestor: ${usuario.getPrimerNombre()} ${usuario.getSegundoNombre()}
    </h3>
  </div>
  <div class="col-2" aria-hidden="true"></div>

  <div class="col-1">
    <button style="width: 60px;" class="btn btn-danger"
            onclick="window.location.href = '/logout';">
      Salir
    </button>
  </div>
</nav>
