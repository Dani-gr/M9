<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.OperacionEntity" %>
<html>
<% List<UsuarioEntity> listaUsuariosAsociados = (List<UsuarioEntity>) request.getAttribute("usuariosAsociados");%>
<head>
<title>Operaciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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
            <th scope="col" class="col col-2">Descripción</th>
            <th scope="col" class="col col-1">Cantidad</th>
            <th scope="col" class="col col-2">Fecha</th>
        </tr>
        </thead>
        <tbody>

        <% for (UsuarioEntity usuario : listaUsuariosAsociados) {
        for (OperacionEntity operacion : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()){ %>
        <tr>
            <th scope="row">1</th>
            <td><%= usuario.getPrimerNombre() %> <%= usuario.getSegundoNombre() %> </td>
            <td><%= usuario.getPrimerApellido() %> <%= usuario.getSegundoApellido() %></td>
            <td><%= operacion.getCambDivisasByIdOperacion().isEmpty() ? "" : "Cambio de divisas"%>
                    <%= operacion.getExtraccionsByIdOperacion().isEmpty() ? "" : "Extraccion"%>
                    <%= operacion.getTransferenciasByIdOperacion().isEmpty() ? "" : "Transferencia"%></td>
            <td><%= operacion.getCambDivisasByIdOperacion().isEmpty() ? "" : operacion.getCambDivisasByIdOperacion().get(0).getCantidad()%>
                <%= operacion.getExtraccionsByIdOperacion().isEmpty() ? "" : operacion.getExtraccionsByIdOperacion().get(0).getCantidad()%>
                <%= operacion.getTransferenciasByIdOperacion().isEmpty() ? "" : operacion.getTransferenciasByIdOperacion().get(0).getCantidad()%></td>
            <td><%=operacion.getFecha().toString()%></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>