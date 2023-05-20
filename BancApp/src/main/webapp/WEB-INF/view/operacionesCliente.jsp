<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Hibernate" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    //@author Nuria Rodríguez Tortosa 100%
    @SuppressWarnings("unchecked")
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
    //UsuarioEntity usuarioOpe = (UsuarioEntity) request.getAttribute("usuario");

    List<CambDivisaEntity> cambios = (List<CambDivisaEntity>) request.getAttribute("cambios");
    List<TransferenciaEntity> transs = (List<TransferenciaEntity>) request.getAttribute("transs");
    List<ExtraccionEntity> extras = (List<ExtraccionEntity>) request.getAttribute("extras");
%>
<html>
<head>
    <title>Operaciones cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabecera.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-75" style="margin: 5% auto auto;">
    <%--@elvariable id="filtro" type="es.proyectotaw.banca.bancapp.ui.FiltroOperaciones"--%>
    <form:form action="/cliente/filtrar" method="post" modelAttribute="filtro">
    Buscar por: <br/>
    Nombre de operación:
    <form:select multiple="false" path="nombreOperacion">
        <form:option value="ninguno" label="------"/>
        <form:option value="Transferencia" label="Transferencia"/>
        <form:option value="Cambio de divisa" label="Cambio de divisa"/>
        <form:option value="Extraccion" label="Extraccion"/>
    </form:select>
    Cantidad >=:
        <form:input path="cantidadFiltro"/>
    <button>Filtrar</button>
    </form:form>

    <%--@elvariable id="ordenar" type="es.proyectotaw.banca.bancapp.ui.OrdenarOperaciones"--%>
    <form:form action="/cliente/ordenar" method="post" modelAttribute="ordenar">
    Ordenar por: <br/>
    Cantidad
        <form:checkbox path="cantidad"/>
    <button>Ordenar</button>
    </form:form>

    <h3>Lista de operaciones</h3>

    <table class="table p-3" border="1">
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
            int i = 1;
            if (!cambios.isEmpty()) {
                for (CambDivisaEntity cambio : cambios) {
        %>
        <tr>
            <td><%=i%>
            <td><%=cambio.nombre()%>
            </td>
            <td><%=cambio.getCantidad()%>
            <td><%=cambio.getOperacionByOperacion().getFecha()%>
        </tr>
        <%
                    i++;
                }
            }
        %>
        <%
            if (!transs.isEmpty()) {
                for (TransferenciaEntity trans : transs) {
        %>
        <tr>
            <td><%=i%>
            <td><%=trans.nombre()%>
            </td>
            <td><%=trans.getCantidad()%>
            <td><%=trans.getOperacionByOperacion().getFecha()%>
        </tr>
        <%
                    i++;
                }
            }
        %>
        <%
            if (!extras.isEmpty()) {
                for (ExtraccionEntity extra : extras) {
        %>
        <tr>
            <td><%=i%>
            <td><%=extra.nombre()%>
            </td>
            <td><%=extra.getCantidad()%>
            </td>
            <td><%=extra.getOperacionByOperacion().getFecha()%>
            </td>
        </tr>
        <%
                    i++;
                }
            }
        %>
        </tbody>
    </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</body>
</html>
