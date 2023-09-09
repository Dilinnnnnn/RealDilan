/**
 * La clase `loggin` proporciona un mecanismo simple de inicio de sesión con encriptación de contraseñas
 * y control de intentos de inicio de sesión.
 */
package UserInterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class loggin {
    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db";
    private static final int DR_MAX_LOGIN_ATTEMPTS = 3;

    /**
     * El método principal de la clase `loggin`.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // Establecer conexión a la base de datos SQLite
            Connection conn = DriverManager.getConnection(DB_URL);

            // Crear tabla si no existe
            String createTableSQL = "CREATE TABLE IF NOT EXISTS DR_USER (" +
                    "UserId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NameUser VARCHAR(30) NOT NULL," +
                    "PasswordUser VARCHAR(32) NOT NULL)"; // MD5 tiene una longitud de 32 caracteres
            conn.createStatement().execute(createTableSQL);

            Scanner scanner = new Scanner(System.in);
            int drloginAttempts = 0;

            while (drloginAttempts < DR_MAX_LOGIN_ATTEMPTS) {
                System.out.print("\n Nombre de usuario: ");
                String username = scanner.nextLine();
                System.out.print("\n Contraseña: ");
                String password = scanner.nextLine();

                boolean loginSuccessful = drloginUser(conn, username, password);

                if (loginSuccessful) {
                    System.out.println("Inicio de sesión exitoso.");
                    // Coloca aquí el código que deseas ejecutar después de iniciar sesión
                    // exitosamente.
                    break; // Sale del bucle si el inicio de sesión es exitoso
                } else {
                    System.out.println(
                            "Inicio de sesión fallido. Intento " + (drloginAttempts + 1) + " de "
                                    + DR_MAX_LOGIN_ATTEMPTS);
                    drloginAttempts++;
                }
            }

            if (drloginAttempts >= DR_MAX_LOGIN_ATTEMPTS) {
                System.out.println("Has excedido el número máximo de intentos de inicio de sesión.");
            }

            conn.close();
            scanner.close();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para iniciar sesión en la aplicación.
     *
     * @param conn     La conexión a la base de datos.
     * @param username El nombre de usuario.
     * @param password La contraseña proporcionada.
     * @return `true` si el inicio de sesión es exitoso, `false` en caso contrario.
     * @throws SQLException             Si ocurre un error de SQL.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo de
     *                                  encriptación.
     */
    private static boolean drloginUser(Connection conn, String username, String password)
            throws SQLException, NoSuchAlgorithmException {
        // Encriptar la contraseña proporcionada para compararla con la almacenada en la
        // base de datos
        String encryptedPassword = drencryptPassword(password);
        // System.out.println(encryptedPassword);

        // Consultar la base de datos para verificar las credenciales
        String selectUserSQL = "SELECT * FROM DR_USER WHERE NameUser = ? AND PasswordUser = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(selectUserSQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, encryptedPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int userId = resultSet.getInt("UserId");

            // Insertar un registro en la tabla de registros de inicio de sesión
            String insertLoginLogSQL = "INSERT INTO DR_LOGGIN (UserId) VALUES (?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertLoginLogSQL);
            insertStatement.setInt(1, userId);
            insertStatement.executeUpdate();

            return true; // Inicio de sesión exitoso
        }

        return false; // Credenciales incorrectas
    }

    /**
     * Método para encriptar la contraseña en MD5.
     *
     * @param password La contraseña a encriptar.
     * @return La contraseña encriptada en formato hexadecimal.
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo de
     *                                  encriptación.
     */
    private static String drencryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();

        // Convertir el hash MD5 a una representación hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));

        }

        return hexString.toString();
    }
}