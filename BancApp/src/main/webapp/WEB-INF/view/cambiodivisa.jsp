<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Transferencia</title>
</head>
<body>

<h1>Datos de la transferencia:</h1>
<%--@elvariable id="cambioDivisa" type="es.proyectotaw.banca.bancapp.entity.CambDivisaEntity"--%>
<form:form action="/operacion/guardarDivisa" modelAttribute="cambioDivisa" method="post">
    <form:hidden path="operacion"/>
    <form:hidden path="operacionByOperacion"></form:hidden>
    Cantidad: <<form:input path="cantidad"></form:input>
    Moneda actual: <form:input path="origen" size="30"  maxlength="30"/> euros <br/>
    Moneda a la que desea cambiar: <form:input path="destino" size="30"  maxlength="30"/> euros <br/>
    <form:button>Realizar cambio de divisa</form:button>
</form:form>
</body>