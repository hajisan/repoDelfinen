import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class FilStyrer {

    private static final String filNavn = "SystemDelfinen/resources/AlleMedlemmer.csv"; // Navn på csv-filen

    public FilStyrer() {}

    // Metode der læser alle medlemmer fra CSV-filen
    public static ArrayList<Medlem> læsAlleMedlemmer() throws IOException {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        File file = new File(filNavn);

        if (!file.exists()) {
            System.out.println("Filen findes ikke. Returnerer en tom liste.");
            return medlemmer;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(","); // Del linjen i felter

                // Opret medlem
                String navn = fields[0];
                LocalDate fødselsdato = KonsolHandler.stringToLocalDate(fields[1]);
                String medlemskategori = fields[2];
                boolean restance = Boolean.parseBoolean(fields[3]);
                Medlem medlem = new Medlem(navn, fødselsdato, medlemskategori);
                medlem.setRestance(restance);

                // Parse træningstider og stævnetider for hver disciplin
                int disciplinIndex = 0;
                for (int i = 4; i < fields.length; i += 2) { // Træning og stævne felter
                    DisciplinNavne disciplinNavn = DisciplinNavne.values()[disciplinIndex++];
                    Svømmedisciplin disciplin = medlem.findSvømmedisciplin(disciplinNavn);

                    // Træningstider
                    if (!fields[i].isEmpty()) {
                        for (String tidStr : fields[i].split(";")) {
                            String[] parts = tidStr.split("\\|");
                            Duration tid = KonsolHandler.stringToDuration(parts[0]);
                            LocalDate dato = KonsolHandler.stringToLocalDate(parts[1]);
                            disciplin.registrerTræningsTid(new Svømmetid(disciplinNavn, tid, dato));
                        }
                    }

                    // Stævnetider
                    if (i + 1 < fields.length && !fields[i + 1].isEmpty()) {
                        for (String tidStr : fields[i + 1].split(";")) {
                            String[] parts = tidStr.split("\\|");
                            Duration tid = KonsolHandler.stringToDuration(parts[0]);
                            LocalDate dato = KonsolHandler.stringToLocalDate(parts[1]);
                            String lokalitet = parts[2];
                            disciplin.registrerStævneTid(new Stævnetid(disciplinNavn, tid, dato, lokalitet));
                        }
                    }
                }
                medlemmer.add(medlem); // Tilføj medlemmet til listen
            }
        }
        return medlemmer;
    }

    // Gem alle medlemmer til CSV-filen
    public void gemAlleMedlemmer(ArrayList<Medlem> medlemmer) {
        File file = new File(filNavn);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Medlem medlem : medlemmer) { //For each loop der itererer gennem vores array af medlemmer
                // Gem medlem
                writer.write(medlem.genererCSV());
                writer.newLine();

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

    // Gem træningstider
    public void gemTræningstider(Medlem medlem, BufferedWriter writer) throws IOException {
        for (Svømmedisciplin disciplin : medlem.getSvømmediscipliner()) {
            writer.write(medlem.svømmetiderTilCsv(disciplin.træningsTider));
        }

    }

    // Gem stævnetider
    public void gemStævnetider(Medlem medlem, BufferedWriter writer) throws IOException {
        for (Svømmedisciplin disciplin : medlem.getSvømmediscipliner()) {
            writer.write(medlem.stævnetiderTilCsv(disciplin.stævneTider));
        }
    }

    //Tilføjer et nyt medlem til CSV-filen.
    public void tilføjMedlem(Medlem nytMedlem) throws IOException{
        ArrayList<Medlem> medlemmer = læsAlleMedlemmer();
        medlemmer.add(nytMedlem);
        gemAlleMedlemmer(medlemmer);
        System.out.println("Medlem tilføjet og gemt.");
    }

    public String getFilNavn() {
        return filNavn;
    }
}