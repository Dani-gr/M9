<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Transferencia</title>
</head>
<body>

<h1>Datos de la transferencia:</h1>
<%--@elvariable id="transferenciaARealizar" type="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity"--%>
<form:form action="/operacion/guardarTransferencia" modelAttribute="transferenciaARealizar" method="post">
    <form:hidden path="operacion"/>
    <form:hidden path="operacionByOperacion"/>
    Cuenta destino: <form:input path="cuentaByCuentaDestino" size="30" maxlength="30"/><br/>
    Si es una cuenta externa, ponga el IBAN: <form:input path="ibanDestino" size="30" maxlength="30"/>
    Cantidad: <form:input path="cantidad" size="10" maxlength="10"/> euros <br/>
    <form:button>Guardar</form:button>
</form:form>
</body>
</html>
