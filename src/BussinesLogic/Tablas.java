package BussinesLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Tablas {
        public static void main(String[] args) {
                // Cambia la ruta de la base de datos según tu configuración
                String dbUrl = "jdbc:sqlite:C:\\RealDilan\\database\\drBaseDatos.db";

                try {
                        // Establece la conexión a la base de datos
                        Connection conn = DriverManager.getConnection(dbUrl);

                        // Crea un objeto Statement para ejecutar los queries
                        Statement statement = conn.createStatement();

                        // Query para crear la tabla DR_USER y agregar registros
                        String createUserTable = "CREATE TABLE IF NOT EXISTS DR_USER ("
                                        + "UserId INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "NameUser VARCHAR(30) NOT NULL, "
                                        + "PasswordUser VARCHAR(8) NOT NULL)";

                        String insertUsers = "INSERT INTO DR_USER (NameUser, PasswordUser) VALUES "
                                        + "('Profe', '827ccb0eea8a706c4c34a16891f84e7b'), "
                                        + "('dilan.real@epn.edu.ec', '0e688dfd210714513f78bf3e807bf0dd'), "
                                        + "('Alberto23@gmail.com', 'c44a471bd78cc6c2fea32b9fe028d30a')";

                        // Query para crear la tabla DR_LOGGIN
                        String createLogginTable = "CREATE TABLE IF NOT EXISTS DR_LOGGIN ("
                                        + "LoginLogId INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "UserId INTEGER NOT NULL, "
                                        + "LoginTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                                        + "FOREIGN KEY(UserId) REFERENCES DR_USER(UserId))";

                        // Query para crear la tabla DR_COORDENADA
                        String createCoordenadaTable = "CREATE TABLE IF NOT EXISTS DR_COORDENADA ("
                                        + "usuarioId INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "Geoposicion VARCHAR(30) NOT NULL)";

                        // Query para crear la tabla DR_COORDENADATIPO
                        String createCoordenadaTipoTable = "CREATE TABLE IF NOT EXISTS DR_COORDENADATIPO ("
                                        + "Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "Tipo VARCHAR(30) NOT NULL)";

                        // Query para crear la tabla DR_ARSENAL
                        String createArsenalTable = "CREATE TABLE IF NOT EXISTS DR_ARSENAL ("
                                        + "Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "ArsenalTipo VARCHAR(30) NOT NULL)";

                        // Query para crear la tabla DR_HORARIOS
                        String createHorariosTable = "CREATE TABLE IF NOT EXISTS DR_HORARIOS ("
                                        + "Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + "Dia VARCHAR(30) NOT NULL, "
                                        + "Hora VARCHAR(30) NOT NULL)";

                        // Ejecuta los queries
                        statement.execute(createUserTable);
                        statement.execute(insertUsers);
                        statement.execute(createLogginTable);
                        statement.execute(createCoordenadaTable);
                        statement.execute(createCoordenadaTipoTable);
                        statement.execute(createArsenalTable);
                        statement.execute(createHorariosTable);

                        // Cierra la conexión
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}