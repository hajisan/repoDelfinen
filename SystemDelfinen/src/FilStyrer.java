import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;

/*GSOn er et java bibliotek, der gør det nemt at arbejde i json fil.
toJson konvertere objekter til skrift der kan lagres. hovedpointen med vi
bruger gson. Modsatte gør sig så gældende for fromJson

 */

public class FilStyrer {
    private static final String filNavn = "AlleMedlemmer.json"; // Navn på JSON-filen, da Allemedlemmer ligger i root,
    //skal der ikke være en filsti angivet.

    /*
     Læser JSON-filen som en String og konverterer det til en liste over Medlem-objekter.
     Returnerer ArrayList af Medlem-objekter.
     */
    public ArrayList<Medlem> læsAlleMedlemmer() {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        File file = new File(filNavn);

        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer; // Returnerer en tom liste
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson(); //opretter en instans af GSON,
            Medlem[] medlemmerArray = gson.fromJson(reader, Medlem[].class); // Læs JSON som array. Her opretter vi
            //medlemsobjekter ved at læse vores fil, og får instantieret dem og kørt ind i en arrayliste
            for (Medlem medlem : medlemmerArray) {
                medlemmer.add(medlem); // Konverter til ArrayList, igennem et For-each loop
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return medlemmer;
    }

    /*
     Gemmer en liste over medlemmer i JSON-filen.
     Medlemmer ArrayList af medlemmer som strings.
     */
    public void gemAlleMedlemmer(ArrayList<Medlem> medlemmer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn))) {
            Gson gson = new Gson();
            String json = gson.toJson(medlemmer); //her laver den medlemmer om til en string der kan lagres i json.
            writer.write(json); // her skriver BufferedReader så stringen in i filen, AlleMedlemmer.json
            System.out.println("Data er gemt til filen: " + filNavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    /*
      Tilføjer et nyt medlem til JSON-filen.
       nytMedlem Medlem-objekt, der skal tilføjes.
     */
    public void tilføjMedlem(Medlem nytMedlem) {
        // Læs eksisterende medlemmer
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();
        //Vi opretter arraylistene gennem den anden metode fra før,
        // Tilføjer det nye medlem til listen
        medlemmer.add(nytMedlem);

        // Gem opdateret liste tilbage i filen, altså smider vi det hele tilbage i allemedlemmer.json igen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn))) {
            Gson gson = new Gson();
            String json = gson.toJson(medlemmer); //kalder biblioteket, så den indbygget funktion toJson, for de nye
            writer.write(json);
            System.out.println("Medlem tilføjet og gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved tilføjelse af medlem: " + e.getMessage());
        }
    }

    /*
     her skaber vi bare filen med en tom struktur, hvis den ikke findes.
     */
    public void initialiserFil() {
        File file = new File(filNavn);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Filen blev oprettet: " + filNavn);
            } catch (IOException e) {
                System.out.println("Fejl ved oprettelse af fil: " + e.getMessage());
            }
        }
    }
}
