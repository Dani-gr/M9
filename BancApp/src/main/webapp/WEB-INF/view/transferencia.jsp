<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Transferencia</title>
</head>
<body>

<h1>Datos de la transferencia:</h1>
<form:form action="/operacion/guardar" modelAttribute="transferenciaARealizar" method="post">
    <form:hidden path="operacion"/>
    Cuenta destino: <form:input path="cuenta_destino" size="30" maxlength="30" /><br/>
    Cantidad: <form:input path="cantidad" size="10"  maxlength="10"/> euros <br/>
    <form:button>Guardar</form:button>
</form:form>
</body>
</html>
