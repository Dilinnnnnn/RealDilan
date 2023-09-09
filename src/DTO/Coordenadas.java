package DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase Coordenadas que se encarga de leer un archivo CSV y cargar los datos de
 * geoposición en una tabla de la base de datos.
 */
public class Coordenadas {

    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db";

    /**
     * Método principal que realiza la lectura del archivo CSV y carga los datos de
     * geoposición en la base de datos.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        String csvFile = "src\\Coordenadas\\RealDilan.csv";
        String line;

        try (Connection conn = DriverManager.getConnection(DB_URL);
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String insertCoordenadaSQL = "INSERT INTO DR_COORDENADA (Geoposicion) VALUES (?)";
            PreparedStatement preparedStatementCoordenada = conn.prepareStatement(insertCoordenadaSQL);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 2) { // Verifica que haya suficientes campos en la línea del CSV
                    String geoposicion = data[1]; // Obtener la Geoposicion (según la posición en el CSV)

                    // Insertar la Geoposicion en la tabla DR_COORDENADA
                    preparedStatementCoordenada.setString(1, geoposicion);
                    preparedStatementCoordenada.executeUpdate();
                }
            }

            System.out.println("Los datos de Geoposicion se han insertado correctamente en la tabla DR_COORDENADA.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}