import Controller.KonsolHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Opretter en instans af Controller.KonsolHandler for at håndtere systeminteraktion
        KonsolHandler konsolHandler = new KonsolHandler();

        // Starter hovedmenuen
        try {
            konsolHandler.startMenu();
        } catch (IOException e) {
            System.out.println("Filen findes ikke: " + e.getMessage());
        }
    }
}
