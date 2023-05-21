<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    //Nuria Rodríguez Tortosa 30%
    //María Fernández Moreno 70%
%>

<html>
<%
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");

%>
<head>
    <title>Operaciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabeceraMenu.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-75" style="margin: 5% auto auto;">

    <%--@elvariable id="filtroOpEmpresa" type="es.proyectotaw.banca.bancapp.ui.FiltroOperacionesEmpresa"--%>
    <form:form action="/empresa/filtrar" method="post" modelAttribute="filtroOpEmpresa">
        Buscar por: <br/>
        Nombre de operación:
        <form:select multiple="false" path="nombreOperacion">
            <form:option value="ninguno" label="------"/>
            <form:option value="Transferencia" label="Transferencia"/>
            <form:option value="Cambio de divisa" label="Cambio de divisa"/>
            <form:option value="Extraccion" label="Extraccion"/>
        </form:select>
        Fecha
        <form:input type="date" path="fechaFiltro"/>
        <button>Filtrar</button>
    </form:form>

    <%--@elvariable id="ordenarOpEmpresa" type="es.proyectotaw.banca.bancapp.ui.OrdenarOperacionesEmpresa"--%>
    <form:form action="/empresa/ordenar" method="post" modelAttribute="ordenarOpEmpresa">
        Ordenar por: <br/>
        Nombre de operación:
        <form:select multiple="false" path="opcionSeleccionada">
            <form:option value="ninguno" label="------"/>
            <form:option value="cantidad" label="Cantidad"/>
        </form:select>
        <button>Filtrar</button>
    </form:form>
    <h3>Lista de operaciones</h3>
    <table class="table p-3" border="1">
        <thead>
        <tr>
            <th scope="col" class="col col-2">Descripción</th>
            <th scope="col" class="col col-1">Cantidad</th>
            <th scope="col" class="col col-2">Fecha</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (OperacionEntity operacion : operaciones) {
        %>
        <tr>
            <td><%if (!operacion.getTransferenciasByIdOperacion().isEmpty()) {%>
                Transferencia
                <% } else if (!operacion.getExtraccionsByIdOperacion().isEmpty()) { %>
                Extracción
                <% } else {%>
                Cambio de divisa
                <%}%></td>
            <td><%if (!operacion.getTransferenciasByIdOperacion().isEmpty()) {%>
                <%=operacion.getTransferenciasByIdOperacion().get(0).getCantidad()%>
                <% } else if (!operacion.getExtraccionsByIdOperacion().isEmpty()) { %>
                <%=operacion.getExtraccionsByIdOperacion().get(0).getCantidad()%>
                <% } else {%>
                <%=operacion.getCambDivisasByIdOperacion().get(0).getCantidad()%>
                <%}%></td>
            <td><%=operacion.getFecha()%>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>