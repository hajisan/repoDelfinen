import com.google.gson.Gson;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FilStyrer {
    private static final String filNavn = "AlleMedlemmer.json"; // Navn på JSON-filen
    private static final String idFilNavn = "id_count.json"; // Navn på JSON-filen

    public ArrayList<Medlem> læsAlleMedlemmer() {

    /*
     Læser JSON-filen som en String og konverterer det til en liste over Medlem-objekter.
     Returnerer ArrayList af Medlem-objekter.
     */
    public static ArrayList<Medlem> læsAlleMedlemmer() {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        File file = new File(filNavn);

        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            Medlem[] medlemmerArray = gson.fromJson(reader, Medlem[].class);
            for (Medlem medlem : medlemmerArray) {
                medlemmer.add(medlem);
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return medlemmer;
    }

    public void gemAlleMedlemmer(ArrayList<Medlem> medlemmer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn))) {
            Gson gson = new Gson();
            String json = gson.toJson(medlemmer);
            writer.write(json);
            System.out.println("Data er gemt til filen: " + filNavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    public void tilføjMedlem(Medlem nytMedlem) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();
        medlemmer.add(nytMedlem);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn))) {
            Gson gson = new Gson();
            String json = gson.toJson(medlemmer);
            writer.write(json);
            System.out.println("Medlem tilføjet og gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved tilføjelse af medlem: " + e.getMessage());
        }
    }

    public void sletMedlem(Medlem eksisterendeMedlem) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();
        System.out.println("Medlem: " + "'" + eksisterendeMedlem + "'" + " bliver slettet");
        medlemmer.remove(eksisterendeMedlem);
        gemAlleMedlemmer(medlemmer);
        System.out.println("Medlemmet er slettet og listen er blevet opdateret");
    }

    public void redigerMedlem() {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        for (int i = 0; i < medlemmer.size(); i++) {
            Medlem medlem = medlemmer.get(i);
            if (medlem.getNavn().equalsIgnoreCase(input) || medlem.getID().equals(input)) {
                boolean isEditing = true;

                while (isEditing) {
                    System.out.println("Hvad vil du redigere?");
                    System.out.println("1. Navn");
                    System.out.println("2. Medlemskategori");
                    System.out.println("3. Fødselsdato");
                    System.out.println("4. Afslut redigering");
                    int valg = sc.nextInt();
                    sc.nextLine();

                    switch (valg) {
                        case 1:
                            System.out.println("Indtast nyt navn:");
                            medlem.setNavn(sc.nextLine());
                            break;

                        case 2:
                            System.out.println("Indtast ny medlemskategori (AktivJunior, AktivSenior, PassivtMedlem):");
                            String nyKategori = sc.nextLine();
                            String id = medlem.getID();
                            String navn = medlem.getNavn();
                            String køn = medlem.getKøn();
                            LocalDate fødselsdato = medlem.getFødselsdato();
                            Medlem nytMedlem;
                            switch (nyKategori) {
                                case "AktivJunior":
                                    nytMedlem = new AktivJuniorMedlem(navn, fødselsdato, køn, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                case "AktivSenior":
                                    nytMedlem = new AktivSeniorMedlem(navn, fødselsdato,køn, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                case "PassivtMedlem":
                                    nytMedlem = new PassivtMedlem( navn, fødselsdato, køn, nyKategori);
                                    nytMedlem.beregnKontingent();
                                    break;
                                default:
                                    System.out.println("Ugyldig kategori! Ingen ændringer foretaget.");
                                    continue;
                            }
                            medlemmer.set(i, nytMedlem);
                            System.out.println("Medlemskategori opdateret.");
                            break;

                        case 3:
                            System.out.println("Indtast ny fødselsdato (format: YYYY-MM-DD):");
                            medlem.setFødselsdato(LocalDate.parse(sc.nextLine()));
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
                System.out.println("Ændringer er gemt!");
                return;
            }
        }
        System.out.println("Ingen medlemmer fundet.");
    }

    public void registrerStævneSvømmetid(String medlemId, String disciplin, Duration tid, LocalDate dato, String lokalitet) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();

        for (Medlem medlem : medlemmer) {
            if (medlem.getID().equals(medlemId)) {
                Svømmedisciplin svømmedisciplin;

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

                svømmedisciplin.registrerStævneTid(medlem, tid, dato, lokalitet);
                gemAlleMedlemmer(medlemmer);
                System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                return;
            }
        }
        System.out.println("Medlem med ID " + medlemId + " blev ikke fundet.");
    }

    public void registrerTræningsSvømmetid(String medlemId, String disciplin, Duration tid, LocalDate dato) {
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();

        for (Medlem medlem : medlemmer) {
            if (medlem.getID().equals(medlemId)) {
                Svømmedisciplin svømmedisciplin;

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

                svømmedisciplin.registrerTræningsTid(medlem, tid, dato);
                gemAlleMedlemmer(medlemmer);
                System.out.println("Tid registreret og gemt for medlem: " + medlem.getNavn());
                return;
            }
        }
        System.out.println("Medlem med ID " + medlemId + " blev ikke fundet.");
    }

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

    public int læsCurrentId() {
        try (FileReader reader = new FileReader(idFilNavn)) {
            Gson gson = new Gson();
            idCount idCount = gson.fromJson(reader, idCount.class);
            return idCount.getCurrentId();
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af ID-fil: " + e.getMessage());
            return 1;
        }
    }

    public void opdatereIdCount(int nyId) {
        try (FileWriter writer = new FileWriter(idFilNavn)) {
            Gson gson = new Gson();
            idCount idCount = new idCount();
            idCount.setCurrentId(nyId);
            gson.toJson(idCount, writer);
        } catch (IOException e) {
            System.out.println("Fejl ved opdatering af ID-fil: " + e.getMessage());
        }
    }

    public String genereUniktId() {
        int currentId = læsCurrentId();
        String nyId = String.format("SKD%04d", currentId);
        opdatereIdCount(currentId + 1);
        return nyId;
    }
}

