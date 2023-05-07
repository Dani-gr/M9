<%@ page import="es.proyectotaw.banca.bancapp.entity.RolusuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Desbloqueos en empresas</title>
</head>
<body>
    <jsp:include page="cabeceraGestor.jsp"></jsp:include>


    <br><br>
    <br><br>
    <%
        List<RolusuarioEntity> roles = (List<RolusuarioEntity>) request.getAttribute("rolesSolicitantes");
    %>

    <div class="container">
        <%
            if(!roles.isEmpty()){%>
        <h1>PERSONAL DE EMPRESAS QUE SOLICITAN SU ACTIVACIÓN</h1>

        <table class="table table-striped">
            <tr>
                <th>NIF</th>
                <th>EMPRESA</th>
                <th>ROL</th> <th></th>
            </tr>

            <%   for(RolusuarioEntity r : roles){   %>
            <tr>
                <td> <%=r.getUsuarioByIdusuario().getNif()%> </td>
                <td> <%=r.getEmpresaByIdempresa().getCif()%> </td>
                <td> <a href="/gestor/desbloquearEnEmpresa?rolUsuario= <%=r.getId()%> ">ACTIVAR</a> </td>
            </tr>
            <%    } %>
        </table>

        <%}%>
    </div>



</body>
</html>
