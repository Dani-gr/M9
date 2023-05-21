<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gestor - seguridad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<!-- Carlos Castaño Moreno -->
<jsp:include page="cabeceraGestor.jsp"></jsp:include>

<br>
<br><br>
<br>

<h1 style="text-align: center">SEGURIDAD DE LAS CUENTAS</h1>

<%
    List<CuentasSospechosasEntity> IBANS = (List<CuentasSospechosasEntity>) request.getAttribute("sospechosas"); %>

<div class="container-md">
    <table class="table table-striped">
        <tr>
            <th>NÚMEROS IBAN SOSPECHOSOS</th>
        </tr>

        <%
            for (CuentasSospechosasEntity c : IBANS) {
        %>
        <tr>
            <td><%= c.getIban() %>
            </td>
            <td><a class="btn btn-warning" href="/gestor/borrarSospechosa?id= <%= c.getIdcuentasSospechosas() %> ">BORRAR</a>
            </td>
        </tr>
        <%
            }
        %>

        <tr>
            <form method="post" action="/gestor/addSospechosa">
                <input type="text" size="24" name="ibanSospechoso" class="form-control" minlength="1" required/>
                <input type="submit" value="AÑADIR" class="btn btn-success">
            </form>
        </tr>

    </table>


    <% List<TransferenciaEntity> transferencias = (List<TransferenciaEntity>) request.getAttribute("transferencias");

    %>

    <%
        if (transferencias.isEmpty()) {%>
    <h3>NO HAY TRANSFERENCIAS SOSPECHOSAS</h3>
    <% } else {%>

    <h3>HAY TRANSFERENCIAS SOSPECHOSAS</h3>
    <table class="table table-striped">
        <tr>
            <th>NÚMERO DE OPERACIÓN</th>
            <th>CANTIDAD TRANSFERIDA</th>
            <th>CUENTA ORIGEN</th>
            <th>CUENTA DESTINO</th>
            <th></th>
        </tr>
        <%
            for (TransferenciaEntity t : transferencias) { %>
        <tr>
            <td><%= t.getOperacionByOperacion().getIdOperacion() %>
            </td>
            <td><%= t.getCantidad() %>
            </td>
            <td><%= t.getOperacionByOperacion().getCuentaByCuentaRealiza().getNumCuenta()%>
            </td>
            <td><%= t.getIbanDestino() %>
            </td>
            <td>
                <%
                    if (t.getOperacionByOperacion().getCuentaByCuentaRealiza().getActiva() == 0) {
                %>
                Cuenta bloqueada
                <%
                } else {
                %>
                <a class="btn btn-danger"
                   href="/gestor/bloquearPorTransferencia?cuenta=<%= t.getOperacionByOperacion().getCuentaByCuentaRealiza().getNumCuenta()%>">
                    BLOQUEAR CUENTA</a>
                <%
                    }
                %>
            </td>
        </tr>
        <% } %>
    </table>

    <% }%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
