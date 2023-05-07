<%@ page import="es.proyectotaw.banca.bancapp.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentaEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    @SuppressWarnings("unchecked")
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
    UsuarioEntity usuarioOpe = (UsuarioEntity) request.getAttribute("usuario");
%>
<html>
<head>
    <title>Oepraciones cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabeceraMenu.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-75" style="margin: 5% auto auto;">
    <%--@elvariable id="filtro" type="es.proyectotaw.banca.bancapp.ui.FiltroOperaciones"--%>
    <form:form action="/cliente/filtrar" method="post" modelAttribute="filtro">
        Buscar por: <br/>
        Nombre de operación:
        <form:select multiple="false" path="nombreOperacion">
            <form:option value="ninguno" label="------" />
            <form:option value="Transferencia" label="Transferencia"/>
            <form:option value="Cambio de divisa" label="Cambio de divisa"/>
            <form:option value="Extraccion" label="Extraccion"/>
        </form:select>
        Cantidad >=: <form:input path="cantidadFiltro" />
        <button>Filtrar</button>
    </form:form>
    <%--@elvariable id="ordenar" type="es.proyectotaw.banca.bancapp.ui.OrdenarOperaciones"--%>
    <form:form action="/cliente/ordenar" method="post" modelAttribute="ordenar">
        Ordenar por: <br/>
        Fecha <form:input path="fecha"/>
        <button>Ordenar</button>
    </form:form>
    <h3>Lista de operaciones</h3>
    <table class="table p-3">
        <thead>
        <tr>
            <th scope="col" class="col col-1">#</th>
            <th scope="col" class="col col-2">Descripción</th>
            <th scope="col" class="col col-1">Cantidad</th>
            <th scope="col" class="col col-2">Fecha</th>
        </tr>
        </thead>
        <tbody>

        <%
            for (OperacionEntity ope: usuarioOpe.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
        %>
        <tr>
            <th scope="row">1</th>
            <td><%= ope.getCambDivisasByIdOperacion().isEmpty() ? "" : "Cambio de divisas"%>
                <%= ope.getExtraccionsByIdOperacion().isEmpty() ? "" : "Extraccion"%>
                <%= ope.getTransferenciasByIdOperacion().isEmpty() ? "" : "Transferencia"%></td>
            <td><%= ope.getCambDivisasByIdOperacion().isEmpty() ? "" : ope.getCambDivisasByIdOperacion().get(0).getCantidad()%>
                <%= ope.getExtraccionsByIdOperacion().isEmpty() ? "" : ope.getExtraccionsByIdOperacion().get(0).getCantidad()%>
                <%= ope.getTransferenciasByIdOperacion().isEmpty() ? "" : ope.getTransferenciasByIdOperacion().get(0).getCantidad()%></td>
            <td><%=ope.getFecha().toString()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
