<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gestor - Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

<!-- Carlos Castaño Moreno -->
<jsp:include page="cabeceraGestor.jsp"></jsp:include>

<%
    ClienteEntity empresa = (ClienteEntity) request.getAttribute("empresa");
%>

<br><br><br><br>

<div class="container-md">
    <h1>DATOS REFERIDOS A LA EMPRESA</h1>

    <table class="table table-striped">
        <tr>
            <td>NÚMERO DE CLIENTE</td>
            <td><%=empresa.getIdCliente()%>
            </td>
        </tr>
        <tr>
            <td>DIRECCIÓN</td>
            <%
                DireccionEntity d = empresa.getDireccionByDireccion();
            %>
            <td><%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                    d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%>
            </td>
        </tr>
        <%
            EmpresaEntity e = empresa.getEmpresasByIdCliente().get(0);
        %>
        <tr>
            <td>CIF</td>
            <td><%=e.getCif()%>
            </td>
        </tr>
        <tr>
            <td>PERSONAL</td>
            <%
                List<RolusuarioEntity> roles = e.getRolusuariosByIdEmpresa();
            %>

            <td>
                <ul>
                    <% for (RolusuarioEntity r : roles) { %>
                    <li>
                        <%= r.getUsuarioByIdusuario().getEmail() + "->" + r.getRolByIdrol().getNombre() %>
                        <%= (r.getBloqueado() != 0) ? "<strong>BLOQUEADO</strong>" : " " %>

                    </li>
                    <% }%>
                </ul>
            </td>
        </tr>

    </table>

    <h3>INFORMACIÓN REFERIDA A LA CUENTA</h3>
    <table class="table table-striped">
        <%
            CuentaEntity cuenta = empresa.getCuentasByIdCliente().get(0);
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
                    List<OperacionEntity> operaciones = empresa.getCuentasByIdCliente().get(0).getOperacionsByNumCuenta();

                    if (operaciones != null && !operaciones.isEmpty()) {
                %>
                <a class="btn btn-secondary" href="/gestor/operaciones?id=<%=empresa.getIdCliente()%>">OPERACIONES</a>
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
