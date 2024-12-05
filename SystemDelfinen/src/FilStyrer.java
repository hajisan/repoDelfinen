import java.io.*;
import java.util.ArrayList;

public class FilStyrer {
    private static final String filNavn = "AlleMedlemmer.json"; // Navn på JSON-filen

    /**
     * Læser JSON-filen som en String og konverterer det til en liste over Medlem-objekter.
     * Returnere ArrayList af Medlem-objekter.
     */
    public ArrayList<String> læsAlleMedlemmer() {
        ArrayList<String> medlemmer = new ArrayList<>();
        File file = new File(filNavn);

        // Tjek om filen eksisterer
        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer; // Returner tom liste
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                medlemmer.add(line.trim()); // Tilføj hver linje til listen
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return medlemmer;
    }

    /**
     * Gemmer en liste over medlemmer i JSON-filen.
     * Medlemmer ArrayList af medlemmer som strings.
     */
    public void gemAlleMedlemmer(ArrayList<String> medlemmer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn))) {
            for (String medlem : medlemmer) {
                writer.write(medlem);
                writer.newLine();
            }
            System.out.println("Data er gemt til filen: " + filNavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    /**
     * Initialiserer filen med en tom struktur, hvis den ikke allerede findes.
     */
    public void initialiserFil() {
        File file = new File(filNavn);

        // Tjek om filen allerede findes
        if (!file.exists()) {
            try {
                file.createNewFile(); // Opret filen
                System.out.println("Filen blev oprettet: " + filNavn);
            } catch (IOException e) {
                System.out.println("Fejl ved oprettelse af fil: " + e.getMessage());
            }
        }
    }
}
