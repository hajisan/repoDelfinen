import java.io.*;
import java.util.ArrayList;

public class FilStyrer {
    private static final String filNavn = "AlleMedlemmer.json"; // Navn på JSON-filen
    private static final String idFileNavn = "id_count.json"; // Navn på JSON-filen

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

    // Læs ID-tælleren fra id_count.json
    public int læsCurrentId() {
        try (FileReader reader = new FileReader(idFileNavn)) {
            idCount idCount = gson.fromJson(reader, idCount.class);
            return idCount.getCurrentId(); // Returnere det akutelle ID
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af ID-fil: " + e.getMessage());
            return 1; // Hvis filen ikke findes, starter vi ID-tælleren fra 1
        }
    }

    // Opdatere ID-tælleren i, id_count.json
    public void opdatereIdCount(int nyId) {
        try (FileWriter writer = new FileWriter(idFileNavn)) {
            idCount idCount = new idCount();
            idCount.setCurrentId(nyId);
            gson.toJson(idCount, writer); // Gem den nye ID-værdi i filen
        } catch (IOException e) {
            System.out.println("Fejl ved opdatering af ID-fil: " + e.getMessage());
        }
    }

    // Genere et unikt ID
    public String genereUnikId() {
        int currentId = læsCurrentId(); // Læs det nuværende ID
        String nyId = String.format("SKD%04d", currentId); // Formatere ID som DSF0001, DSF0002, osv.
        opdatereIdCount(currentId + 1); // Opdatere ID-tælleren
        return nyId; // Returnere det nye ID
    }
    
}
