package DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Coordenadas {

    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db"; // Cambia esto al URL de tu base de
                                                                                 // datos

    public static void main(String[] args) {
        String csvFile = "src\\Coordenadas\\RealDilan.csv"; // Cambia esto a la ruta de tu archivo CSV
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