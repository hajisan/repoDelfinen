<import com.google.gson.Gson;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FilStyrer {

    private static final String filNavn = "AlleMedlemmer.csv"; // Navn på csv-filen



    /*
     Læser alle medlemmer fra CSV-filen.
     Formatet i filen skal være: Navn,Fødselsdato,Medlemskategori
     returnerer En ArrayList af Medlem-objekter læst fra filen
     */

    public static ArrayList<Medlem> læsAlleMedlemmer() {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        File file = new File(filNavn);


        // Tjekker om filen eksisterer
        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer;
        }

        // Læser fra filen
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true; // Spring header-linjen over

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Spring header-linje over
                }

                // Split linjen i datafelter
                String[] parts = line.split(",");
                if (parts.length < 3) { // Sørger for at alle linjer er gyldige
                    System.out.println("Ugyldig linje i CSV: " + line);
                    continue;
                }

                try {
                    // Læs data fra CSV-linjen
                    String navn = parts[0].trim();
                    LocalDate fødselsdato = KonsolHandler.stringToLocalDate(parts[1].trim());
                    String medlemskategori = parts[2].trim();

                    // Opret Medlem-objekt
                    Medlem medlem = new Medlem(navn, fødselsdato, medlemskategori);
                    medlemmer.add(medlem);
                } catch (IllegalArgumentException e) {
                    System.out.println("Fejl i linje: " + line + ". " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return medlemmer;
    }

    /*
     Gemmer alle medlemmer til CSV-filen
     Laver en ArrayList af Medlem-objekter, der skal gemmes
     */
    public void gemAlleMedlemmer(ArrayList<Medlem> medlemmer) {
        File file = new File(filNavn);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Skriv header-linje
            writer.write("Navn,Fødselsdato,Medlemskategori");
            writer.newLine();

            // Skriv hver medlemsdata til filen
            for (Medlem medlem : medlemmer) {
                writer.write(medlem.getNavn() + "," +
                        KonsolHandler.LocalDateToString(medlem.getFødselsdato()) + "," +
                        medlem.getMedlemstypeEnum());
                writer.newLine();
            }

            System.out.println("Data er gemt til filen: " + filNavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    /*
    Tilføjer et nyt medlem til CSV-filen.
    Et nytMedlem Medlem-objekt, der skal tilføjes
     */
    public void tilføjMedlem(Medlem nytMedlem) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs eksisterende medlemmer
        medlemmer.add(nytMedlem); // Tilføj det nye medlem
        gemAlleMedlemmer(medlemmer); // Gem alle medlemmer tilbage til filen
        System.out.println("Medlem tilføjet og gemt.");
    }

    /*
     Sletter et medlem fra CSV-filen baseret på dets navn.
     Finder eksisterendeMedlem Medlem-objekt, der skal slettes
     */
    public void sletMedlem(Medlem eksisterendeMedlem) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs eksisterende medlemmer

        // Forsøg at fjerne medlemmet
        if (medlemmer.removeIf(m -> m.getNavn().equalsIgnoreCase(eksisterendeMedlem.getNavn()))) {
            gemAlleMedlemmer(medlemmer); // Gem ændringer til filen
            System.out.println("Medlemmet er slettet og listen er blevet opdateret.");
        } else {
            System.out.println("Medlemmet blev ikke fundet.");
        }
    }


    /*
    Redigerer oplysninger om et medlem baseret på dets navn.
    */
    public void redigerMedlem() {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs eksisterende medlemmer
        Scanner sc = new Scanner(System.in);

        System.out.println("Indtast navnet på medlemmet, du vil redigere:");
        String input = sc.nextLine();
        boolean medlemFundet = false;

        for (Medlem medlem : medlemmer) {
            if (medlem.getNavn().equalsIgnoreCase(input)) {
                medlemFundet = true;
                boolean isEditing = true;

                while (isEditing) {
                    System.out.println("Hvad vil du redigere?");
                    System.out.println("1. Navn");
                    System.out.println("2. Medlemskategori");
                    System.out.println("3. Fødselsdato");
                    System.out.println("4. Afslut redigering");
                    int valg = sc.nextInt();
                    sc.nextLine(); // Ryd scanner

                    switch (valg) {
                        case 1:
                            System.out.println("Indtast nyt navn:");
                            medlem.setNavn(sc.nextLine());
                            break;
                        case 2:
                            System.out.println("Indtast ny medlemskategori (aktiv/passiv):");
                            String nyKategori = sc.nextLine();
                            medlem.setMedlemsKategori(Enum.valueOf(Medlemstyper.class, nyKategori.toUpperCase()));
                            break;
                        case 3:
                            System.out.println("Indtast ny fødselsdato (dd/mm/yyyy):");
                            medlem.setFødselsdato(KonsolHandler.stringToLocalDate(sc.nextLine()));
                            break;
                        case 4:
                            isEditing = false;
                            break;
                        default:
                            System.out.println("Ugyldigt valg. Prøv igen.");
                    }
                }
                break;
            }
        }

        if (medlemFundet) {
            gemAlleMedlemmer(medlemmer); // Gem ændringer
            System.out.println("Ændringer gemt.");
        } else {
            System.out.println("Ingen medlemmer fundet med det angivne navn.");
        }
    }

    /* public void registrerStævneSvømmetid(String medlemNavn, Enum<disciplinNavne> disciplinNavn, Duration tid, LocalDate dato, String lokalitet) {
         ArrayList<Medlem> medlemmer = læsAlleMedlemmer();

         for (Medlem medlem : medlemmer) {
             if (medlem.getNavn().equals(medlemNavn)) {
                 Svømmedisciplin svømmedisciplin;

                 switch (disciplinNavn) {
                     case disciplinNavne.RYGCRAWL:
                         svømmedisciplin = new Svømmedisciplin() {
                         };
                         break;
                     case "Butterfly":
                         svømmedisciplin = new Butterfly();
                         break;
                     case "Crawl":
                         svømmedisciplin = new Crawl();
                         break;
                     case "Brystsvømning":
                         svømmedisciplin = new Brystsvømning();
                         break;
                     default:
                         System.out.println("Ugyldig disciplin!");
                         return;
                 }

                 svømmedisciplin.registrerStævneTid(medlem, tid, dato, lokalitet);
                 gemAlleMedlemmer(medlemmer);
                 System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                 return;
             }
         }
         System.out.println("Medlem med ID " + medlemNavn + " blev ikke fundet.");
     }
 */
    public void registrerTræningsSvømmetid(String medlemNavn, String disciplin, Duration tid, LocalDate dato) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();

        for (Medlem medlem : medlemmer) {
            if (medlem.getNavn().equals(medlemNavn)) {
                Svømmedisciplin svømmedisciplin;

                switch (disciplin) {
                    case "Rygcrawl":
                        svømmedisciplin = new Rygcrawl();
                        break;
                    case "Butterfly":
                        svømmedisciplin = new Butterfly();
                        break;
                    case "Crawl":
                        svømmedisciplin = new Crawl();
                        break;
                    case "Brystsvømning":
                        svømmedisciplin = new Brystsvømning();
                        break;
                    default:
                        System.out.println("Ugyldig disciplin!");
                        return;
                }

                svømmedisciplin.registrerTræningsTid(medlem, tid, dato);
                gemAlleMedlemmer(medlemmer);
                System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                return;
            }
        }
        System.out.println("Medlem med Navn " + medlemNavn + " blev ikke fundet.");
    }

    /*
     Initialiserer CSV-filen, hvis den ikke allerede eksisterer.
     */
    public void initialiserFil() {
        File file = new File(filNavn);

        // Opret filen, hvis den ikke eksisterer
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Filen blev oprettet: " + filNavn);
                }
            } catch (IOException e) {
                System.out.println("Fejl ved oprettelse af fil: " + e.getMessage());
            }
        }
    }

}

