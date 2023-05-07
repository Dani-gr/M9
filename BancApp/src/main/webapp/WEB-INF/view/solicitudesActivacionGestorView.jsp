<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Activacion cuentas</title>
</head>
<body>
    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <%
        List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("cuentas");
    %>

    <%
        if(!cuentas.isEmpty()){%>
            <h1>CUENTAS QUE SOLICITAN SU ACTIVACIÓN</h1>

            <table border="1">
                <tr>
                    <th>NÚMERO</th>
                    <th>CLIENTE</th>
                    <th></th>
                </tr>

         <%   for(CuentaEntity c : cuentas){   %>
                <tr>
                    <td> <%=c.getNumCuenta()%> </td>
                    <td> <%=c.getClienteByCliente().getIdCliente()%> </td>
                    <td> <a href="/gestor/activar?cuenta= <%=c.getNumCuenta()%> ">ACTIVAR</a> </td>
                </tr>
        <%    } %>
            </table>

     <%}%>


</body>
</html>
