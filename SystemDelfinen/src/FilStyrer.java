import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/*GSOn er et java bibliotek, der gør det nemt at arbejde i json fil.
toJson konvertere objekter til skrift der kan lagres. hovedpointen med vi
bruger gson. Modsatte gør sig så gældende for fromJson

 */

public class FilStyrer {
    private static final String filNavn = "AlleMedlemmer.json"; // Navn på JSON-filen
    private static final String idFilNavn = "id_count.json"; // Navn på JSON-filen


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

    public void sletMedlem(Medlem eksisterendeMedlem) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); //læser eksisterende medlemmer
        System.out.println("Medlem: " + "'" + eksisterendeMedlem + "'" + " bliver slettet");
        medlemmer.remove(eksisterendeMedlem); // indbygget ArrayList metode til at fjerne medlem
        gemAlleMedlemmer(medlemmer); //her kalder vi metoden "gemAlleMedlemmer", så vi får en opdateret liste.
        System.out.println("Medlemmet er slettet og listen er blevet opdateret");
    }


    public void redigerMedlem() {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        for (int i = 0; i < medlemmer.size(); i++) {
            Medlem medlem = medlemmer.get(i);
            //iterrer gennem listen af medlemmer
            if (medlem.getNavn().equalsIgnoreCase(input) || medlem.getID().equals(input)) {

                boolean isEditing = true;

                while (isEditing) {
                    // Vis valgmuligheder for redigering
                    System.out.println("Hvad vil du redigere?");
                    System.out.println("1. Navn");
                    System.out.println("2. Medlemskategori");
                    System.out.println("3. Fødselsdato");
                    System.out.println("4. Afslut redigering");
                    int valg = sc.nextInt();
                    sc.nextLine(); // Rens scanner
                    System.out.println("Hvad vil du med: " + medlem + "'s profil?");
                    System.out.println();
                    switch (sc.nextInt()) {

                        case 1:
                            System.out.println("Hvad skal det nye fødselsår være på dit " + medlem);
                            medlem.setFødselsdato(KonsolHandler.stringToLocalDate(sc.nextLine()));

                        case 2:
                            System.out.println("Indtast ny medlemskategori (AktivJunior, AktivSenior, PassivtMedlem):");
                            String nyKategori = sc.nextLine();

                            // Behold gamle værdier
                            String id = medlem.getID();
                            String navn = medlem.getNavn();
                            LocalDate fødselsdato = medlem.getFødselsdato();

                            // Opret nyt objekt baseret på ny kategori
                            Medlem nytMedlem;
                            switch (nyKategori) {
                                case "AktivJunior":
                                    nytMedlem = new AktivJuniorMedlem(navn, fødselsdato, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                case "AktivSenior":
                                    nytMedlem = new AktivSeniorMedlem(navn, fødselsdato, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                case "PassivtMedlem":
                                    nytMedlem = new PassivtMedlem(navn, fødselsdato, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                default:
                                    System.out.println("Ugyldig kategori! Ingen ændringer foretaget.");
                                    continue;
                            }

                            // Udskift det gamle objekt med det nye
                            medlemmer.set(i, nytMedlem);
                            System.out.println("Medlemskategori opdateret.");
                            break;

                        case 3:
                            System.out.println("Indtast ny fødselsdato (format: YYYY-MM-DD):");
                            String nyDato = sc.nextLine();
                            medlem.setFødselsdato(KonsolHandler.stringToLocalDate(nyDato));
                            break;

                        case 4:
                            isEditing = false;
                            System.out.println("Redigering afsluttet.");
                            break;

                        default:
                            System.out.println("Ugyldigt valg, prøv igen.");

                    }

                }
                gemAlleMedlemmer(medlemmer);
                System.out.println("ændringer er gemt!");
                return;
            }
            System.out.println("ingen medlemmer fundet");

        }

    }

    public void registrerStævneSvømmetid(String medlemId, String disciplin, double tid, String dato) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs medlemmer fra JSON-filen

        for (Medlem medlem : medlemmer) {
            if (medlem.getID().equals(medlemId)) { // Find det specifikke medlem
                Svømmedisciplin svømmedisciplin;

                // Opret instans af den relevante disciplin
                switch (disciplin) {
                    case "Rygcrawl":
                        svømmedisciplin = new Crawl();
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

                // Kald disciplinens registrerTid-metode
                svømmedisciplin.registrerStævneTid(medlem, tid, dato);

                // Gem opdateret liste tilbage i JSON-filen
                gemAlleMedlemmer(medlemmer);
                System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                return;
            }
        }

        System.out.println("Medlem med ID " + medlemId + " blev ikke fundet.");
    }

    public void registrerTræningsSvømmetid(String medlemId, String disciplin, double tid, String dato) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs medlemmer fra JSON-filen

        for (Medlem medlem : medlemmer) {
            if (medlem.getID().equals(medlemId)) { // Find det specifikke medlem
                Svømmedisciplin svømmedisciplin;

                // Opret instans af den relevante disciplin
                switch (disciplin) {
                    case "Rygcrawl":
                        svømmedisciplin = new Crawl();
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

                // Kald disciplinens registrerTid-metode
                svømmedisciplin.registrerTræningsTid(medlem, tid, dato);

                // Gem opdateret liste tilbage i JSON-filen
                gemAlleMedlemmer(medlemmer);
                System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                return;
            }
        }

        System.out.println("Medlem med ID " + medlemId + " blev ikke fundet.");
    }

    //SortDisciplin

    public int visSvømmetider() {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer(); // Læs medlemmer fra JSON
        ArrayList<String> resultater = new ArrayList<>(); // Liste til at gemme visningsresultater

        Scanner scanner = new Scanner(System.in);
        String disciplinNavn;

        // Bed brugeren om at vælge en disciplin
        while (true) {
            System.out.println("Vælg en disciplin:");
            System.out.println("1. Crawl");
            System.out.println("2. Butterfly");
            System.out.println("3. Rygcrawl");
            System.out.println("4. Brystsvømning");
            System.out.print("Indtast dit valg (1-4): ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // Rens scannerens buffer

            switch (valg) {
                case 1:
                    disciplinNavn = "Crawl";
                    break;
                case 2:
                    disciplinNavn = "Butterfly";
                    break;
                case 3:
                    disciplinNavn = "Rygcrawl";
                    break;
                case 4:
                    disciplinNavn = "Brystsvømning";
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    continue;
            }
            break; // Hvis valget er gyldigt, bryd ud af loop
        }

        // Gennemgå alle medlemmer for at finde svømmetider for den givne disciplin
        for (Medlem medlem : medlemmer) {
            for (Svømmetid tid : medlem.getSvømmetider()) {
                if (tid.getDisciplin().equalsIgnoreCase(disciplinNavn)) {
                    // Tilføj informationer til resultatlisten
                    resultater.add("Navn: " + medlem.getNavn() +
                            ", Disciplin: " + tid.getDisciplin() +
                            ", Tid: " + tid.getTid() +
                            " sekunder, Dato: " + tid.getDato());
                }
            }
        }

        // Sortér resultaterne ved hjælp af SorterAlle
        resultater = SorterAlle.sorterResultater(resultater);

        // Udskriv resultaterne
        System.out.println("Svømmetider for disciplinen: " + disciplinNavn);
        for (String resultat : resultater) {
            System.out.println(resultat);
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


        // Læs ID-tælleren fra id_count.json
        public int læsCurrentId() {
            try (FileReader reader = new FileReader(idFilNavn)) {
                Gson gson = new Gson();
                idCount idCount = gson.fromJson(reader, idCount.class);
                return idCount.getCurrentId(); // Returnere det akutelle ID
            } catch (IOException e) {
                System.out.println("Fejl ved læsning af ID-fil: " + e.getMessage());
                return 1; // Hvis filen ikke findes, starter vi ID-tælleren fra 1
            }
        }

        // Opdatere ID-tælleren i, id_count.json
        public void opdatereIdCount ( int nyId){
            try (FileWriter writer = new FileWriter(idFilNavn)) {
                Gson gson = new Gson();
                idCount idCount = new idCount();
                idCount.setCurrentId(nyId);
                gson.toJson(idCount, writer); // Gem den nye ID-værdi i filen
            } catch (IOException e) {
                System.out.println("Fejl ved opdatering af ID-fil: " + e.getMessage());
            }
        }

        // Genere et unikt ID
        public String genereUniktId () {
            int currentId = læsCurrentId(); // Læs det nuværende ID
            String nyId = String.format("SKD%04d", currentId); // Formatere ID som DSF0001, DSF0002, osv.
            opdatereIdCount(currentId + 1); // Opdatere ID-tælleren
            return nyId; // Returnere det nye ID
        }
    }

    


