<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    ////Nuria Rodríguez Tortosa 80%
%>
<html>
<head>
    <title>Extraer dinero</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<h1>Datos de la extracción:</h1>
<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<c:if test="${not null and not empty error}">
    <h4 style="color:red"><%=error%>
    </h4>
</c:if>
<%--@elvariable id="extraer" type="es.proyectotaw.banca.bancapp.entity.ExtraccionEntity"--%>
<form:form action="/operacion/guardarExtraccion" modelAttribute="extraer" method="post">
    <form:hidden path="idExtraccion"/>
    <form:hidden path="operacionByOperacion"/>
    Cantidad: <form:input path="cantidad" size="30" maxlength="30" cssClass="form-control"/> euros <br/>
    <form:button>Realizar extracci&oacute;n</form:button>
</form:form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
