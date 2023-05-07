<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.OperacionEntity" %>
<html>
<% List<UsuarioEntity> listaUsuariosAsociados = (List<UsuarioEntity>) request.getAttribute("usuariosAsociados");%>
<head>

</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabeceraMenu.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-75" style="margin: 5% auto auto;">
    <h3>Lista de operaciones</h3>
    <table class="table p-3">
        <thead>
        <tr>
            <th scope="col" class="col col-1">#</th>
            <th scope="col" class="col col-3">Nombre</th>
            <th scope="col" class="col col-3">Apellidos</th>
            <th scope="col" class="col col-2">Tipo de operación</th>
            <th scope="col" class="col col-3">Descripción</th>
        </tr>
        </thead>
        <tbody>

        <% for (UsuarioEntity usuario : listaUsuariosAsociados) {
        for (OperacionEntity operacion : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()){ %>
        <tr>
            <th scope="row">1</th>
            <td><%= usuario.getPrimerNombre() %> <%= usuario.getSegundoNombre() %> </td>
            <td><%= usuario.getPrimerApellido() %> <%= usuario.getSegundoApellido() %></td>
            <td><%=operacion.getFecha().toString()%></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>