<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<% List<UsuarioEntity> listaUsuariosAsociados = (List<UsuarioEntity>) request.getAttribute("usuariosAsociados");%>
<% List<UsuarioEntity> usuariosBloqueados = (List<UsuarioEntity>) request.getAttribute("usuariosBloqueados");%>

<% // María %>
<html>
<head>
    <title>Autorizados y asociados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabeceraMenu.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-75" style="margin: 5% auto auto;">
    <form:form modelAttribute="filtro" method="post" action="/empresa/filtrar">

    </form:form>

    <h3>Lista de socios y autorizados</h3>
    <table class="table p-3">
        <thead>
        <tr>
            <th scope="col" class="col col-1">#</th>
            <th scope="col" class="col col-3">Nombre</th>
            <th scope="col" class="col col-3">Apellidos</th>
            <th scope="col" class="col col-2">Rol</th>
            <th scope="col" class="col col-2">Bloquear</th>
        </tr>
        </thead>
        <tbody>

        <% for (UsuarioEntity usuario : listaUsuariosAsociados) { %>
        <tr>
            <th scope="row">1</th>
            <td><%= usuario.getPrimerNombre() %> <%= usuario.getSegundoNombre() %> </td>
            <td><%= usuario.getPrimerApellido() %> <%= usuario.getSegundoApellido() %></td>
            <td><a href="/empresa/cambiarRol?id=<%=usuario.getId()%>&rol=<%=usuario.getRolusuariosById().get(0).getRolByIdrol().getIdrol()%>"/> <%= usuario.getRolusuariosById().get(0).getRolByIdrol().getNombre() %></td>
            <td><a href="/empresa/bloquearUsuario?id=<%=usuario.getId()%>"/><button class="btn btn-danger
                <%= usuario.getRolusuariosById().get(0).getBloqueado() == (byte) 1 ? " disabled\" disabled=\"true\"" : "" %>
                "> X </button></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <div/>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</body>
</html>