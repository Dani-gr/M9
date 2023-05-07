<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
    <a href="/gestor/solicitudesAlta">VOLVER A SOLICITANTES</a>

    <h1>INFORMACIÓN DEL SOLICITANTE</h1>

    <%
        boolean esEmpresa = (boolean)request.getAttribute("isEmpresa");
        ClienteEntity solicitante = (ClienteEntity) request.getAttribute("solicitante");
    %>

    <p>TIPO: <%= (esEmpresa)? "empresa" : "particular" %> </p>

    <table>
        <tr>
            <th>DIRECCIÓN APORTADA</th>
        </tr>
        <%
            DireccionEntity d = solicitante.getDireccionByDireccion();
        %>
        <tr>
            <%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                    d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%>
        </tr>
    </table>

    <table>
        <tr>
            <th>IDENTIFICACIÓN</th>
        </tr>
        <tr>
            <td> <%= (esEmpresa)? "CIF = " + solicitante.getEmpresasByIdCliente().get(0).getCif() :
                                "NIF = " + solicitante.getUsuariosByIdCliente().get(0).getNif() %> </td>
        </tr>

    </table>

    Elige si quieres que ingrese o no:
    <a href="/gestor/asignarCuenta?id=<%=solicitante.getIdCliente()%>">ACEPTAR</a>
    <a href="/gestor/borrarCliente?id=<%=solicitante.getIdCliente()%>">DENEGAR</a>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
