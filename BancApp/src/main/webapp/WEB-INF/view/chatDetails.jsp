<!--
Autor: Andres Perez Garcia
-->
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

%>
<html>
<head>
    <title>Chat</title>
    <style>
        .header {
            background-color: #dcdcdc;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            color: #333333;
        }

        .container {
            width: 80%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #d7e9f7;
            padding: 20px;
            box-sizing: border-box;
        }

    </style>
</head>
<jsp:include page="cabecera.jsp"/>
<body>
<div class="header">Chat de asistencia</div>
<div class="container" style="text-align: right;">
    <c:forEach items="${mensajes}" var="mensaje">
        <c:if test="${mensaje.usuarioByEmisor.clienteByCliente == null}">
            <!-- NO ES CLIENTE, POR LO TANTO ES ASISTENTE -->
            <div style="text-align: right">
                <h4 style="margin-bottom: 0px; margin-top: 0px;">${mensaje.usuarioByEmisor.primerNombre}: ${mensaje.contenido}</h4>
                <p style="margin-top: 0px;">${mensaje.fechaHora}</p>
            </div>
        </c:if>
        <c:if test="${mensaje.usuarioByEmisor.clienteByCliente != null}">
            <!-- ES CLIENTE -->
            <div style="text-align: left">
                <h4 style="text-align: left; margin-bottom: 0px; margin-top: 0px">${mensaje.usuarioByEmisor.primerNombre}: ${mensaje.contenido}</h4>
                <p style="margin-top: 0px">${mensaje.fechaHora}</p>
            </div>
        </c:if>

    </c:forEach>
</div>
<div class="container" style="text-align: right;">
    <c:if test="${chat.activo == 1 && (usuario.getId()==chat.usuarioByAsistenteId.id || usuario.getClienteByCliente().getIdCliente()==chat.clienteByClienteIdCliente.idCliente)}">
        <form action="/chats/crearMensaje" method="post">
            <!-- AQUI HAY QUE HACER 2 CASOS (CLIENTE Y ASISTENTE) -->
            <c:if test="<%=usuario.getClienteByCliente()==null%>">
                <!-- NO ES CLIENTE, POR LO TANTO ES ASISTENTE -->
                <input type="hidden" id="idUsuario" name="idUsuario" value="${chat.usuarioByAsistenteId.id}">
                <input type="hidden" id="rol" name="rol" value="Asistente">
            </c:if>
            <c:if test="<%=usuario.getClienteByCliente()!=null%>">
                <input type="hidden" id="idUsuario" name="idUsuario"
                       value="${chat.clienteByClienteIdCliente.idCliente}">
                <input type="hidden" id="rol" name="rol" value="Cliente">
            </c:if>
            <input type="hidden" id="idChat" name="idChat" value="${chat.id}">
            <input type="text" id="mensaje" name="mensaje" placeholder="Escriba aqui su mensaje..." required>
            <input type="submit" value="Enviar">
        </form>
    </c:if>
    <c:if test="${chat.activo == 0}">
        <div class="container">
            <h4>No puedes enviar mensajes. El chat esta cerrado.</h4>
        </div>
    </c:if>
</div>


</body>
</html>
