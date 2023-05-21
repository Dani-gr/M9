<!-- Carlos Castaño Moreno -->

<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operaciones Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

    <div class="container-md">
        <%
            ClienteEntity c = (ClienteEntity) request.getAttribute("cliente");
            boolean esEmpresa = c.getEmpresasByIdCliente() != null && !c.getEmpresasByIdCliente().isEmpty()  &&  c.getEmpresasByIdCliente().get(0) != null;

            String urlReturn = (esEmpresa)? "/gestor/empresa?id=" : "/gestor/particular?id=";
            urlReturn += c.getIdCliente();
        %>

        <%
            List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
        %>

        <div class="col-1">
            <a class="btn btn-primary" href="<%=urlReturn%>">VOLVER</a>
        </div>
        <br><br>


        <h3>FILTRAR Y ORDENAR</h3>

        <a class="btn btn-success" href="/gestor/operaciones/ordenar?id=<%=c.getIdCliente()%>">ORDENAR POR FECHA MÁS RECIENTE</a> <br>

        <form method="post" action="/gestor/operaciones/filtrar">
            <input type="number" name="id" value="<%=c.getIdCliente()%>" hidden>

            <select name="tipo" required>
                <option value=""></option>
                <option value="t">transferencia</option>
                <option value="cd">cambio de divisa</option>
                <option value="e">extracción</option>
            </select>

            <input class="btn btn-primary" type="submit" value="FILTRAR">
        </form>

        <a class="btn btn-warning" href="/gestor/operaciones?id=<%=c.getIdCliente()%>">SIN ORDENAR Y SIN FILTROS</a> <br>


        <h3 style="text-align: center">HISTORIAL DE OPERACIONES</h3>

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
                                (!op.getExtraccionsByIdOperacion().isEmpty())? "Extracción" : ""
                %> </td>
            </tr>
            <% } %>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
