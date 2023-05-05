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
        Gestor: Usuario

        <h1 style="text-align: center">SISTEMA DE GESTIÓN DEL BANCO</h1>

        <hr>

        <div class=".container-md">
            <h3>RELACIÓN DE CLIENTES DEL BANCO</h3>

            <table class="table table-success table-striped">
                <tr>
                    <th>ID CLIENTE</th>
                    <th>DIRECCIÓN FISCAL</th>
                    <th>TIPO</th>
                    <th></th>
                </tr>

                <%
                    List<ClienteEntity> clientes = (List<ClienteEntity>) request.getAttribute("clientes");

                    for(ClienteEntity c : clientes){ %>
                <tr>
                    <td> <%= c.getIdCliente() %> </td>
                    <% DireccionEntity d = c.getDireccionByDireccion(); %>
                    <td> <%= d.getCiudad() + " " + d.getPais() + " " + d.getCodpostal() %> </td>
                    <td> <%= (c.getEmpresaByIdCliente() != null)?
                            "empresa con CIF = " + c.getEmpresaByIdCliente().getCif()
                            :   "particular con NIF = " + c.getUsuariosByIdCliente().get(0).getNif()
                    %> </td>
                    <td> <a href="/gestor/cliente?id=<%=c.getIdCliente()%>" >VER CLIENTE</a> </td>
                </tr>
                <%  }
                %>
            </table>
        </div>

        <h3>OPERACIONES DE GESTIÓN</h3>

        <table border="1">
            <tr>
                <td><a href="" class="btn btn-success">SOLICITUDES DE ALTA DE CLIENTE</a></td>
            </tr>
            <tr>
                <td><a href="" class="btn btn-success">ACTIVIDAD DE LAS CUENTAS</a></td>
            </tr>
            <tr>
                <td><a href="" class="btn btn-success">SEGURIDAD DE LAS CUENTAS</a></td>
            </tr>
        </table>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
