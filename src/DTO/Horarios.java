package DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Horarios {

    private static final String DB_URL = "jdbc:sqlite:database\\drBaseDatos.db"; // Cambia esto al URL de tu base de
                                                                                 // datos

    public static void main(String[] args) {
        String csvFile = "src\\Coordenadas\\RealDilan.csv"; // Cambia esto a la ruta de tu archivo CSV
        String line;

        try (Connection conn = DriverManager.getConnection(DB_URL);
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String insertHorarioSQL = "INSERT INTO DR_HORARIOS (Dia, HoraInicio, HoraFin) VALUES (?, ?, ?)";
            PreparedStatement preparedStatementHorario = conn.prepareStatement(insertHorarioSQL);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 9) { // Verifica que haya suficientes campos en la línea del CSV
                    String diaHora = data[3]; // Obtener el valor de "Dia" y "HoraInicio-HoraFin" (según la posición en
                                              // el CSV)

                    // Separar el valor de "Dia" y "HoraInicio-HoraFin" por el carácter "-"
                    String[] diaHoraSplit = diaHora.split("-");
                    if (diaHoraSplit.length == 2) {
                        String dia = diaHoraSplit[0].trim();
                        String[] horaSplit = diaHoraSplit[1].trim().split(" "); // Separar HoraInicio y HoraFin
                        if (horaSplit.length == 2) {
                            String horaInicio = horaSplit[0].trim();
                            String horaFin = horaSplit[1].trim();

                            // Insertar los valores en la tabla DR_HORARIOS
                            preparedStatementHorario.setString(1, dia);
                            preparedStatementHorario.setString(2, horaInicio);
                            preparedStatementHorario.setString(3, horaFin);
                            preparedStatementHorario.executeUpdate();
                        }
                    }
                }
            }

            System.out.println("Los datos de Horarios se han insertado correctamente en la tabla DR_HORARIOS.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}