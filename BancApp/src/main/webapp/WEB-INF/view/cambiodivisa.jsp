<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Cambio de divisa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"/>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<h1>Datos del cambio de divisa:</h1>
<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<c:if test="${not null and not empty error}">
    <h4 style="color:red"><%=error%></h4>
</c:if>
<%--@elvariable id="cambioDivisa" type="es.proyectotaw.banca.bancapp.entity.CambDivisaEntity"--%>
<form:form action="/operacion/guardarDivisa" modelAttribute="cambioDivisa" method="post">
    <form:hidden path="idDivisa"/>
    <form:hidden path="operacionByOperacion"/>
    Cantidad: <form:input path="cantidad" cssClass="form-control"/>
    Moneda actual: <form:input path="origen" size="30"  maxlength="30" cssClass="form-control"/> euros <br/>
    Moneda a la que desea cambiar: <form:input path="destino" size="30"  maxlength="30" cssClass="form-control"/> euros <br/>
    <form:button>Realizar cambio de divisa</form:button>
</form:form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>