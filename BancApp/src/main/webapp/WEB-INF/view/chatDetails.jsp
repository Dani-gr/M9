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
    <title>Chat con Andres</title>
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
<div class="container">
    <c:forEach items="${chat.mensajesById}" var="mensaje">
        <h4>Mensaje de ${mensaje.usuarioByEmisor.primerNombre}: ${mensaje.contenido}</h4>
    </c:forEach>
</div>


</body>
</html>
