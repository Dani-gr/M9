<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor</title>
</head>
<body>
    <h1>PÁGINA PRINCIPAL DE GESTOR</h1>

    <h2>CLIENTES DEL SISTEMA</h2>

    <table border = "1">
        <tr>
            <th>ID</th>
            <th>DIRECCIÓN FISCAL</th>
            <th></th>
        </tr>

<%
    List<ClienteEntity> clientes = (List<ClienteEntity>) request.getAttribute("clientes");

    for(ClienteEntity c : clientes){ %>
        <tr>
            <td> <%= c.getIdCliente() %> </td>
            <% DireccionEntity d = c.getDireccionByDireccion(); %>
            <td> <%= d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + " " +
                    d.getCiudad() + " " + d.getCodpostal() %> </td>
            <td> <a href="/gestor/cliente?id=<%=c.getIdCliente()%>" >Informacion</a> </td>
        </tr>
<%  }
%>


    </table>

</body>
</html>
