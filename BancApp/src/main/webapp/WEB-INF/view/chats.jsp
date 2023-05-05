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
                    <th>Cliente</th>
                    <th>Estado</th>
                    <th>Ultimo mensaje</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${Chats}</td>
                    <td>Chat 1</td>
                    <td>Hola, ¿cómo estás?</td>
                    <td>a</td>
                </tr>
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
