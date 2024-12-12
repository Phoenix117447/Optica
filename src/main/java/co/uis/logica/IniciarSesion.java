/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uis.logica;

import co.uis.persistencia.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");

        if (nombre == null || cedula == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre y cédula son obligatorios.");
            return;
        }

        try {
            // Crear conexión a la base de datos
            ConexionBD conexionBD = new ConexionBD();
            Connection connection = conexionBD.getConnection();

            // Consulta SQL para verificar si el usuario existe
            String sql = "SELECT nombre, cedula FROM usuarios WHERE nombre = ? AND cedula = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setInt(2, Integer.parseInt(cedula));

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // Si el nombre es "Andres Ruiz" y la cédula es "23456780", se considera trabajador
                    if ("Andres Ruiz".equals(nombre) && "23456780".equals(cedula)) {
                        // Guardar la cédula del usuario en la sesión
                        request.getSession().setAttribute("cedulaUsuario", cedula);

                        // Redirigir al trabajador
                        response.sendRedirect("trabajador.html");
                    } else {
                        // Guardar la cédula del usuario en la sesión
                        request.getSession().setAttribute("cedulaUsuario", cedula);

                        // Redirigir al usuario común
                        response.sendRedirect("usuario.html");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de base de datos: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La cédula debe ser un número válido.");
        }
    }
}

