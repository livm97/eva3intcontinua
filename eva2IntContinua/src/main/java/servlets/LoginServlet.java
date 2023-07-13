package servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Obtener la conexión a la base de datos
        Connection conexion = ConfiguracionBD.obtenerConexion();

     // Consulta SQL para verificar la autenticación del usuario
        String consulta = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Autenticación exitosa
                // Realiza las acciones correspondientes, como permitir el acceso al usuario
                response.sendRedirect("inicioSesionExitoso.html");
            } else {
            	
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        } 

        finally {
            // Cerrar la conexión a la base de datos
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

