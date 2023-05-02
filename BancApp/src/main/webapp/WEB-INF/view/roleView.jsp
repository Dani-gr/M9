<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.RolEntity" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 24/04/2023
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RolEntity> roles = (List<RolEntity>) request.getAttribute("roles");
%>
<html>
<head>
    <title>Roles</title>
</head>
<body>
    <h1>Roles</h1>

<%  for(RolEntity r : roles) {%>
    <%= r.getIdRol() %> :  <%= r.getNombre() %>
<%
    }
%>
</body>
</html>
