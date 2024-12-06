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
        return Medlem.opretMedlem(navn, stringToLocalDate(fødselsdato), medlemskategori);
    }

    //Tilføjer en tid til det valgte medlem ud fra tid i sekunder og millisekunder
    public void tilføjTidTilMedlem(Medlem medlem, long sekunder, long millisekunder) {
        //medlem.tilføjTid(Duration.ofSeconds(sekunder, millisekunder*1000));
    }

    //Tilføjer en træningstid til det valgte medlem
    public void tilføjSvømmetidTilMedlem(Medlem medlem, Duration tid, String disciplin, LocalDate dato) {
        Svømmetid svømmetid = new Svømmetid(tid, disciplin, dato);
        medlem.tilføjTræningstid(svømmetid);
    }

    //Tilføjer en Stævnetid til det valgte medlem
    public void tilføjStævnetidTilMedlem(Medlem medlem, Duration tid, String disciplin, LocalDate dato, String lokalitet) {
        Stævnetid stævnetid = new Stævnetid(tid, disciplin, dato, lokalitet);
        medlem.tilføjStævnetid(stævnetid);
    }

    //Returnerer en ArrayList af et givent medlems Svømmetider
    public ArrayList<Svømmetid> visSvømmetider(Medlem medlem) {
        return medlem.getSvømmetider();
    }

    //Returnerer en ArrayList af et givent medlems Stævnetider
    public ArrayList<Stævnetid> visStævnetider(Medlem medlem) {
        return medlem.getStævnetider();
    }

    public static LocalDate stringToLocalDate(String dato) {
        //Try-catch-blok der skal sørge for, at dato-Stringen er i formatet dd/mm/yyyy
        try {
            return LocalDate.of(Integer.parseInt(dato.split("/")[0]), Integer.parseInt(dato.split("/")[1]), Integer.parseInt(dato.split("/")[2]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ugyldigt format. Indtast venligst dato i formatet dd/mm/yyyy");
        }
    }

    public static String LocalDateToString(LocalDate dato) {
        return dato.toString().split("-")[2] + "/" + dato.toString().split("-")[1] + "/" + dato.toString().split("-")[0];
    }

    // Startmenu til navigation
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            // Print menu
            System.out.println(
                    "Velkommen til medlemssystemet for SKD\n" +
                    "1. Opret et nyt medlem\n" +
                    "2. Tilføj træningstid til medlem\n" +
                    "3. Tilføj stævnetider til medlem\n" +
                    "4. Vis medlems svømmetider\n" +
                    "5. Vis medlems stævnetider\n" +
                    "6. Redigere oplysninger på et medlem\n" +
                    "7. Backup af medlemslisten\n" +
                    "8. Afslut program\n"
                    );
            System.out.print("\n Indtast dit valg: ");
            int valg = scanner.nextInt();
            scanner.nextLine(); // Ryd scanner

            switch (valg) {
                case 1: // Opret nyt medlem

                    // Fornavn og efternavn
                    System.out.print("Indtast fornavn: ");
                    String forNavn = scanner.nextLine();
                    System.out.print("Indtast efternavn: ");
                    String efterNavn = scanner.nextLine();

                    // Fødselsdato
                    System.out.print("Indtast fødselsdato (dd/mm/yyyy): ");
                    String fødselsdato = scanner.nextLine();

                    // Telefonnummer
                    System.out.print("Indtast telefonnummer: ");
                    int telefonnummer = scanner.nextInt();
                    scanner.nextLine(); // Ryd scanner

                    // Mailadresse
                    System.out.print("Indtast mailadresse: ");
                    String mailadresse = scanner.nextLine();

                    // Adresse input
                    System.out.print("Indtast vejnavn og nummer: "); // Vejnavn
                    String vejNavn = scanner.nextLine();
                    System.out.print("Indtast postnummer: "); // Postnummer
                    int postnummer = scanner.nextInt();
                    scanner.nextLine(); // Ryd scanner
                    System.out.print("Indtast bynavn: "); // Bynavn
                    String byNavn = scanner.nextLine();

                    // Passivt eller aktivt medlem
                    System.out.print("Er det et aktivt eller passivt medlem: ");
                    String medlemskategori = scanner.nextLine();

                    // Opret medlem
                    Medlem nytMedlem = opretMedlem(forNavn + " " + efterNavn, fødselsdato, medlemskategori);
                    System.out.println("Medlem oprettet: " + nytMedlem);
                    break;

                case 2: // Tilføj træningstid til medlem
                    // Kopir metoden
                    // Søg efter medlem via navn eller
                    // Implementer logik her
                    break;

                case 3:
                    // Tilføj stævnetider til medlem
                    // Implementer logik her
                    break;

                case 4:
                    // Vis medlems svømmetider 
                    // Implementer logik her
                    break;

                case 5:
                    // Vis medlems stævnetider
                    // Implementer logik her
                    break;

                case 6:
                    // Redigere oplysninger på et medlem
                    // Implementer logik her
                    break;

                case 7:
                    // Backup af medlemslisten
                    // Implementer logik her
                    break;

                case 8:
                    // Afslut program
                    isRunning = false;
                    System.out.println("Programmet afsluttes.");
                    break;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    break;

            }

        }

    }
}


