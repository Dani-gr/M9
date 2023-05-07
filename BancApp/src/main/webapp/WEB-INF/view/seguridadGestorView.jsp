<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 06/05/2023
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gestor - seguridad</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <h1>SEGURIDAD DE LAS CUENTAS</h1>

    <%
        List<CuentasSospechosasEntity> IBANS = (List<CuentasSospechosasEntity>) request.getAttribute("sospechosas");
        List<TransferenciaEntity> transferencias = (List<TransferenciaEntity>) request.getAttribute("transferencias");

        if(IBANS.isEmpty()){
    %>  <h3>NO HAY NINGUNA CUENTA SOSPECHOSA</h3>

    <%
        }else{%>

            <table border = "1">
                <tr><th>NÚMEROS IBAN SOSPECHOSOS</th></tr>
                <tr>
                    <%
                        for(CuentasSospechosasEntity c : IBANS){
                    %>
                        <td> <%= c.getIban() %> </td>
                    <%
                        }
                    %>
                </tr>
            </table>

            <%
                if(!transferencias.isEmpty()){ %>

                <table border="1">
                    <tr>
                        <th>NÚMERO DE OPERACIÓN</th>
                        <th>CANTIDAD TRANSFERIDA</th>
                        <th>CUENTA ORIGEN</th>
                        <th>CUENTA DESTINO</th>
                        <th>  </th>
                    </tr>
                    <%
                        for(TransferenciaEntity t : transferencias){ %>
                            <th> <%= t.getOperacionByOperacion().getIdOperacion() %> </th>
                            <th> <%= t.getCantidad() %> </th>
                            <th> <%= t.getOperacionByOperacion().getCuentaByCuentaRealiza().getNumCuenta()%> </th>
                            <th> <%= t.getIbanDestino() %> </th>
                            <th> <a href="/gestor/bloquearPorTransferencia?cuenta=<%= t.getOperacionByOperacion().getCuentaByCuentaRealiza().getNumCuenta()%>">
                                        BLOQUEAR CUENTA</a> </th>
                    <%  }   %>
                </table>

             <% }%>

    <%  }%>

</body>
</html>
