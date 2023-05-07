<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Datos de la empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabecera.jsp"/>
<div class="m-3">&nbsp;</div>
<div class="card text-center w-50" style="margin: 5% auto auto;">
    <div class="card-body">
        <h4 class="card-title">Mis datos</h4>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success">${mensaje}</div>
        </c:if>

        <%--@elvariable id="empresa" type="es.proyectotaw.banca.bancapp.entity.EmpresaEntity"--%>
        <form:form action="/empresa/guardar" modelAttribute="empresa" method="post">
            <form:hidden path="idEmpresa"/>
            CIF: <form:input path="cif" size="9" maxlength="9"/><br/><br/>
            <h5>Dirección</h5>
            <form:hidden path="clienteByCliente.idCliente"/>
            <form:hidden path="clienteByCliente.direccionByDireccion.id"/>
            Calle: <form:input path="clienteByCliente.direccionByDireccion.calle" size="50" maxlength="50"/><br/><br/>
            Número: <form:input path="clienteByCliente.direccionByDireccion.numero" size="2" maxlength="2"/><br/><br/>
            Planta/Puerta/Oficina: <form:input path="clienteByCliente.direccionByDireccion.plantaPuertaOficina" size="2" maxlength="2"/><br/><br/>
            Ciudad: <form:input path="clienteByCliente.direccionByDireccion.ciudad" size="30" maxlength="30"/><br/><br/>
            Región: <form:input path="clienteByCliente.direccionByDireccion.region" size="30" maxlength="30"/><br/><br/>
            País: <form:input path="clienteByCliente.direccionByDireccion.pais" size="30" maxlength="30"/><br/><br/>
            Código Postal: <form:input path="clienteByCliente.direccionByDireccion.codpostal" size="30" maxlength="30"/><br/><br/>
            <form:button class="btn btn-primary" id="liveAlertBtn">Guardar</form:button>
        </form:form>
    </div>


    <!--form:form action="/datosUsuario/guardar" modelAttribute="usuario" method="post"-->
    <!--form:hidden path="customerId"/-->
    <!--/form:form-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</body>
</html>