package BussinesLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TablaGeneral {
    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        String dbUrl = "jdbc:sqlite:C:\\RealDilan\\database\\drBaseDatos.db";
        Connection conn = null;

        try {
            // Establece la conexión a la base de datos
            conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();

            // Consulta SQL para crear la tabla TablaGeneral
            String createTableQuery = "CREATE TABLE IF NOT EXISTS TablaGeneral (" +
                    "Usuario VARCHAR(30)," +
                    "Coordenada VARCHAR(30)," +
                    "Tipo VARCHAR(30)," +
                    "Arsenal VARCHAR(30)," +
                    "Dia VARCHAR(30))";

            // Ejecuta la consulta de creación de la tabla
            stmt.execute(createTableQuery);

            // Consulta SQL para insertar los datos en la tabla TablaGeneral
            String insertDataQuery = "INSERT INTO TablaGeneral (Usuario, Coordenada, Tipo, Arsenal, Dia) " +
                    "SELECT C.usuarioId AS Usuario, C.Geoposicion AS Coordenada, CT.Tipo AS Tipo, " +
                    "A.ArsenalTipo AS Arsenal, H.Dia AS Dia " +
                    "FROM DR_COORDENADA C " +
                    "LEFT JOIN DR_COORDENADATIPO CT ON C.usuarioId = CT.Id " +
                    "LEFT JOIN DR_ARSENAL A ON CT.Id = A.Id " +
                    "LEFT JOIN DR_HORARIOS H ON A.Id = H.Id";

            stmt.execute(insertDataQuery);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra la conexión
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}