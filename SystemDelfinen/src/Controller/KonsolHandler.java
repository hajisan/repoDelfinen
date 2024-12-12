package Controller;

import Model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class KonsolHandler {
    FilStyrer filStyrer;
    KontingentStyrer kontingentStyrer;

    //Constructor initialiserer en Controller.FilStyrer og Controller.KontingentStyrer
    public KonsolHandler() {
        filStyrer = new FilStyrer();
        kontingentStyrer = new KontingentStyrer();
    }

    // Metode der tilføjer en træningstid til det valgte medlem
    public void tilføjSvømmetidTilMedlem(Medlem medlem, Duration tid, DisciplinNavne disciplin, LocalDate dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplin);
        Svømmetid svømmetid = new Svømmetid(disciplin, tid, dato);
        medlem.tilføjTræningstid(svømmetid);
        System.out.println("Registreret ny tid for " + disciplin + ": " + svømmetid);
    }

    // Metode der tilføjer en Model.Stævnetid til det valgte medlem
    public void tilføjStævnetidTilMedlem(Medlem medlem, Duration tid, DisciplinNavne disciplin, LocalDate dato, String lokalitet) {
        System.out.println("Registrerer tid for disciplinen: " + disciplin);
        Stævnetid stævnetid = new Stævnetid(disciplin, tid, dato, lokalitet);
        medlem.tilføjStævnetid(stævnetid);
        System.out.println("Registreret ny tid for " + disciplin + ": " + stævnetid);
    }

    // Metode der tager en String af formen "mm:ss", hvor m er minutter og s er sekunder
    public static Duration stringToDuration(String tid) {
        try {
            String[] parts = tid.split(":"); // Split tid i minutter og sekunder
            long minutter = Long.parseLong(parts[0]);
            long sekunder = Long.parseLong(parts[1]);
            return Duration.ofMinutes(minutter).plusSeconds(sekunder);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Fejl: Ugyldigt format for Duration. Brug formatet mm:ss.");
            throw new IllegalArgumentException("Duration-formatet er ugyldigt.");
        }
    }

    // Metode der formaterer Duration som en brugbar String i formen "mm:ss"
    public static String durationToString(Duration duration) {
        long minutter = duration.toMinutes();
        long sekunder = duration.minusMinutes(minutter).getSeconds();
        return String.format("%02d:%02d", minutter, sekunder);
    }

    // Metode der laver en String af formen "dd/mm/yyyy" om til et LocalDate-objekt
    public static LocalDate stringToLocalDate(String dato) {
        //Try-catch-blok der skal sørge for, at dato-Stringen er i formatet dd/mm/yyyy
        try {
            String[] parts = dato.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("Fejl: Ugyldigt datoformat. Brug venligst formatet DD/MM/YYYY.");
            throw new IllegalArgumentException("Datoformatet er ugyldigt eller værdierne er uden for rækkevidde.");
        }
    }

    // Metode der laver et LocalDate-objekt om til en String i formatet "dd/mm/yyyy"
    public static String LocalDateToString(LocalDate dato) {
        return String.format("%02d/%02d/%04d", dato.getDayOfMonth(), dato.getMonthValue(), dato.getYear());
    }

    // Hovedmenu til at styre systemet
    public void startMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean kørSystem = true; //go go time

        while (kørSystem) {
            // Udskriv hovedmenuen
            System.out.println("\nVelkommen til System Delfinen!\n");
            System.out.println("Vælg en funktion:");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Registrer stævnetid");
            System.out.println("3. Registrer træningstid");
            System.out.println("4. Vis alle medlemmer");
            System.out.println("5. Beregn forventet kontingentindkomst");
            System.out.println("6. Vis top 5 svømmere");
            System.out.println("7. Generer økonomirapport");
            System.out.println("8. Afslut");

            int valg = scanner.nextInt();
            scanner.nextLine(); // For at konsumere linjeskiftet

            switch (valg) {
                case 1: //I denne case scanner vi bare efter de informationer vi behøver og kalder opretMedlem metoden
                    // Opretter et nyt medlem
                    System.out.print("Indtast navn: ");
                    String navn = scanner.nextLine();
                    System.out.print("Indtast fødselsdato (DD/MM/YYYY): ");
                    String fødselsdato = scanner.nextLine();
                    System.out.print("Indtast medlemskategori (Aktiv/Passiv): ");
                    String medlemskategori = scanner.nextLine();
                    Medlem nytMedlem = new Medlem(navn, fødselsdato, medlemskategori);
                    filStyrer.tilføjMedlem(nytMedlem); //her tilføjer vi den så til en arrayListe som gemmes til CSV filen
                    System.out.println("Nyt medlem oprettet: " + nytMedlem);
                    break;
                case 2: // Vi scanner efter nødvendige oplysninger, og gemmer i midletidige variabler
                    // Registrerer stævnetid for et medlem
                    System.out.print("Indtast navn på medlem: ");
                    String medlemNavnStævne = scanner.nextLine();
                    System.out.print("Indtast disciplin: ");
                    String disciplinStævne = scanner.nextLine();
                    System.out.print("Indtast tid (i sekunder): ");
                    int tidStævne = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Indtast dato (DD/MM/YYYY): ");
                    String datoStævne = scanner.nextLine();
                    System.out.print("Indtast lokalitet: ");
                    String lokalitet = scanner.nextLine();
                    Medlem medlemStævne = findMedlem(medlemNavnStævne);
                    if (medlemStævne != null) { //her tildeler vi dem så metoden fra tidligere der initialiserer et stævnetids-objekt
                        DisciplinNavne disciplinEnumStævne = DisciplinNavne.valueOf(disciplinStævne.toUpperCase());
                        Duration tidStævneDuration = Duration.ofSeconds(tidStævne);
                        LocalDate datoParsedStævne = stringToLocalDate(datoStævne);
                        tilføjStævnetidTilMedlem(medlemStævne, tidStævneDuration, disciplinEnumStævne, datoParsedStævne, lokalitet);
                        try {
                            filStyrer.gemStævnetider(medlemStævne, new BufferedWriter(new FileWriter(filStyrer.getFilNavn(), true)));
                        } catch (IOException e) {
                            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
                        }
                        System.out.println("Model.Stævnetid registreret og gemt.");
                    } else {
                        System.out.println("Model.Medlem ikke fundet.");
                    }
                    break;
                case 3:// Vi scanner efter nødvendige oplysninger, og gemmer i midletidige variabler
                    // Registrerer træningstid for et medlem
                    System.out.print("Indtast navn på medlem: ");
                    String medlemNavnTræning = scanner.nextLine();
                    System.out.print("Indtast disciplin: ");
                    String disciplinTræning = scanner.nextLine();
                    System.out.print("Indtast tid (i sekunder): ");
                    int tidTræning = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Indtast dato (DD/MM/YYYY): ");
                    String datoTræning = scanner.nextLine();
                    Medlem medlemTræning = findMedlem(medlemNavnTræning);
                    if (medlemTræning != null) {//her tildeler vi dem så metoden fra tidligere der initialiserer et Svømmetids-objekt
                        DisciplinNavne disciplinEnumTræning = DisciplinNavne.valueOf(disciplinTræning.toUpperCase());
                        Duration tidTræningDuration = Duration.ofSeconds(tidTræning);
                        LocalDate datoParsedTræning = stringToLocalDate(datoTræning);
                        tilføjSvømmetidTilMedlem(medlemTræning, tidTræningDuration, disciplinEnumTræning, datoParsedTræning);
                        try {
                            filStyrer.gemStævnetider(medlemTræning, new BufferedWriter(new FileWriter(filStyrer.getFilNavn(), true)));
                        } catch (IOException e) {
                            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
                        }
                        System.out.println("Træningstid registreret og gemt.");
                    } else {
                        System.out.println("Model.Medlem ikke fundet.");
                    }
                    break;
                case 4:
                    // Viser alle medlemmer
                    ArrayList<Medlem> medlemmer = FilStyrer.læsAlleMedlemmer(); //henter bare metoden fra filstyrer
                    if (medlemmer.isEmpty()) {
                        System.out.println("Ingen medlemmer fundet."); //hvis ikke der er indhold, giv meddelse
                    } else {
                        for (Medlem medlem : medlemmer) {
                            System.out.println(medlem); //ellers itererer igennem listen af medlemmer og print dem
                        }
                    }
                    break;
                case 5:
                    // Beregner og viser forventet kontingentindkomst
                    double forventetIndkomst = kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()); //kalder metoden fra kontingentStyrer-klassen
                    System.out.println("Forventet kontingentindkomst: " + forventetIndkomst + " DKK");
                    break;
                case 6:
                    // Viser top 5 svømmere i en given disciplin
                    System.out.print("Indtast disciplin (BUTTERFLY," + //dine valgmuligheder
                            "CRAWL, " +
                            "RYGCRAWL, " +
                            "BRYSTSVØMNING. ");
                    String disciplinInput = scanner.nextLine();
                    DisciplinNavne disciplin = DisciplinNavne.valueOf(disciplinInput.toUpperCase());

                    // Find disciplinen og hent top 5 tider
                    medlemmer = FilStyrer.læsAlleMedlemmer();
                    for (Medlem medlem : medlemmer) {
                        Svømmedisciplin svømmedisciplin = medlem.findSvømmedisciplin(disciplin);
                        ArrayList<Svømmetid> top5 = svømmedisciplin.getTop5Træningstider();

                        // Print top 5 tider for hver svømmer
                        System.out.println("Top 5 træningstider for " + medlem.getNavn() + ":");
                        for (Svømmetid tid : top5) {
                            System.out.println(tid);
                        }
                    }
                    break;
                case 7:
                    // Genererer en økonomirapport
                    ArrayList<Medlem> medlemmerForRapport = FilStyrer.læsAlleMedlemmer(); //henter alle medlemmer
                    ØkonomiRapport rapport = new ØkonomiRapport(medlemmerForRapport); //initaliserer et nyt rapport objekt
                    double totalIndkomst = rapport.beregnTotalIndkomst();
                    System.out.println("Total indkomst: " + totalIndkomst + " DKK");
                    break;
                case 8:
                    // Afslutter programmet
                    System.out.println("Afslutter systemet. Tak for at bruge System Delfinen!");
                    kørSystem = false;
                    break;
                default:
                    // Håndterer ugyldigt input
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
        scanner.close();
    }

    // Metode der finder et medlem baseret på navn
    private Medlem findMedlem(String navn) throws IOException {
        // Søger efter et medlem i listen over medlemmer
        ArrayList<Medlem> medlemmer = FilStyrer.læsAlleMedlemmer();
        for (Medlem medlem : medlemmer) {
            if (medlem.getNavn().equalsIgnoreCase(navn)) {
                return medlem;
            }
        }
        return null;
    }
}