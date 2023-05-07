<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gestor - seguridad</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <br>
    <br><br>
    <br>

    <h1 style="text-align: center">SEGURIDAD DE LAS CUENTAS</h1>

    <%
        List<CuentasSospechosasEntity> IBANS = (List<CuentasSospechosasEntity>) request.getAttribute("sospechosas"); %>

    <div class="container-md">
        <table class="table table-striped">
            <tr><th>NÚMEROS IBAN SOSPECHOSOS</th></tr>

            <%
                for(CuentasSospechosasEntity c : IBANS){
            %>
            <tr><td> <%= c.getIban() %> </td>
                <td> <a href="/gestor/borrarSospechosa?id= <%= c.getIdcuentasSospechosas() %> ">BORRAR</a> </td>
            </tr>
            <%
                }
            %>

            <tr>
                <form method="post" action="/gestor/addSospechosa" >
                    <input type="text" size="24" name="ibanSospechoso" class="form-control" minlength="1" required/>
                    <input type="submit" value="AÑADIR" class="btn btn-success">
                </form>
            </tr>

        </table>


    <%  List<TransferenciaEntity> transferencias = (List<TransferenciaEntity>) request.getAttribute("transferencias");

    %>

            <%
                if(!transferencias.isEmpty()){ %>
                <h3>HAY TRANSFERENCIAS SOSPECHOSA</h3>
                <table class="table table-striped">
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
                            <th>
                                <%
                                    if(t.getOperacionByOperacion().getCuentaByCuentaRealiza().getActiva() == 0){
                                %>
                                    Cuenta bloqueada
                                <%
                                    }else{
                                %>
                                        <a href="/gestor/bloquearPorTransferencia?cuenta=<%= t.getOperacionByOperacion().getCuentaByCuentaRealiza().getNumCuenta()%>">
                                        BLOQUEAR CUENTA</a>
                                <%
                                    }
                                %>
                            </th>
                    <%  }   %>
                </table>

             <% }%>
    </div>
</body>
</html>
