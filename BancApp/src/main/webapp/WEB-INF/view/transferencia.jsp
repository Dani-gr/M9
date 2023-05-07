<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    //Nuria Rodríguez Tortosa 80%
%>
<html>
<head>
    <title>Transferencia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"/>
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabecera.jsp"/>
<div class="card text-center w-50" style="margin: 5% auto auto;">
    <div class="card-body">
        <h1 class="card-title" style="color:mediumblue;">Datos de la transferencia:</h1>
        <div class="card-footer">
            <jsp:useBean id="error" scope="request" type="java.lang.String"/>
            <c:if test="${not null and not empty error}">
                <h4 style="color:red"><%=error%></h4>
            </c:if>
            <%--@elvariable id="transferenciaARealizar" type="es.proyectotaw.banca.bancapp.entity.TransferenciaEntity"--%>
            <form:form action="/operacion/guardarTransferencia" modelAttribute="transferenciaARealizar" method="post">
                <form:hidden path="idTransferencia"/>
                <form:hidden path="operacionByOperacion"/>
                <form:hidden path="cuentaByCuentaDestino"/>
                Cuenta destino: <form:input path="ibanDestino" size="30" maxlength="30" cssClass="form-control"/>
                Cantidad: <form:input path="cantidad" size="10" maxlength="10" cssClass="form-control"/> euros <br/>
                <form:button cssClass="btn btn-primary">Guardar</form:button>
            </form:form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
