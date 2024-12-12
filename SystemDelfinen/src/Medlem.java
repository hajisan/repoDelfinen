import java.time.LocalDate;
import java.util.ArrayList;

public class Medlem {
    // Private instansvariabler for medlemsegenskaper
    private String navn; // Medlemmets navn
    private LocalDate fødselsDato; // Medlemmets fødselsdato
    private Medlemstyper medlemstypeEnum; // Medlemmets type (f.eks. Aktiv, Passiv)
    private boolean restance = false; // Angiver om medlemmet er i restance
    private ArrayList<Svømmedisciplin> svømmediscipliner; // Liste over svømmediscipliner, som indeholder tider for hver disciplin

    // Constructor initialiserer et Medlem-objekt
    public Medlem(String navn, LocalDate fødselsdato, String medlemsKategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt.");
        }
        this.navn = navn;
        this.fødselsDato = fødselsdato;
        svømmediscipliner = new ArrayList<>();
        svømmediscipliner.add(findSvømmedisciplin(DisciplinNavne.BUTTERFLY));
        svømmediscipliner.add(findSvømmedisciplin(DisciplinNavne.CRAWL));
        svømmediscipliner.add(findSvømmedisciplin(DisciplinNavne.RYGCRAWL));
        svømmediscipliner.add(findSvømmedisciplin(DisciplinNavne.BRYSTSVØMNING));

        switch (medlemsKategori.toLowerCase().trim()) {
            case "aktiv":
                if (LocalDate.now().minusYears(18).isBefore(this.fødselsDato)) {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_JUNIOR;
                } else if (LocalDate.now().minusYears(18).isAfter(this.fødselsDato) &&
                        LocalDate.now().minusYears(60).isBefore(this.fødselsDato)) {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR;
                } else {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR_60PLUS;
                }
                break;
            case "passiv":
                this.medlemstypeEnum = Medlemstyper.PASSIV;
                break;
            default:
                throw new IllegalArgumentException(
                        "Medlemskategori: " + medlemsKategori + " kan ikke genkendes. Skriv venligst enten aktiv eller passiv."
                );
        }
    }

    // Alternativ constructor der tager en String som fødselsdato i stedet for et LocalDate-objekt
    public Medlem(String navn, String fødselsdato, String medlemsKategori) {
        this(navn, KonsolHandler.stringToLocalDate(fødselsdato), medlemsKategori);

    }

    // Metode der finder en svømmedisciplin i listen over de fire discipliner
    public Svømmedisciplin findSvømmedisciplin(DisciplinNavne disciplin) {
        for (Svømmedisciplin svømmedisciplin : svømmediscipliner) {
            if (svømmedisciplin.getDisciplinNavn().equals(disciplin.name())) {
                return svømmedisciplin;
            }
        }
        return null;
    }


    public void tilføjTræningstid(Svømmetid træningstid) {
        String disciplin = træningstid.getDisciplin().toString();
        for (int i = 0; i < this.svømmediscipliner.size(); i++) {
            if (svømmediscipliner.get(i).getDisciplinNavn().equalsIgnoreCase(disciplin)) {
                svømmediscipliner.get(i).registrerTræningsTid(træningstid);
            }
        }
    }

    public void tilføjStævnetid(Stævnetid stævnetid) {
        String disciplin = stævnetid.getDisciplin().toString();
        for (int i = 0; i < this.svømmediscipliner.size(); i++) {
            if (svømmediscipliner.get(i).getDisciplinNavn().equalsIgnoreCase(disciplin)) {
                svømmediscipliner.get(i).registrerStævneTid(stævnetid);
            }
        }
    }

    // Getter og Setter metoder
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getFødselsdato() {
        return fødselsDato;
    }

    public void setFødselsdato(LocalDate fødselsDato) {
        this.fødselsDato = fødselsDato;
    }

    public double getKontingent() {
        return medlemstypeEnum.getKontingent();
    }

    public Medlemstyper getMedlemsKategori() {
        return medlemstypeEnum;
    }

    public void setMedlemsKategori(Medlemstyper type) {
        this.medlemstypeEnum = type;
    }

    public int getAlder() {
        return LocalDate.now().getYear() - fødselsDato.getYear();
    }

    public boolean getRestance() {
        return this.restance;
    }

    public void setRestance(boolean restance) {
        this.restance = restance;
    }

    public Medlemstyper getMedlemstypeEnum() {
        return medlemstypeEnum;
    }

    public ArrayList<Svømmedisciplin> getSvømmediscipliner() {
        return svømmediscipliner;
    }

    // Metode der returnerer medlemmet som String i et format der stemmer overens med det, der skrives ind i vores AlleMedlemmer.csv
    public String genererCSV() {
        StringBuilder csvLinje = new StringBuilder();
        csvLinje.append(navn).append(",")
                .append(KonsolHandler.LocalDateToString(fødselsDato)).append(",")
                .append(medlemstypeEnum).append(",")
                .append(restance ? "true" : "false");

        for (Svømmedisciplin disciplin : svømmediscipliner) {
            //csvLinje.append()
            csvLinje.append(",").append(svømmetiderTilCsv(disciplin.getTræningsTider()));
            csvLinje.append(",").append(stævnetiderTilCsv(disciplin.getStævneTider()));
        }
        return csvLinje.toString();
    }

    // Metode der skriver svømmetider i vores CSV-format
    public String svømmetiderTilCsv(ArrayList<Svømmetid> tider) {
        if (tider == null || tider.isEmpty()) {
            return "";
        }
        StringBuilder csvTider = new StringBuilder();
        for (Svømmetid tid : tider) {
            csvTider.append(KonsolHandler.durationToString(tid.getTid()))
                    .append("|")
                    .append(KonsolHandler.LocalDateToString(tid.getDato()))
                    .append(";");
        }
        csvTider.setLength(csvTider.length() - 1); // Fjern sidste semikolon
        return csvTider.toString();
    }

    // Metode der skriver stævnetider i vores CSV-format
    public String stævnetiderTilCsv(ArrayList<Stævnetid> tider) {
        if (tider == null || tider.isEmpty()) {
            return "";
        }
        StringBuilder csvTider = new StringBuilder();
        for (Stævnetid tid : tider) {
            csvTider.append(KonsolHandler.durationToString(tid.getTid()))
                    .append("|")
                    .append(KonsolHandler.LocalDateToString(tid.getDato()))
                    .append("|")
                    .append(tid.getLokalitet())
                    .append(";");
        }
        csvTider.setLength(csvTider.length() - 1); // Fjern sidste semikolon
        return csvTider.toString();
    }


    // toString-metode for at returnere en String af et Medlem-objekt
    @Override
    public String toString() {
        return "- Medlem:\n" +
                "  Navn: " + navn + "\n" +
                "  Fødselsdato: " + KonsolHandler.LocalDateToString(fødselsDato) + "\n" +
                "  Medlemskategori: " + medlemstypeEnum + "\n";
    }
}