<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Transferencia</title>
</head>
<body class="bg-gradient bg-dark">
<div class="card text-center w-50" style="margin: 5% auto auto;">
    <div class="card-body">
        <h1 class="card-title" style="color:mediumblue;">Datos de la transferencia:</h1>
        <div class="card-footer">
            <%--@elvariable id="transferenciaARealizar" type="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity"--%>
            <form:form action="/operacion/guardarTransferencia" modelAttribute="transferenciaARealizar" method="post">
                <form:hidden path="idTransferencia"></form:hidden>
                <form:hidden path="operacionByOperacion"></form:hidden>
                Cuenta destino: <form:input path="cuentaByCuentaDestino" size="30" maxlength="30"/><br/>
                Si es una cuenta externa, ponga el IBAN: <form:input path="ibanDestino" size="30" maxlength="30"/>
                Cantidad: <form:input path="cantidad" size="10" maxlength="10"/> euros <br/>
                <form:button>Guardar</form:button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
