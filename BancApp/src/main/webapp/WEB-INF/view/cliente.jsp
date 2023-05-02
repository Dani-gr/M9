<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Transferencia</title>
</head>
<body>

<h1>Datos de la transferencia:</h1>
<form:form action="/operacion/guardar" modelAttribute="cliente" method="post">
    <form:hidden path="id_cliente"/>
    Cantidad: <input type="text" id="cantidad"> <br/>
    Moneda actual: <form:input path="origen" size="30"  maxlength="30"/> euros <br/>
    Moneda a la que desea cambiar: <form:input path="destino" size="30"  maxlength="30"/> euros <br/>
    <form:button>Realizar cambio de divisa</form:button>
</form:form>
</body>