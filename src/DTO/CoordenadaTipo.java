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

/**
 * Clase CoordenadaTipo que se encarga de leer un archivo CSV y cargar los tipos
 * de coordenadas en una tabla de la base de datos.
 */
public class CoordenadaTipo {

    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db"; // Cambia esto al URL de tu base de
                                                                                 // datos

    /**
     * Método principal que realiza la lectura del archivo CSV y carga los tipos de
     * coordenadas en la base de datos.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        String csvFile = "src\\Coordenadas\\RealDilan.csv"; // Cambia esto a la ruta de tu archivo CSV
        String line;

        // Utilizar un conjunto (Set) para evitar duplicados
        Set<String> coordenadaTipos = new HashSet<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 3) { // Verifica que haya suficientes campos en la línea del CSV
                    String coordenadaTipo = data[2]; // Obtener el CoordenadaTipo (según la posición en el CSV)
                    coordenadaTipos.add(coordenadaTipo); // Agregar a conjunto para evitar duplicados
                }
            }

            // Insertar los CoordenadaTipos en la tabla DR_COORDENADATIPO
            String insertCoordenadaTipoSQL = "INSERT INTO DR_COORDENADATIPO (Tipo) VALUES (?)";
            PreparedStatement preparedStatementCoordenadaTipo = conn.prepareStatement(insertCoordenadaTipoSQL);

            for (String tipo : coordenadaTipos) {
                preparedStatementCoordenadaTipo.setString(1, tipo);
                preparedStatementCoordenadaTipo.executeUpdate();
            }

            System.out.println(
                    "Los datos de CoordenadaTipo se han insertado correctamente en la tabla DR_COORDENADATIPO.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}