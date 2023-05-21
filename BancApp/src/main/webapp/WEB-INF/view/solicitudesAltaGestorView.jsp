<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Carlos Castaño Moreno -->
<html>
<head>
    <title>Solicitudes de Alta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

<jsp:include page="cabeceraGestor.jsp"></jsp:include>

<br><br>
<br><br>
<%
    List<ClienteEntity> solicitantes = (List<ClienteEntity>) request.getAttribute("solicitantes");
    if (solicitantes.isEmpty()) {
%>

<h3 style="text-align: center">ESTÁS AL DÍA</h3>
<h1 style="text-align: center">NO HAY NINGUNA SOLICITUD DE ALTA EN EL BANCO</h1>

<% } else { %>

<h1 style="text-align: center">SOLICITANTES DE INGRESO EN EL BANCO</h1>

<table class="table table-striped">
    <tr>
        <th>IDENTIFICACIÓN</th>
        <th>DIRECCIÓN APORTADA</th>
        <th>MÁS INFORMACIÓN</th>
        <th></th>
        <th></th>
    </tr>

    <%
        for (ClienteEntity c : solicitantes) {
            boolean esEmpresa = !c.getEmpresasByIdCliente().isEmpty() && c.getEmpresasByIdCliente().get(0) != null;
    %>
    <tr>
        <td><%= c.getIdCliente() %>
        </td>

        <% DireccionEntity d = c.getDireccionByDireccion(); %>
        <td><%= d.getCiudad() + " " + d.getPais() + " " + d.getCodpostal() %>
        </td>

        <td>
            <%= (esEmpresa) ?
                    "ES UNA EMPRESA" :
                    "ES UN PARTICULAR"
            %>
            <a href="/gestor/verSolicitante?id=<%=c.getIdCliente()%>">INFORMACIÓN</a>
        </td>

        <td>
            <a class="btn btn-success" href="/gestor/asignarCuenta?id=<%=c.getIdCliente()%>">ACEPTAR</a>
        </td>
        <td>
            <a class="btn btn-danger" href="/gestor/borrarCliente?id=<%=c.getIdCliente()%>">DENEGAR</a>
        </td>

    </tr>
    <%}%>

</table>

<%}%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

</body>
</html>
