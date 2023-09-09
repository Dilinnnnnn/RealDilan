package DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class Horarios {
    private static final String DB_URL = "jdbc:sqlite:C:\\RealDilan\\database\\drBaseDatos.db";

    public static void main(String[] args) {
        String csvFile = "C:\\RealDilan\\src\\Coordenadas\\RealDilan.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
                Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT INTO DR_HORARIOS (Dia, Hora) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);

            // Mapear los nombres de los días en el CSV a sus valores correspondientes
            Map<String, String> diaMapping = new HashMap<>();
            diaMapping.put("Lunes", "Monday");
            diaMapping.put("Martes", "Tuesday");
            diaMapping.put("Miercoles", "Wednesday");
            diaMapping.put("Jueves", "Thursday");
            diaMapping.put("Viernes", "Friday");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 9) {
                    String coordenada = data[1];
                    String coordenadaTipo = data[2];
                    String tipoArsenal = data[8];

                    for (int i = 3; i <= 7; i++) {
                        String dia = diaMapping.get(data[i]);
                        String hora = data[8];

                        if (dia != null && !hora.isEmpty()) {
                            preparedStatement.setString(1, dia);
                            preparedStatement.setString(2, hora);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
            }

            System.out.println("Los datos se han insertado correctamente en la tabla DR_HORARIOS.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}