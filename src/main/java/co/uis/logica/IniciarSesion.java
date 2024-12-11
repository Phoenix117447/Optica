/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uis.logica;

import co.uis.persistencia.ConexionBD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");

        // Validar los parámetros
        if (nombre == null || cedula == null || nombre.isEmpty() || cedula.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        try {
            // Convertir la cédula a long
            long cedulaLong = Long.parseLong(cedula);

            // Crear instancia de la clase ConexionBD para obtener la conexión
            ConexionBD conexionBD = new ConexionBD();
            Connection connection = conexionBD.getConnection();

            // Consulta SQL para verificar las credenciales
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND cedula = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setLong(2, cedulaLong);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Credenciales válidas - Redirigir a Usuario.html
                        response.sendRedirect("Usuario.html");
                    } else {
                        // Credenciales inválidas
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales incorrectas.");
                    }
                }
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de base de datos: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La cédula debe ser un número válido.");
        } finally {
            // Cerrar la conexión
            ConexionBD conexionBD = new ConexionBD();
            conexionBD.cerrarConexion();
        }
    }
}
