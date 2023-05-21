<%@ page import="es.proyectotaw.banca.bancapp.entity.RolusuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Carlos Castaño Moreno -->
<html>
<head>
    <title>Gestor - Desbloqueos en empresas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<jsp:include page="cabeceraGestor.jsp"></jsp:include>


<br><br>
<br><br>
<%
    List<RolusuarioEntity> roles = (List<RolusuarioEntity>) request.getAttribute("rolesSolicitantes");
%>

<div class="container">
    <% if (roles.isEmpty()) { %>
    <h1>NO HAY SOLICITUDES</h1>
    <% } else { %>
    <h1>PERSONAL DE EMPRESAS QUE SOLICITAN SU DESBLOQUEO</h1>

    <table class="table table-striped">
        <tr>
            <th>NIF</th>
            <th>EMPRESA</th>
            <th>ROL</th>
            <th></th>
        </tr>

        <% for (RolusuarioEntity r : roles) { %>
        <tr>
            <td><%=r.getUsuarioByIdusuario().getNif()%>
            </td>
            <td><%=r.getEmpresaByIdempresa().getCif()%>
            </td>
            <td><%=r.getRolByIdrol().getNombre()%>
            </td>
            <td><a class="btn btn-success"
                   href="/gestor/desbloquearEnEmpresa?rolUsuario= <%=r.getId()%> ">DESBLOQUEAR</a></td>
        </tr>
        <% } %>
    </table>

    <%}%>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
