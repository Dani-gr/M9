<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Lista de Chats</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Asistente Asignado</th>
                    <th>Cliente</th>
                    <th>Estado</th>
                    <th>Ultimo mensaje</th>
                    <th></th>
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
                        <c:if test="${chat.activo == 1}">
                            <td>Abierto</td>
                        </c:if>
                        <c:if test="${chat.activo == 0}">
                            <td>Cerrado</td>
                        </c:if>
                        <td>Pendiente de hacer</td>
                        <td><a href="detallesChat/${chat.id}" class="btn btn-secondary">Entrar</a></td>
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
