<%@ page import="es.proyectotaw.banca.bancapp.entity.RolEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 24/04/2023
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Vista roles</title>
</head>
<body>
<%
    @SuppressWarnings("unchecked")
    List<RolEntity> roles = (List<RolEntity>) request.getAttribute("roles");
    for (RolEntity rol : roles) {
%>
<%=rol.getIdrol()%>: <%=rol.getNombre()%>
<%
    }
%>

</body>
</html>
