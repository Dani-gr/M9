<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 06/05/2023
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Solicitudes de Alta</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <%
        List<ClienteEntity> solicitantes = (List<ClienteEntity>) request.getAttribute("solicitantes");
        if(solicitantes.isEmpty()){
    %>

    <h3 style="text-align: center">ESTÁS AL DÍA</h3>
        <h1 style="text-align: center">NO HAY NINGUNA SOLICITUD DE ALTA EN EL BANCO</h1>

    <% }else{ %>

    <h1 style="text-align: center">SOLICITANTES DE INGRESO EN EL BANCO</h1>

        <table class="table table-success table-striped">
            <tr>
                <th>IDENTIFICACIÓN</th>
                <th>DIRECCIÓN APORTADA</th>
                <th>MÁS INFORMACIÓN</th>
                <th></th> <th></th>
            </tr>

            <%for(ClienteEntity c : solicitantes){
                boolean esEmpresa = !c.getEmpresasByIdCliente().isEmpty()  &&  c.getEmpresasByIdCliente().get(0) != null;
            %>
            <tr>
                <td> <%= c.getIdCliente() %> </td>

                <% DireccionEntity d = c.getDireccionByDireccion(); %>
                <td> <%= d.getCiudad() + " " + d.getPais() + " " + d.getCodpostal() %> </td>

                <td>
                    <%= (esEmpresa) ?
                            "ES UNA EMPRESA" :
                            "ES UN PARTICULAR"
                    %>
                    <a href="/gestor/solicitudesAlta/verSolicitante?id=<%=c.getIdCliente()%>"
                </td>

                <td>
                    <a href="gestor/asignarCuenta?id=<%=c.getIdCliente()%>">ACEPTAR</a>
                </td>
                <td>
                    <a href="gestor/borrarCliente?id=<%=c.getIdCliente()%>">DENEGAR</a>
                </td>

            </tr>
            <%}%>

        </table>

    <%}%>

</body>
</html>
