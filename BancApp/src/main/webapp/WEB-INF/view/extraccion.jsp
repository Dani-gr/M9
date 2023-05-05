<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nurir
  Date: 04/05/2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Extraer dinero</title>
</head>
<body>
<h1>Datos de la extracción:</h1>
<%--@elvariable id="cambioDivisa" type="es.proyectotaw.banca.bancapp.entity.CambDivisaEntity"--%>
<form:form action="/operacion/guardarExtraccion" modelAttribute="Extraer" method="post">
  <form:hidden path="operacion"/>
  <form:hidden path="operacionByOperacion"></form:hidden>
  Cantidad: <form:input path="cantidad" size="30"  maxlength="30"/> euros <br/>
  <form:button>Realizar extracci&oacute;n</form:button>
</form:form>
</body>
</body>
</html>
