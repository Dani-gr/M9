<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Particular</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <%
        ClienteEntity particular = (ClienteEntity)request.getAttribute("particular");
    %>

    <br><br><br><br>

    <div class="container-md">
        <h1 style="text-align: center">DATOS REFERIDOS AL PARTICULAR</h1>

        <table class="table table-striped">
            <tr>
                <td>NÚMERO DE CLIENTE</td>  <td><%=particular.getIdCliente()%></td>
            </tr>
            <tr>
                <td>DIRECCIÓN</td>
                <%
                    DireccionEntity d = particular.getDireccionByDireccion();
                %>
                <td><%=d.getCalle() + " " + d.getNumero() + " " + d.getPlantaPuertaOficina() + "\n" +
                        d.getRegion() + " " + d.getPais() + " " + d.getCodpostal()%></td>
            </tr>
            <%
                UsuarioEntity u = particular.getUsuariosByIdCliente().get(0);
            %>
            <tr>
                <td>NIF</td><td><%=u.getNif()%></td>
            </tr>
            <tr>
                <td>EMAIL</td><td><%=u.getEmail()%></td>
            </tr>
            <tr>
                <td>NOMBRE COMPLETO</td><td><%=u.getPrimerNombre() + " " %> <%=(u.getSegundoNombre() != null)? u.getSegundoNombre(): ""%>  <%="\n" +
                    u.getPrimerApellido() + " " %> <%= (u.getSegundoApellido() == null)? u.getSegundoApellido(): ""%></td>
            </tr>
            <tr>
                <td>FECHA DE NACIMIENTO</td><td><%=u.getFechaNacimiento()%></td>
            </tr>
        </table>

        <h3 style="text-align: center">INFORMACIÓN REFERIDA A LA CUENTA</h3>
        <table class="table table-striped">
            <%
                CuentaEntity cuenta = particular.getCuentasByIdCliente().get(0);
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

        <h3 style="text-align: center">HISTORIAL DE OPERACIONES</h3>
        <%
            List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();
        %>
        <table class="table table-striped">
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
    </div>

</body>
</html>
