
import BussinesLogic.queryi;
import DTO.Arsenal;
import DTO.CoordenadaTipo;
import DTO.Coordenadas;
import DTO.Horarios;
import UserInterface.loggin;

public class App {
    private final static String DR_CEDULA = "17507418934";
    private final static String DR_NOMBRECOMPLETO = "DILAN ELIAS REAL ANGULO";

    public static void main(String[] args) throws Exception {
        queryi.main(args);
        loggin.main(args);
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
