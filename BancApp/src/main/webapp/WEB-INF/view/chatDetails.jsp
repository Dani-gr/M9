<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andil
  Date: 06/05/2023
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body>
<div class="header">Chat con ${chat.clienteByClienteIdCliente.usuariosByIdCliente.get(0).primerNombre}</div>
<div class="container" style="text-align: right;">
    <c:forEach items="${chat.mensajesById}" var="mensaje">
        <h4 style="margin-bottom:0px;">${mensaje.usuarioByEmisor.primerNombre}: ${mensaje.contenido}</h4>
        <p>${mensaje.fechaHora}</p>
    </c:forEach>
</div>
<div class="container" style="text-align: right;">
    <c:if test="${chat.activo == 1}">
        <form action="/chats/crearMensaje" method="post">
            <input type="hidden" id="idUsuario" name="idUsuario" value="${chat.usuarioByAsistenteId.id}">
            <input type="hidden" id="idChat" name="idChat" value="${chat.id}">
            <input type="text" id="mensaje" name="mensaje" placeholder="Escriba aqui su mensaje...">
            <input type="submit" value="Enviar">
        </form>
    </c:if>
    <c:if test="${chat.activo == 0}">
        <div class = "container">
            <h4>No puedes enviar mensajes. El chat esta cerrado.</h4>
        </div>
    </c:if>
</div>


</body>
</html>
