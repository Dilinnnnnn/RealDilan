package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Framework.AppException;

public abstract class SqliteDataHelper {

    private static String DBPathConnection = "jdbc:C:\\RealDilan\\database\\drBaseDatos.db";
    private static Connection conn = null;

    public SqliteDataHelper() {
    }

    public static synchronized Connection openConnection() throws AppException {
        try {
            if (conn == null)
                conn = DriverManager.getConnection(DBPathConnection);
        } catch (SQLException e) {
            throw new AppException(e, "SQLiteDataHelper", "Fallo la conexion a la base de datos");
        }
        return conn;
    }

    protected static void closeConnection() throws AppException {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new AppException(e, "SQLiteDataHelper", "Fallo la conexión con la base de datos");
        }
    }
}