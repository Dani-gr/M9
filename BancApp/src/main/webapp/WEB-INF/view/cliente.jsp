<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<!--<jsp:include page="cabecera.jsp"/>-->
<div class="m-3">&nbsp;</div>
<div class="d-flex justify-content-center">
    <div class="card text-center w-75" style="margin: 5% auto auto;">
        <div class="card-header">
            <div class="row mt-3">
                <h1>Perfil y operaciones realizadas</h1>
                <!-- TODO Agregar bean del modelAttribute -->
                <form action="/registro" method="post" class="text-start">
                    <label for="userNIF" class="form-label">ID/NIF</label>
                    <input type="text" id="userNIF" name="userNIF" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userPNombre" class="form-label">Primer nombre</label>
                    <input type="text" id="userPNombre" name="userNombre" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userSNombre" class="form-label">Segundo nombre</label>
                    <input type="text" id="userSNombre" name="userNombreSegundo" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userPApellido" class="form-label">Primer apellido</label>
                    <input type="text" id="userPApellido" name="userApellidoPrimero" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userSApellido" class="form-label">Segundo apellido</label>
                    <input type="text" id="userSApellido" name="userApellidoSegundo" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userFechaNacimiento" class="form-label">Fecha de nacimiento</label>
                    <input type="date" id="userFechaNacimiento" name="userFechaNacimiento" class="form-control"
                           value=""/>
                    <br/>

                    <label for="userEmail" class="form-label">Correo electr&oacute;nico</label>
                    <input type="email" id="userEmail" name="userEmail" placeholder="hello@example.taw" class="form-control"
                           value=""/>
                    <br/>

                    <label for="password" class="form-label">Contrase&ntilde;a</label>
                    <input type="password" id="password" name="userPassword" class="form-control"/>
                    <br/>

                    Dirección: <br>
                    <label for="direccionCalle" class="form-label">Calle</label>
                    <input type="test" id="direccionCalle" name="direccionCalle" class="form-control"/>
                    <br/>

                    <label for="direccionNumero" class="form-label">Número</label>
                    <input type="test" id="direccionNumero" name="direccionNumero" class="form-control"/>
                    <br/>

                    <label for="direccionPlanta" class="form-label">Planta/Puerta/Oficina</label>
                    <input type="test" id="direccionPlanta" name="direccionPlanta" class="form-control"/>
                    <br/>

                    <label for="direccionCiudad" class="form-label">Ciudad</label>
                    <input type="test" id="direccionCiudad" name="direccionCiudad" class="form-control"/>
                    <br/>

                    <label for="direccionRegion" class="form-label">Región</label>
                    <input type="test" id="direccionRegion" name="direccionRegion" class="form-control"/>
                    <br/>

                    <label for="direccionPais" class="form-label">País</label>
                    <input type="test" id="direccionPais" name="direccoinPais" class="form-control"/>
                    <br/>

                    <label for="direccionCodPostal" class="form-label">Código Postal</label>
                    <input type="test" id="direccionCodPostal" name="direccionPostal" class="form-control"/>
                    <br/>
                </form>
            </div>
        </div>
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
