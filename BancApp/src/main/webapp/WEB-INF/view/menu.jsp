<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: nurir
  Date:01/05/2023
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getAttribute("usuario");
%>

<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1 style="align-content: center">BancApp</h1>

<button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-primary">
    <a href="/cliente/perfil=idUsuario=<%=usuario.getId()%>">Ver perfil y operaciones</a>
</button>

<button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-primary"
><a href="/operacion/transferencia?cliente=<%=cliente.getCuentasByIdCliente()%>">Transferencia</a>
</button>

<button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-primary"
        onclick="location.href = 'cambiodivisa.jsp';"> Cambio de divisa
</button>

<button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-primary"
        onclick="location.href = 'cajero.java';"> Cajero
</button>

<p>¿Necesitas ayuda?</p>
<button style="background: transparent; outline: none; border: 0; width: 60px;" class="btn btn-primary"
        onclick="location.href = 'Asistente.java';"> Buscar chat con un asistente
</button><!--Aquí habrá que hacer otra cosa porque habrá que pasa el cliente-->


</body>
</html>
