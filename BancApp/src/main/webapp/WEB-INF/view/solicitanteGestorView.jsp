<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitante</title>
</head>
<body>
    <a href="/gestor/solicitudesAlta">VOLVER A SOLICITANTES</a>

    <h1>INFORMACIÓN DEL SOLICITANTE</h1>

    <%
        boolean esEmpresa = (boolean)request.getAttribute("esEmpresa");
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
    <a href="gestor/asignarCuenta?id=<%=solicitante.getIdCliente()%>">ACEPTAR</a>
    <a href="gestor/borrarCliente?id=<%=solicitante.getIdCliente()%>">DENEGAR</a>

</body>
</html>
