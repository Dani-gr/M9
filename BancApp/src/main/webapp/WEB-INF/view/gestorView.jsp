<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.ClienteEntity" %>
<%@ page import="es.proyectotaw.banca.bancapp.entity.DireccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
    <body>

        <div class="d-flex justify-content-end">
            <span class="mr-2"> Gestor autenticado: <!-- request.getAttribute("usuario") --> </span> -->
            <a class="btn btn-danger" href="/">SALIR</a>
        </div>

        <hr>

        <h1 style="text-align: center">SISTEMA DE GESTIÓN DEL BANCO</h1>

        <hr>

        <div class="container-md">
            <h3>OPERACIONES DE GESTIÓN</h3>

            <table>
                <tr>
                    <td>
                        <a href="/gestor/solicitudesAlta" class="btn btn-success">SOLICITUDES DE ALTA DE CLIENTE</a>
                        <a href="/gestor/cuentasInactivas" class="btn btn-success">CUENTAS SIN ACTIVIDAD EN 30 DÍAS</a>
                        <a href="" class="btn btn-success">SOLICITUDES DE DESBLOQUEO EN EMPRESAS</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="" class="btn btn-success">SOLICITUDES DE DESBLOQUEO DE CUENTAS</a>
                        <a href="/gestor/seguridadTransferencias" class="btn btn-success">SEGURIDAD DE LAS CUENTAS</a>
                    </td>
                </tr>
            </table>
        </div>

        <br><hr>

        <div class="container-md">
            <h3>RELACIÓN DE CLIENTES DEL BANCO</h3>

            <table>
                <tr>
                    FILTROS PARA VISUALIZAR CLIENTES
                    <form:form method="post" action="/gestor/filtrar" modelAttribute="filtroClientes">

                    </form:form>
                </tr>
            </table>

            <table class="table table-success table-striped">
                <tr>
                    <th>ID CLIENTE</th>
                    <th>DIRECCIÓN FISCAL</th>
                    <th>INFORMACIÓN</th>
                </tr>

                <%
                    List<ClienteEntity> clientes = (List<ClienteEntity>) request.getAttribute("clientes");

                    for(ClienteEntity c : clientes){ %>
                <tr>
                    <td> <%= c.getIdCliente() %> </td>

                    <% DireccionEntity d = c.getDireccionByDireccion(); %>
                    <td> <%= d.getCiudad() + " " + d.getPais() + " " + d.getCodpostal() %> </td>

                    <td>
                        <%
                            boolean esEmpresa = c.getEmpresaByIdCliente() != null;
                        %>
                        <%= (esEmpresa) ?
                                "<a class=\"btn btn-dark\" href='/gestor/empresa?id=" + c.getIdCliente() + "'>VER EMPRESA</a>" :
                                "<a class=\"btn btn-secondary\" href='/gestor/particular?id=" + c.getIdCliente() + "'>VER PARTICULAR</a>"
                        %>
                    </td>

                </tr>
                <%  }
                %>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
