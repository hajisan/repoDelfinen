import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class KonsolHandler {

    //Constructor -- Tom indtil videre
    public KonsolHandler() {

    }

    //Opretter et nyt medlem med Factory-metoden fra Medlem-klassen
    public Medlem opretMedlem(String navn, String fødselsdato, String medlemskategori) {
        return new Medlem(navn, stringToLocalDate(fødselsdato), medlemskategori);
    }

    //Tilføjer en tid til det valgte medlem ud fra tid i sekunder og millisekunder
    public void tilføjTidTilMedlem(Medlem medlem, long sekunder, long millisekunder) {
        //medlem.tilføjTid(Duration.ofSeconds(sekunder, millisekunder*1000));
    }

    //Tilføjer en træningstid til det valgte medlem
    public void tilføjSvømmetidTilMedlem(Medlem medlem, Duration tid, disciplinNavne disciplin, LocalDate dato) {
        Svømmetid svømmetid = new Svømmetid(disciplin, tid, dato);
        medlem.tilføjTræningstid(svømmetid);
    }

    //Tilføjer en Stævnetid til det valgte medlem
    public void tilføjStævnetidTilMedlem(Medlem medlem, Duration tid, disciplinNavne disciplin, LocalDate dato, String lokalitet) {
        Stævnetid stævnetid = new Stævnetid(disciplin, tid, dato, lokalitet);
        medlem.tilføjStævnetid(stævnetid);
    }

    //Returnerer en ArrayList af et givent medlems Svømmetider
    public ArrayList<Svømmetid> visEtMedlemsSvømmetider(Medlem medlem) {
        return medlem.getSvømmetider();
    }

    //Returnerer en ArrayList af et givent medlems Stævnetider
    public ArrayList<Stævnetid> visStævnetider(Medlem medlem) {
        return medlem.getStævnetider();
    }

    public static LocalDate stringToLocalDate(String dato) {
        //Try-catch-blok der skal sørge for, at dato-Stringen er i formatet dd/mm/yyyy
        try {
            // Split datoen ved "/" og omdan til integers
            String[] parts = dato.split("/");
            int day = Integer.parseInt(parts[0]); // Dag
            int month = Integer.parseInt(parts[1]); // Måned
            int year = Integer.parseInt(parts[2]); // År

            // Opret og returnér LocalDate
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("Fejl: Ugyldigt datoformat. Brug venligst formatet DD/MM/YYYY.");
            throw new IllegalArgumentException("Datoformatet er ugyldigt eller værdierne er uden for rækkevidde.");
        }
    }

    public static String LocalDateToString(LocalDate dato) {
        return dato.toString().split("-")[2] + "/" + dato.toString().split("-")[1] + "/" + dato.toString().split("-")[0];
    }

    // Hovedmenu til at styre systemet
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean kørSystem = true;

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
                case 1:
                    // Opretter et nyt medlem
                    System.out.print("Indtast navn: ");
                    String navn = scanner.nextLine();
                    System.out.print("Indtast fødselsdato (DD/MM/YYYY): ");
                    String fødselsdato = scanner.nextLine();
                    System.out.print("Indtast medlemskategori (Aktiv/Passiv): ");
                    String medlemskategori = scanner.nextLine();
                    Medlem nytMedlem = opretMedlem(navn, fødselsdato, medlemskategori);
                    System.out.println("Nyt medlem oprettet: " + nytMedlem);
                    break;
                case 2:
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
                    if (medlemStævne != null) {
                        disciplinNavne disciplinEnumStævne = disciplinNavne.valueOf(disciplinStævne.toUpperCase());
                        Duration tidStævneDuration = Duration.ofSeconds(tidStævne);
                        LocalDate datoParsedStævne = stringToLocalDate(datoStævne);
                        Svømmedisciplin stævneDisciplin = medlemStævne.findEllerOpretSvømmedisciplin(disciplinEnumStævne);
                        Stævnetid stævnetid = new Stævnetid(disciplinEnumStævne, tidStævneDuration, datoParsedStævne, lokalitet);
                        medlemStævne.tilføjStævnetid(stævnetid);
                        System.out.println("Stævnetid registreret.");
                    } else {
                        System.out.println("Medlem ikke fundet.");
                    }
                    break;
                case 3:
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
                    if (medlemTræning != null) {
                        disciplinNavne disciplinEnumTræning = disciplinNavne.valueOf(disciplinTræning.toUpperCase());
                        Duration tidTræningDuration = Duration.ofSeconds(tidTræning);
                        LocalDate datoParsedTræning = stringToLocalDate(datoTræning);
                        Svømmedisciplin træningDisciplin = medlemTræning.findEllerOpretSvømmedisciplin(disciplinEnumTræning);
                        Svømmetid træningstid = new Svømmetid(disciplinEnumTræning, tidTræningDuration, datoParsedTræning);
                        medlemTræning.tilføjTræningstid(træningstid);
                        System.out.println("Træningstid registreret.");
                    } else {
                        System.out.println("Medlem ikke fundet.");
                    }
                    break;
                case 4:
                    // Viser alle medlemmer
                    ArrayList<Medlem> medlemmer = FilStyrer.læsAlleMedlemmer();
                    if (medlemmer.isEmpty()) {
                        System.out.println("Ingen medlemmer fundet.");
                    } else {
                        for (Medlem medlem : medlemmer) {
                            System.out.println(medlem);
                        }
                    }
                    break;
                case 5:
                    // Beregner og viser forventet kontingentindkomst
                    double forventetIndkomst = KontingentStyrer.beregnForventetIndkomst();
                    System.out.println("Forventet kontingentindkomst: " + forventetIndkomst + " DKK");
                    break;
                case 6:
                    // Viser top 5 svømmere i en given disciplin
                    System.out.print("Indtast disciplin (e.g., BUTTERFLY): ");
                    String disciplinInput = scanner.nextLine();
                    disciplinNavne disciplin = disciplinNavne.valueOf(disciplinInput.toUpperCase());

                    // Find disciplinen og hent top 5 tider
                    medlemmer = FilStyrer.læsAlleMedlemmer();
                    for (Medlem medlem : medlemmer) {
                        Svømmedisciplin svømmedisciplin = medlem.findEllerOpretSvømmedisciplin(disciplin);
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
                    ArrayList<Medlem> medlemmerForRapport = FilStyrer.læsAlleMedlemmer();
                    ØkonomiRapport rapport = new ØkonomiRapport(medlemmerForRapport);
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

    // Hjælpefunktion til at finde et medlem baseret på navn
    private Medlem findMedlem(String navn) {
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