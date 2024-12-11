import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FilStyrer {

    private static final String filNavn = "SystemDelfinen/resources/AlleMedlemmer.csv"; // Navn på csv-filen

    public FilStyrer() {
    }

    /*
     Læser alle medlemmer fra CSV-filen.
     Formatet i filen skal være: Navn, Fødselsdato, Medlemskategori
     returnerer En ArrayList af Medlem-objekter læst fra filen
     */

    // Læs alle medlemmer fra CSV-filen
    public static ArrayList<Medlem> læsAlleMedlemmer() {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        File file = new File(filNavn);

        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer;
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            //her går vi ind og aflæser hver linje. Da det er CSV (comma seperated version), er hver data imellem kommaer
            while ((line = reader.readLine()) != null) {
                String[] parts = new String[line.split(",").length];
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = line.split(",")[i];
                }
                if (parts[0].equalsIgnoreCase("Medlem")) {
                    // Læs medlem
                    String navn = parts[1].trim(); //på plads 1 har vi navn
                    LocalDate fødselsdato = KonsolHandler.stringToLocalDate(parts[2].trim()); //fødselsdagsdato der laves om til string
                    String medlemskategori = parts[3].trim(); //er du aktiv eller passivt medlem? junior eller senior?
                    Medlem medlem = new Medlem(navn, fødselsdato, medlemskategori);
                    medlemmer.add(medlem); //tilføjer medlemmet til arraylisten medlemmer
                    if (parts.length >= 5 && parts[4].equalsIgnoreCase("Træningstider")) {
                        String[] træningstiderIParts = parts[5].split(";");
                        for (String partToSplitAgain : træningstiderIParts) {
                            switch (partToSplitAgain.split(":")[0].toLowerCase().trim()) {
                                case "butterfly":
                                    medlem.tilføjTræningstid(new Svømmetid(disciplinNavne.BUTTERFLY, KonsolHandler.stringToDuration(træningstiderIParts[1]), KonsolHandler.stringToLocalDate(træningstiderIParts[2])));
                                    break;
                                case "crawl":
                                    medlem.tilføjTræningstid(new Svømmetid(disciplinNavne.CRAWL, KonsolHandler.stringToDuration(træningstiderIParts[1]), KonsolHandler.stringToLocalDate(træningstiderIParts[2])));
                                    break;
                                case "rygcrawl":
                                    medlem.tilføjTræningstid(new Svømmetid(disciplinNavne.RYGCRAWL, KonsolHandler.stringToDuration(træningstiderIParts[1]), KonsolHandler.stringToLocalDate(træningstiderIParts[2])));
                                    break;
                                case "brystsvømning":
                                    medlem.tilføjTræningstid(new Svømmetid(disciplinNavne.BRYSTSVØMNING, KonsolHandler.stringToDuration(træningstiderIParts[1]), KonsolHandler.stringToLocalDate(træningstiderIParts[2])));
                                    break;
                            }
                        }
                    }
                    if (parts.length >= 7 && parts[6].equalsIgnoreCase("Stævnetider")) {
                        String[] stævnetiderIParts = parts[7].split(";");
                        for (String partToSplitAgain : stævnetiderIParts) {
                            switch (partToSplitAgain.split(":")[0].toLowerCase().trim()) {
                                case "butterfly":
                                    medlem.tilføjTræningstid(new Stævnetid(disciplinNavne.BUTTERFLY, KonsolHandler.stringToDuration(stævnetiderIParts[1]), KonsolHandler.stringToLocalDate(stævnetiderIParts[2]), stævnetiderIParts[3]));
                                    break;
                                case "crawl":
                                    medlem.tilføjTræningstid(new Stævnetid(disciplinNavne.CRAWL, KonsolHandler.stringToDuration(stævnetiderIParts[1]), KonsolHandler.stringToLocalDate(stævnetiderIParts[2]), stævnetiderIParts[3]));
                                    break;
                                case "rygcrawl":
                                    medlem.tilføjTræningstid(new Stævnetid(disciplinNavne.RYGCRAWL, KonsolHandler.stringToDuration(stævnetiderIParts[1]), KonsolHandler.stringToLocalDate(stævnetiderIParts[2]), stævnetiderIParts[3]));
                                    break;
                                case "brystsvømning":
                                    medlem.tilføjTræningstid(new Stævnetid(disciplinNavne.BRYSTSVØMNING, KonsolHandler.stringToDuration(stævnetiderIParts[1]), KonsolHandler.stringToLocalDate(stævnetiderIParts[2]), stævnetiderIParts[3]));
                                    break;
                            }
                        }
                    }
                    if (parts.length >= 10 && parts[8].equalsIgnoreCase("restance")) {
                        medlem.setRestance(Boolean.parseBoolean(parts[9]));
                    }
                }
                    /*
                    // Læs træningstid
                    String navn = parts[1].trim(); //Vi tager navnet på medlemmet
                    disciplinNavne disciplin = disciplinNavne.valueOf(parts[2].trim()); //Hvilken disciplin har de deltaget i
                    Duration tid = Duration.ofSeconds(Long.parseLong(parts[3].trim())); //hvor hurtgige var de
                    LocalDate dato = KonsolHandler.stringToLocalDate(parts[4].trim()); // datoen hvor på det skete i let læseligt format

                    Medlem medlem = findMedlemByName(medlemmer, navn); //den her mini metode, er bare for at finde det rigtige medlem
                    // og tilføje den disciplin og tid til dem
                    if (medlem != null) {
                        Svømmetid svømmetid = new Svømmetid(disciplin, tid, dato);
                        medlem.tilføjTræningstid(svømmetid);
                    }
                 else if (parts[0].equalsIgnoreCase("Stævnetid")) {
                    // Læs stævnetid
                    String navn = parts[1].trim();
                    disciplinNavne disciplin = disciplinNavne.valueOf(parts[2].trim());
                    Duration tid = Duration.ofSeconds(Long.parseLong(parts[3].trim()));
                    LocalDate dato = KonsolHandler.stringToLocalDate(parts[4].trim());
                    String lokalitet = parts[5].trim(); //her er det det samme som træningstider, der er bare også en string for lokalitet

                    Medlem medlem = findMedlemByName(medlemmer, navn);
                    if (medlem != null) {
                        Stævnetid stævnetid = new Stævnetid(disciplin, tid, dato, lokalitet);
                        medlem.tilføjStævnetid(stævnetid);
                    }
                }

                   */
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
    // Gem alle medlemmer til CSV-filen
    public void gemAlleMedlemmer(ArrayList<Medlem> medlemmer) {
        File file = new File(filNavn);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (Medlem medlem : medlemmer) { //For each loop der itererer gennem vores array af medlemmer
                // Gem medlem
                writer.write("Medlem," + medlem.getNavn() + "," + KonsolHandler.LocalDateToString(medlem.getFødselsdato()) + "," + medlem.getMedlemstypeEnum() + "," + "Træningstider" + "," + medlem.getSvømmetiderSomString() + "," + "Stævnetider" + "," + medlem.getStævnetiderSomString() + "," + "Restance" + "," + medlem.getRestance());// her skrives informationen ind i CSV filen i det
                writer.newLine(); // her går vi videre til næste linje

                // Gem træningstider
                gemTræningstider(medlem, writer);

                // Gem stævnetider
                gemStævnetider(medlem, writer);
            }
            writer.close();
            System.out.println("Data gemt til CSV-filen: " + filNavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    public void gemTræningstider(Medlem medlem, BufferedWriter writer) throws IOException {
        // Gem træningstider
        for (Svømmetid træningstid : medlem.getSvømmetider("Butterfly")) { //For each loop, for svømmetider,
            writer.write("Træningstid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    træningstid.getDisciplin() + "," + træningstid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(træningstid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid træningstid : medlem.getSvømmetider("Crawl")) { //For each loop, for svømmetider,
            writer.write("Træningstid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    træningstid.getDisciplin() + "," + træningstid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(træningstid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid træningstid : medlem.getSvømmetider("Rygcrawl")) { //For each loop, for svømmetider,
            writer.write("Træningstid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    træningstid.getDisciplin() + "," + træningstid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(træningstid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid træningstid : medlem.getSvømmetider("Brystsvømning")) { //For each loop, for svømmetider,
            writer.write("Træningstid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    træningstid.getDisciplin() + "," + træningstid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(træningstid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
    }

    public void gemStævnetider(Medlem medlem, BufferedWriter writer) throws IOException {
        for (Svømmetid stævnetid : medlem.getStævnetider("Butterfly")) { //For each loop, for svømmetider,
            writer.write("Stævnetid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    stævnetid.getDisciplin() + "," + stævnetid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(stævnetid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid stævnetid : medlem.getStævnetider("Crawl")) { //For each loop, for svømmetider,
            writer.write("Stævnetid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    stævnetid.getDisciplin() + "," + stævnetid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(stævnetid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid stævnetid : medlem.getStævnetider("Rygcrawl")) { //For each loop, for svømmetider,
            writer.write("Stævnetid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    stævnetid.getDisciplin() + "," + stævnetid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(stævnetid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
        }
        for (Svømmetid stævnetid : medlem.getStævnetider("Brystsvømning")) { //For each loop, for svømmetider,
            writer.write("Stævnetid," + medlem.getNavn() + "," + //tilføjer tiden til medlemmet
                    stævnetid.getDisciplin() + "," + stævnetid.getTid().toSeconds() + "," + KonsolHandler.LocalDateToString(stævnetid.getDato()));
            writer.newLine(); // Ny linje i CSV-filen
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
        if (medlemmer.removeIf(m -> m.getNavn().equalsIgnoreCase(eksisterendeMedlem.getNavn()))) { //hvis det indtastet navn er = med et eksisterende medlem, så fjern det
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

        for (Medlem medlem : medlemmer) { //vi iterer gennem listen af medlemmer
            if (medlem.getNavn().equalsIgnoreCase(input)) { //sammenligner det med eksisterende nanvne
                medlemFundet = true;//hvis det findes er er denne sat til true og
                boolean isEditing = true; //denne sættes til true

                while (isEditing) { //så starter vores do while loop
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

        if (medlemFundet) { //hvis medlemmet eksisterer gemmer vi så ændringene
            gemAlleMedlemmer(medlemmer); // Gem ændringer
            System.out.println("Ændringer gemt.");
            sc.close();
        } else {
            System.out.println("Ingen medlemmer fundet med det angivne navn.");
        }
    }

    // Læser svømmetider fra en CSV-fil og opdaterer medlemmernes data
    public void læsSvømmetider(ArrayList<Medlem> medlemmer, String filNavn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filNavn))) {
            String line;

            // Læs hver linje i CSV-filen
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Udpak data
                String medlemNavn = data[0];
                disciplinNavne disciplin = disciplinNavne.valueOf(data[1]);
                Duration tid = Duration.parse(data[2]);
                LocalDate dato = LocalDate.parse(data[3]);

                // Find medlem baseret på navn
                Medlem medlem = findMedlemByName(medlemmer, medlemNavn);

                // Find eller opret disciplin for medlemmet
                Svømmedisciplin svømmedisciplin = medlem.findEllerOpretSvømmedisciplin(disciplin);

                if (data.length == 5) { // Stævnetid (har lokalitet)
                    String lokalitet = data[4];
                    Stævnetid nyStævnetid = new Stævnetid(disciplin, tid, dato, lokalitet);
                    svømmedisciplin.getStævneTider().add(nyStævnetid);
                } else { // Træningstid
                    Svømmetid nyTræningstid = new Svømmetid(disciplin, tid, dato);
                    svømmedisciplin.getTræningsTider().add(nyTræningstid);
                }
            }
        }
    }


    // Gemmer de 5 bedste trænings- og stævnetider til en CSV-fil
    public void gemSvømmetider(ArrayList<Medlem> medlemmer, String filNavn) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filNavn, true))) {
            // Gennemløb alle medlemmer
            for (Medlem medlem : medlemmer) {
                // Gennemgå hver disciplin for medlemmet
                for (Svømmedisciplin disciplin : medlem.getSvømmediscipliner()) {
                    // Gem de 5 bedste træningstider
                    ArrayList<Svømmetid> bedsteTræningstider = disciplin.getTop5Træningstider();
                    for (Svømmetid tid : bedsteTræningstider) {
                        writer.write(medlem.getNavn() + "," + disciplin.getDisciplinNavn() + "," + tid.getTid() + "," + tid.getDato());
                        writer.newLine();
                    }

                    // Gem de 5 bedste stævnetider
                    ArrayList<Stævnetid> bedsteStævnetider = disciplin.getTop5Stævnetider();
                    for (Stævnetid tid : bedsteStævnetider) {
                        writer.write(medlem.getNavn() + "," + disciplin.getDisciplinNavn() + "," + tid.getTid() + "," + tid.getDato() + "," + tid.getLokalitet());
                        writer.newLine();
                    }
                }
            }
        }
    }

    // Hjælpefunktion til at finde et medlem baseret på navn
    private static Medlem findMedlemByName(ArrayList<Medlem> medlemmer, String navn) {
        for (Medlem medlem : medlemmer) {
            if (medlem.getNavn().equalsIgnoreCase(navn)) {
                return medlem;
            }
        }
        throw new IllegalArgumentException("Medlem med navn " + navn + " blev ikke fundet.");
    }

    /*
     Initialiserer CSV-filen, hvis den ikke allerede eksisterer. Bare til backup for andre
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

    public String getFilNavn() {
        return filNavn;
    }
}

