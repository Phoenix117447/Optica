<%-- 
    Document   : registrarse
    Created on : 9/12/2024, 1:17:01 p. m.
    Author     : Arley Mantilla
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
          rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
          crossorigin="anonymous">
    <script>
        function validarFormulario() {
            const correo = document.getElementById("correo").value;
            const telefono = document.getElementById("telefono").value;
            const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const regexTelefono = /^\d{10}$/;

            let errores = "";

            if (!regexCorreo.test(correo)) {
                errores += "- El correo electrónico no tiene un formato válido.\n";
            }
            if (!regexTelefono.test(telefono)) {
                errores += "- El número telefónico debe ser de 10 dígitos.\n";
            }

            if (errores) {
                alert("Errores encontrados:\n" + errores);
                return false; // Impide el envío del formulario
            }

            return true; // Permite el envío del formulario
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Registro de Usuario</h1>
        <div class="row mt-4">
            <div class="col-12 col-md-8 offset-md-2">
                <form action="RegistrarUsuario" method="post" onsubmit="return validarFormulario()">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre y Primer Apellido</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="cedula" class="form-label">Cédula</label>
                        <input type="text" class="form-control" id="cedula" name="cedula" required>
                    </div>
                    <div class="mb-3">
                        <label for="correo" class="form-label">Correo Electrónico</label>
                        <input type="email" class="form-control" id="correo" name="correo" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" required>
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-success">Registrar</button>
                        <button type="reset" class="btn btn-secondary">Limpiar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-w76A6e+PQA81i6ZnnFStDGFvj0bg59qHrf41Pg3QA2DvtmIvRFaNk/8zBvrew60a" 
            crossorigin="anonymous"></script>
</body>
</html>
