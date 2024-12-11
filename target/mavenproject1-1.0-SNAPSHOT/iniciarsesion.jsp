<%-- 
    Document   : iniciarsesion
    Created on : 9/12/2024, 1:16:43 p. m.
    Author     : Arley Mantilla
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <!-- Enlace a Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
          rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
          crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header text-center bg-primary text-white">
                        <h3>Iniciar Sesión</h3>
                    </div>
                    <div class="card-body">
                        <form action="IniciarSesion" method="POST">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre de Usuario</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese su nombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="cedula" class="form-label">Contraseña (Cédula)</label>
                                <input type="password" class="form-control" id="cedula" name="cedula" placeholder="Ingrese su cédula" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Ingresar</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        ¿No tienes una cuenta? <a href="registrarse.jsp" class="text-primary">Regístrate aquí</a>.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Scripts opcionales de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-wh2J6o7tW34Wp3mHkYdU7u9lA+au2J/eQuglg9lYBvhZPqJeImniBJRpMghgf1X" 
            crossorigin="anonymous"></script>
</body>
</html>
