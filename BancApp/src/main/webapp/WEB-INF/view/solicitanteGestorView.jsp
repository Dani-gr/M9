<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Carlos Castaño Moreno -->
<html>
<head>
    <title>Solicitante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

<div class="col-1">
    <a class="btn btn-primary" href="/gestor/solicitudesAlta">VOLVER A SOLICITANTES</a>
</div>

<br>

<h1 style="text-align: center">INFORMACIÓN DEL SOLICITANTE</h1>

<%
    boolean esEmpresa = (boolean) request.getAttribute("isEmpresa");
    ClienteEntity solicitante = (ClienteEntity) request.getAttribute("solicitante");
%>

<div class="container-md">
    <p>TIPO: <%= (esEmpresa) ? "empresa" : "particular" %>
    </p>

    <table class="table table-striped">
        <tr>
            <td><strong>DIRECCIÓN APORTADA</strong></td>
            <%
                DireccionEntity d = solicitante.getDireccionByDireccion();
            %>
            <td><%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                    d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%>
            </td>
        </tr>
        <tr>
            <td><strong>IDENTIFICACIÓN</strong></td>
            <td><%= (esEmpresa) ? "CIF = " + solicitante.getEmpresasByIdCliente().get(0).getCif() :
                    "NIF = " + solicitante.getUsuariosByIdCliente().get(0).getNif() %>
            </td>
        </tr>
    </table>


    <br><br>
    Elige si quieres que ingrese o no:
    <a class="btn btn-success" href="/gestor/asignarCuenta?id=<%=solicitante.getIdCliente()%>">ACEPTAR</a>
    <a class="btn btn-danger" href="/gestor/borrarCliente?id=<%=solicitante.getIdCliente()%>">DENEGAR</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
