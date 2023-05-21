<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Carlos Castaño Moreno -->
<html>
<head>
    <title>Gestor - Particular</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

<jsp:include page="cabeceraGestor.jsp"></jsp:include>

<%
    ClienteEntity particular = (ClienteEntity) request.getAttribute("particular");
%>

<br><br><br><br>

<div class="container-md">
    <h1 style="text-align: center">DATOS REFERIDOS AL PARTICULAR</h1>

    <table class="table table-striped">
        <tr>
            <td>NÚMERO DE CLIENTE</td>
            <td><%=particular.getIdCliente()%>
            </td>
        </tr>
        <tr>
            <td>DIRECCIÓN</td>
            <%
                DireccionEntity d = particular.getDireccionByDireccion();
            %>
            <td><%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                    d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%>
            </td>
        </tr>
        <%
            UsuarioEntity u = particular.getUsuariosByIdCliente().get(0);
        %>
        <tr>
            <td>NIF</td>
            <td><%=u.getNif()%>
            </td>
        </tr>
        <tr>
            <td>EMAIL</td>
            <td><%=u.getEmail()%>
            </td>
        </tr>
        <tr>
            <td>NOMBRE COMPLETO</td>
            <td><%=u.getPrimerNombre() + " " %> <%=(u.getSegundoNombre() != null) ? u.getSegundoNombre() : ""%>  <%="\n" +
                    u.getPrimerApellido() + " " %> <%= (u.getSegundoApellido() == null) ? u.getSegundoApellido() : ""%>
            </td>
        </tr>
        <tr>
            <td>FECHA DE NACIMIENTO</td>
            <td><%=u.getFechaNacimiento()%>
            </td>
        </tr>
    </table>

    <h3 style="text-align: center">INFORMACIÓN REFERIDA A LA CUENTA</h3>
    <table class="table table-striped">
        <%
            CuentaEntity cuenta = particular.getCuentasByIdCliente().get(0);
        %>
        <tr>
            <th>NÚMERO</th>
            <th>SALDO ACTUAL</th>
            <th>ESTADO</th>
            <th></th>
        </tr>
        <tr>
            <td><%=cuenta.getNumCuenta()%>
            </td>
            <td><%=cuenta.getSaldo()%>
            </td>
            <td>
                <%= (cuenta.getActiva() == 1) ? "Activa" : "Bloqueada" %>
            </td>
            <td>
                <%
                    List<OperacionEntity> operaciones = particular.getCuentasByIdCliente().get(0).getOperacionsByNumCuenta();

                    if (operaciones != null && !operaciones.isEmpty()) {
                %>
                <a class="btn btn-secondary"
                   href="/gestor/operaciones?id=<%=particular.getIdCliente()%>">OPERACIONES</a>
                <% } else { %>
                <strong>No hay operaciones</strong>
                <% } %>
            </td>
        </tr>
    </table>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
