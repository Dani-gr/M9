<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
%>
<html>
<head>
    <title>Area Asistente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-white">
<div class="container">
    <jsp:include page="cabecera.jsp"/>
</div>
<hr>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-12">
            <nav>
                <h1>Lista de Chats</h1>
                <c:if test="<%=usuario.getClienteByCliente()==null%>">
                    <form action="busquedaChatsPorNombre">
                        <input type="text" id="nombre" name="nombre" placeholder="Buscar por cliente...">
                        <input type="submit" value="Buscar">
                    </form>
                </c:if>
                <form action="filtrarPorActivo">
                    Filtro:
                    <select name="filtro" id="filtro">
                        <option value="Abiertos">Selección: Sólo Abiertos</option>
                        <option value="Cerrados">Selección: Sólo Cerrados</option>
                        <option value="OrdenPrimeroAbiertos">Orden: Primero Abiertos</option>
                        <option value="OrdenPrimeroCerrados">Orden: Primero Cerrados</option>
                        <option value="OrdenAlfabeticoAsistente">Orden: Asistente Alfabetico</option>
                        <option value="Reset">Reiniciar</option>
                    </select>
                    <input type="submit" value="Filtrar">
                </form>
            </nav>
            <table class="table table-striped table-info">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Asistente Asignado</th>
                    <th>Cliente</th>
                    <th>Ult. mensaje</th>
                    <th>Estado</th>
                    <th></th>
                    <c:if test="<%=usuario.getClienteByCliente()!=null%>">
                        <th></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${chats}" var="chat">
                    <tr>
                        <td class="table-plus">${chat.id}</td>
                        <td>${chat.usuarioByAsistenteId.primerNombre} ${chat.usuarioByAsistenteId.primerApellido}</td>
                        <td>
                                ${chat.clienteByClienteIdCliente.usuariosByIdCliente.get(0).primerNombre}
                                ${chat.clienteByClienteIdCliente.usuariosByIdCliente.get(0).primerApellido}
                        </td>
                        <td>Pendiente de hacer</td>
                        <c:if test="${chat.activo == 1}">
                            <td>Abierto</td>
                            <td><a href="detallesChat/${chat.id}" class="btn btn-secondary">Entrar</a></td>
                            <c:if test="<%=usuario.getClienteByCliente()!=null%>">
                                <td><a href="/chats/cerrarChat/${chat.id}" class="btn btn-warning">Cerrar asistencia</a></td>
                            </c:if>
                        </c:if>
                        <c:if test="${chat.activo == 0}">
                            <td>Cerrado</td>
                            <td><a href="detallesChat/${chat.id}" class="btn btn-secondary">Entrar</a></td>
                            <c:if test="<%=usuario.getClienteByCliente()!=null%>">
                                <td></td>
                            </c:if>
                        </c:if>


                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
