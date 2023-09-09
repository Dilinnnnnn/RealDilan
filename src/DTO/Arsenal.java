package DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Arsenal {

    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db"; // Cambia esto al URL de tu base de
                                                                                 // datos

    public static void main(String[] args) {
        String csvFile = "src\\Coordenadas\\RealDilan.csv"; // Cambia esto a la ruta de tu archivo CSV
        String line;

        // Utilizar un conjunto (Set) para evitar duplicados
        Set<String> arsenalTipos = new HashSet<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 9) { // Verifica que haya suficientes campos en la línea del CSV
                    String tipoArsenal = data[8]; // Obtener el TipoArsenal (según la posición en el CSV)
                    arsenalTipos.add(tipoArsenal); // Agregar a conjunto para evitar duplicados
                }
            }

            // Insertar los TipoArsenal en la tabla DR_ARSENAL
            String insertArsenalSQL = "INSERT INTO DR_ARSENAL (ArsenalTipo) VALUES (?)";
            PreparedStatement preparedStatementArsenal = conn.prepareStatement(insertArsenalSQL);

            for (String tipo : arsenalTipos) {
                preparedStatementArsenal.setString(1, tipo);
                preparedStatementArsenal.executeUpdate();
            }

            System.out.println("Los datos de ArsenalTipo se han insertado correctamente en la tabla DR_ARSENAL.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}