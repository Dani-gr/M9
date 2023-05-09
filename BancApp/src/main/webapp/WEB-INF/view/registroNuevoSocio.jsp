<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar nuevo socio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="bg-gradient bg-dark">
<div class="card text-start w-50" style="margin: 5% auto auto;">
    <div class="card-body">
        <h4>Datos del nuevo asociado:</h4> <br>
        Rol en la empresa:
        <label for="socio" class="form-label">socio</label>
        <input id="socio" type="radio" name="rol" value="socio" checked>
        <label for="autorizado" class="form-label">autorizado</label>
        <input id="autorizado" type="radio" name="rol" value="autorizado">
        </br>
        <label for="userNIF" class="form-label">ID/NIF</label>
        <input type="text" id="userNIF" name="userNIF" class="form-control"
               value="" maxlength="9" minlength="9" size="9"/>
        <br/>

        <label for="userPNombre" class="form-label">Primer nombre</label>
        <input type="text" id="userPNombre" name="userNombre" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="userSNombre" class="form-label">Segundo nombre</label>
        <input type="text" id="userSNombre" name="userNombreSegundo" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="userPApellido" class="form-label">Primer apellido</label>
        <input type="text" id="userPApellido" name="userApellidoPrimero" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="userSApellido" class="form-label">Segundo apellido</label>
        <input type="text" id="userSApellido" name="userApellidoSegundo" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="userFechaNacimiento" class="form-label">Fecha de nacimiento</label>
        <input type="date" id="userFechaNacimiento" name="userFechaNacimiento" class="form-control"
               value=""/>
        <br/>

        <label for="userEmail" class="form-label">Correo electrónico</label>
        <input type="email" id="userEmail" name="userEmail" placeholder="hello@example.taw" class="form-control"
               value=""/>
        <br/>

        <label for="password" class="form-label">Contraseña</label>
        <input type="password" id="password" name="userPassword" class="form-control"/>
        <br/>

        Dirección:

        <br>
        <label for="direccionCalle" class="form-label">Calle</label>
        <input type="text" id="direccionCalle" name="direccionCalle" class="form-control"
               value="" maxlength="30" minlength="1" size="30"/>
        <br/>

        <label for="direccionNumero" class="form-label">Número</label>
        <input type="text" id="direccionNumero" name="direccionNumero" class="form-control"
               value="" maxlength="10" minlength="1" size="10"/>
        <br/>

        <label for="direccionPlanta" class="form-label">Planta/Puerta/Oficina</label>
        <input type="text" id="direccionPlanta" name="direccionPlanta" class="form-control"
               value="" maxlength="30" minlength="1" size="30"/>
        <br/>

        <label for="direccionCiudad" class="form-label">Ciudad</label>
        <input type="text" id="direccionCiudad" name="direccionCiudad" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="direccionRegion" class="form-label">Región</label>
        <input type="text" id="direccionRegion" name="direccionRegion" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="direccionPais" class="form-label">País</label>
        <input type="text" id="direccionPais" name="direccionPais" class="form-control"
               value="" maxlength="20" minlength="1" size="20"/>
        <br/>

        <label for="direccionCodPostal" class="form-label">Código Postal</label>
        <input type="text" id="direccionCodPostal" name="direccionPostal" class="form-control"
               maxlength="5" minlength="5" size="5"/>
        <br/>

        <button name="btnRegistro" value="registrarme" class="btn btn-primary">Registrar asociado</button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>