<!--
Autor: Andres Perez Garcia  Porcentaje: 20%
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.RolusuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="nombresRoles" type="java.util.List<java.lang.String>" scope="session"/>
<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
    boolean bloqueado = !(usuario.getRolusuariosById().stream().map(RolusuarioEntity::getBloqueado).toList().contains((byte) 0));
%>
<html>
<head>
    <title>Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"/>
</head>
<body class="bg-gradient bg-dark">
<jsp:include page="cabeceraMenu.jsp"/>
<div class="m-3">&nbsp;</div>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String"/>
<c:if test="${not null and not empty mensaje}">
    <div class="d-flex justify-content-center">
        <h4 style="color:limegreen"><%=mensaje%>
        </h4>
    </div>
</c:if>
<div class="d-flex justify-content-center">
    <div class="card text-center w-75" style="margin: 5% auto auto;">
        <div class="card-header">
            <jsp:useBean id="menu" scope="session" type="java.lang.String"/>
            <c:choose>
                <c:when test="${\"cajero\".equalsIgnoreCase(menu)}">
                    <div class="row row-cols-lg-3 row-cols-md-2 row-cols-1 justify-content-center align-content-center">
                        <div class="col" style="margin-top: 10px">
                            <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%>"
                                 onclick="window.location.href='/operacion/transferencia'">
                                Transferencia
                            </div>
                        </div>
                        <div class="col" style="margin-top: 10px">
                            <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%>"
                                 onclick="window.location.href='/operacion/cambioDivisa'">
                                Cambio de divisa + extracción
                            </div>
                        </div>
                        <div class="col" style="margin-top: 10px">
                            <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%>"
                                 onclick="window.location.href='/operacion/extraccion'">
                                Extracci&oacute;n
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${nombresRoles.contains(\"gestor\")}">
                            <!-- TODO por el gestor -->
                        </c:when>
                        <c:otherwise>
                            <div class="row row-cols-lg-3 row-cols-md-2 row-cols-1 justify-content-center align-content-center">
                                <div class="col" style="margin-top: 10px">
                                    <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%>"
                                         onclick="window.location.href='/operacion/transferencia'">
                                        Transferencia
                                    </div>
                                </div>
                                <div class="col" style="margin-top: 10px">
                                    <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%>"
                                         onclick="window.location.href='/operacion/cambioDivisa'">
                                        Cambio de divisa
                                    </div>
                                </div>
                            </div>

                            <% if(nombresRoles.contains("socio")){  %>
                            <div class="row">
                                <div class="col pt-3">
                                    <div class="btn btn-lg btn-outline-secondary<%=bloqueado ? " disabled" : ""%> "
                                         onclick="window.location.href='/empresa/'">
                                        Gestión de socios y autorizados
                                    </div>
                                </div>

                            </div>

                            <% } %>

                            <% if((nombresRoles.contains("socio") || nombresRoles.contains("autorizado")) && usuario.getRolusuariosById().get(0).getBloqueado() == 1){  %>
                            <div class="row">
                                <div class="col pt-3">
                                    <div class="btn btn-lg btn-outline-secondary "
                                         onclick="window.location.href='/empresa/solicitarDesbloqueo'">
                                        Solicitar desbloqueo
                                    </div>
                                </div>
                            </div>

                            <% } %>


                        <!--    <%// if(nombresRoles.contains("representante")){  %>
                            <div class="row">
                                <div class="col pt-3">
                                    <div class="btn btn-lg btn-outline-secondary "
                                         onclick="window.location.href='/empresa/addSocios'">
                                        Añadir nuevos socios y autorizados
                                    </div>
                                </div>
                            </div>
                            <%// } %>
                            -->

                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <div class="row m-3"></div>
        </div>
        <c:if test="${\"normal\".equalsIgnoreCase(menu) && !nombresRoles.contains(\"gestor\")}">
            <div class="card-footer">
                <div class="row">
                    <div class="col">
                        <p>¿Necesitas ayuda?</p>
                    </div>
                </div>
                <div class="row mb-3">
                    <form:form action="/chats/solicitudAsistencia" method="post">
                        <div class="col">
                            <input type="hidden" id="usuario" name="usuario" value="<%=usuario.getId()%>">
                            <input type="submit" href="/chats/solicitudAsistencia" class="btn btn-lg btn-outline-secondary" value="Busca chat con un asistente">
                        </div>
                    </form:form>
                    <br/>
                    <form:form action="/chats/" method="get">
                        <div class="col">
                            <input type="hidden" id="cliente" name="cliente" value="<%=usuario.getClienteByCliente().getIdCliente()%>">
                            <input type="submit" class="btn btn-lg btn-outline-secondary" value="Acceder a los chats con asistentes">
                        </div>
                    </form:form>

                    <c:if test="${bloqueado}">
                        <div class="col">
                            <div class="btn btn-lg btn-outline-warning disabled">Solicitar desbloqueo</div>
                            <!-- TODO -->
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>
</div>
<div class="mt-5">

</div>
<div class="mt-5">
    &nbsp;
</div>
<div class="mt-5">
    &nbsp;
</div>
<div class="mt-5">
    &nbsp;
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
