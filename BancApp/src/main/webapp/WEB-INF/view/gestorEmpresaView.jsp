<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 06/05/2023
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Empresa</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <%
        ClienteEntity empresa = (ClienteEntity)request.getAttribute("empresa");
    %>

    <h1>DATOS REFERIDOS AL PARTICULAR</h1>

    <table border="1">
        <tr>
            <td>NÚMERO DE CLIENTE</td>  <td><%=empresa.getIdCliente()%></td>
        </tr>
        <tr>
            <td>DIRECCIÓN</td>
            <%
                DireccionEntity d = empresa.getDireccionByDireccion();
            %>
            <td><%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                    d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%></td>
        </tr>
        <%
            EmpresaEntity e = empresa.getEmpresasByIdCliente().get(0);
        %>
        <tr>
            <td>CIF</td><td><%=e.getCif()%></td>
        </tr>
        <tr>
            <td>PERSONAL</td>
            <%
                List<RolusuarioEntity> roles = e.getRolusuariosByIdEmpresa();
            %>

            <td>
                <ul>
                    <% for(RolusuarioEntity r : roles){ %>
                        <li>
                            <%= r.getUsuarioByIdusuario().getEmail() + "->" + r.getRolByIdrol().getNombre() %>
                        </li>
                    <% }%>
                </ul>
            </td>
        </tr>

    </table>

    <h3>INFORMACIÓN REFERIDA A LA CUENTA</h3>
    <table border="1">
        <%
            CuentaEntity cuenta = empresa.getCuentasByIdCliente().get(0);
        %>
        <tr>
            <th>NÚMERO</th><th>SALDO ACTUAL</th><th>ESTADO</th>
        </tr>
        <tr>
            <td><%=cuenta.getNumCuenta()%></td><td><%=cuenta.getSaldo()%></td>
            <td>
                <%= (cuenta.getActiva() == 1)? "Activa" : "Bloqueada" %>
            </td>

        </tr>
    </table>

    <h3>HISTORIAL DE OPERACIONES</h3>
    <%
        List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();
    %>
    <table>
        <tr>
            <th>NÚMERO DE OPERACIÓN</th><th>FECHA</th><th>TIPO</th>
        </tr>
        <% for(OperacionEntity op : operaciones){ %>
            <tr>
                <td> <%=op.getIdOperacion()%> </td>
                <td> <%=op.getFecha()%> </td>
                <td> <%=
                    (!op.getTransferenciasByIdOperacion().isEmpty())? "Transferencia" :
                            (!op.getCambDivisasByIdOperacion().isEmpty())? "Cambio de divisa" :
                                    (!op.getExtraccionsByIdOperacion().isEmpty())? "Extraccion" : ""
                %> </td>
            </tr>
        <% } %>
    </table>

</body>
</html>
