<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cuentas sin actividad</title>
</head>
<body>

    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <h1>CUENTAS SIN MOVIMIENTOS</h1>

    <%
        List<CuentaEntity> inactivas = (List<CuentaEntity>) request.getAttribute("inactivas");

        if(inactivas.isEmpty()){
    %>
            <h3 style="color: darkred"> NO HAY CUENTAS INACTIVAS </h3>
    <%
        }else{
    %>
        <table border="1">
            <tr>
                <th>
                    NÚMERO DE CUENTA
                </th>
                <th>
                    ID DE CLIENTE
                </th> <th>ESTADO</th>
                <th></th>

            </tr>
            <%
                for(CuentaEntity c : inactivas){
            %>
            <tr>

                    <td> <%=c.getNumCuenta()%> </td>
                    <td> <%=c.getClienteByCliente().getIdCliente()%> </td>

                    <td> <%=(c.getActiva() == 1)? "Activa" : "Bloqueada"%> </td>
                    <td>
                        <%=
                            (c.getActiva() == 1)?
                                "<a href=\"\" >BLOQUEAR</a>" : ""
                        %>
                    </td>
            </tr>
            <%  }%>


        </table>
    <%
        }
    %>

</body>
</html>
