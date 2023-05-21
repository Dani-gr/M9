<%@ page import="es.proyectotaw.banca.bancapp.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.OperacionEntity" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cuentas sin actividad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

    <!-- Carlos Castaño Moreno -->
    <jsp:include page="cabeceraGestor.jsp"></jsp:include>

    <br><br><br><br>

    <div class="container">
        <h1>CUENTAS SIN MOVIMIENTOS EN 30 DÍAS</h1>

        <%
            List<CuentaEntity> inactivas = (List<CuentaEntity>) request.getAttribute("inactivas");

            if(inactivas.isEmpty()){
        %>
        <h3 style="color: darkred"> NO HAY CUENTAS INACTIVAS </h3>
        <%
        }else{
        %>
        <table class="table table-striped">
            <tr>
                <th>
                    NÚMERO DE CUENTA
                </th>
                <th>
                    ID DE CLIENTE
                </th> <th>ESTADO</th>
                <th>FECHA DE LA ÚLTIMA OPERACIÓN</th>
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
                    <%
                        OperacionEntity op = c.getOperacionsByNumCuenta().stream()
                                .max(Comparator.comparing(OperacionEntity::getFecha))
                                .orElse(null);

                        Date date = op.getFecha();

                        LocalDate fecha = date.toLocalDate();
                        LocalDate fechaActual = LocalDate.now();
                        long dias = ChronoUnit.DAYS.between(fecha, fechaActual);

                    %>
                    <%= date + " - hace " + dias + " días" %>

                </td>
                <td>
                    <%=
                    (c.getActiva() == 1)?
                            "<a class=\"btn btn-danger\" href=\"/gestor/bloquearInactiva?id=" + c.getNumCuenta() + "\" >BLOQUEAR</a>" : ""
                    %>
                </td>
            </tr>
            <%  }%>


        </table>
        <%
            }
        %>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
