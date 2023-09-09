
/**
 * Esta es la clase principal de la aplicación.
 * Se encarga de coordinar la ejecución de diferentes partes del programa.
 */
import BussinesLogic.Tablas;
import DTO.Arsenal;
import DTO.CoordenadaTipo;
import DTO.Coordenadas;
import DTO.Horarios;
import UserInterface.loggin;

public class App {
    private final static String DR_CEDULA = "17507418934";
    private final static String DR_NOMBRECOMPLETO = "DILAN ELIAS REAL ANGULO";

    /**
     * El método principal de la aplicación.
     *
     * @param args Los argumentos de línea de comandos.
     * @throws Exception Si ocurre una excepción.
     */
    public static void main(String[] args) throws Exception {
        // Ejecuta el método main de la clase Tablas para inicializar las tablas de la
        // base de datos.
        Tablas.main(args);

        // Ejecuta el método main de la clase loggin para manejar el inicio de sesión.
        loggin.main(args);

        // Pausa la ejecución por 3 segundos.
        Thread.sleep(3000);

        // Continuar con el resto del código después de la pausa
        System.out.println("\n La pausa ha terminado. Continuando...");
        System.out.println("\n Developer-Nombre :" + DR_NOMBRECOMPLETO);
        System.out.println("\n Developer-CEDULA :" + DR_CEDULA);

        System.out.println("\n [+] Leyendo:");
        System.out.println("\n\t -Coordenada...");
        Thread.sleep(1000);
        System.out.println("\n\t -CoordenadaTipo...");
        Thread.sleep(1000);
        System.out.println("\n\t -Arsenal...");
        Thread.sleep(1000);
        System.out.println("\n\t -Horarios...");
        Thread.sleep(1000);
        System.out.println("\n [+] Guardando.");
        System.out.println("\n\t -Coordenada...");
        Thread.sleep(1000);

        // Ejecuta el método main de las clases Coordenadas, CoordenadaTipo, Arsenal y
        // Horarios para procesar los datos.
        Coordenadas.main(args);
        System.out.println("\n\t -CoordenadaTipo...");
        Thread.sleep(1000);
        CoordenadaTipo.main(args);
        System.out.println("\n\t -Arsenal...");
        Thread.sleep(1000);
        Arsenal.main(args);
        System.out.println("\n\t -Horarios...");
        Thread.sleep(1000);
        Horarios.main(args);
    }
}