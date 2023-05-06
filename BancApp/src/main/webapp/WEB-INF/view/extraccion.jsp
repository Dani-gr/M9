<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Extraer dinero</title>
</head>
<body>
<h1>Datos de la extracción:</h1>
<%--@elvariable id="Extraer" type="es.proyectotaw.banca.bancapp.entity.ExtraccionEntity"--%>
<form:form action="/operacion/guardarExtraccion" modelAttribute="Extraer" method="post">
    <form:hidden path="operacion"/>
    <form:hidden path="operacionByOperacion"/>
    Cantidad: <form:input path="cantidad" size="30" maxlength="30"/> euros <br/>
    <form:button>Realizar extracci&oacute;n</form:button>
</form:form>
</body>
</html>
