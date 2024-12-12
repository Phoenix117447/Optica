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
import java.sql.SQLException;

@WebServlet(name = "RegistrarConsultaMedica", urlPatterns = {"/RegistrarConsultaMedica"})
public class RegistrarConsultaMedica extends HttpServlet {

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos enviados en el body de la solicitud
        String cedulaUsuario = request.getParameter("cedula_usuario");
        String fechaHora = request.getParameter("fecha_hora");

        try {
            // Crear la conexión a la base de datos
            ConexionBD conexionBD = new ConexionBD();
            Connection connection = conexionBD.getConnection();

            // Insertar la consulta en la base de datos
            String sql = "INSERT INTO consultamedica (cedula_usuario, fecha_hora) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(cedulaUsuario));
                statement.setString(2, fechaHora);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().write("{\"success\": true}");
                } else {
                    response.getWriter().write("{\"success\": false}");
                }
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de base de datos: " + e.getMessage());
        }
    }
}

