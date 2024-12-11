import java.time.LocalDate;
import java.util.ArrayList;

// Abstrakt klasse som representerer et medlem
public class Medlem {
    // Private instansvariabler for medlemsegenskaper
    protected String navn;
    protected LocalDate fødselsDato;
    protected Medlemstyper medlemstypeEnum;
    protected boolean restance = false;
    protected ArrayList<Svømmedisciplin> svømmediscipliner;

    // Konstruktør for at initialisere et Medlem-objekt
    public Medlem(String navn, LocalDate fødselsdato, String medlemsKategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt.");
        }
        this.navn = navn;
        System.out.println(this.navn);
        this.fødselsDato = fødselsdato;
        System.out.println(this.fødselsDato);
        svømmediscipliner= new ArrayList<>();
        svømmediscipliner.add(findEllerOpretSvømmedisciplin(disciplinNavne.BUTTERFLY));
        svømmediscipliner.add(findEllerOpretSvømmedisciplin(disciplinNavne.CRAWL));
        svømmediscipliner.add(findEllerOpretSvømmedisciplin(disciplinNavne.RYGCRAWL));
        svømmediscipliner.add(findEllerOpretSvømmedisciplin(disciplinNavne.BRYSTSVØMNING));

        switch (medlemsKategori.toLowerCase().trim()) {
            case "aktiv":
                if (LocalDate.now().minusYears(18).isBefore(this.fødselsDato)) {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_JUNIOR;
                    System.out.println(this.medlemstypeEnum);
                } else if (LocalDate.now().minusYears(18).isAfter(this.fødselsDato) &&
                        LocalDate.now().minusYears(60).isBefore(this.fødselsDato)) {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR;
                    System.out.println(this.medlemstypeEnum);
                } else {
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR_60PLUS;
                    System.out.println(this.medlemstypeEnum);
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

    public Medlem(String navn, String fødselsdato, String medlemsKategori) {
        this(navn, KonsolHandler.stringToLocalDate(fødselsdato), medlemsKategori);

    }

    public Svømmedisciplin findEllerOpretSvømmedisciplin(disciplinNavne disciplin) {
        for (Svømmedisciplin svømmedisciplin : svømmediscipliner) {
            if (svømmedisciplin.getDisciplinNavn().equals(disciplin.name())) {
                return svømmedisciplin;
            }
        }
        Svømmedisciplin nyDisciplin = new Svømmedisciplin(disciplin);
        svømmediscipliner.add(nyDisciplin);
        return nyDisciplin;
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

    public ArrayList<Svømmetid> getSvømmetider(String disciplin) {
        for (Svømmedisciplin sd : svømmediscipliner) {
            if (sd.getDisciplinNavn().equalsIgnoreCase(disciplin)) {
                if (sd.getTræningsTider() == null) return new ArrayList<Svømmetid>();
                else return sd.getTræningsTider();
            }
        }
        return null;
    }

    public ArrayList<Stævnetid> getStævnetider(String disciplin) {
        for (Svømmedisciplin sd : svømmediscipliner) {
            if (sd.getDisciplinNavn().equalsIgnoreCase(disciplin)) {
                if (sd.getStævneTider() == null) return new ArrayList<Stævnetid>();
                return sd.getStævneTider();
            }
        }
        return null;
    }

    public String getSvømmetiderSomString() {
        String tider = "";
        for (Svømmedisciplin disciplin : svømmediscipliner) {
            tider += disciplin.træningstiderTilString();
        }
        return tider;
        //svømmediscipliner.get(0).træningstiderTilString() + svømmediscipliner.get(1).træningstiderTilString() + svømmediscipliner.get(2).træningstiderTilString() + svømmediscipliner.get(3).træningstiderTilString();
    }

    public String getStævnetiderSomString() {
        String tider = "";
        for (Svømmedisciplin disciplin : svømmediscipliner) {
            tider += disciplin.stævnetiderTilString();
        }
        return tider;
        //svømmediscipliner.get(0).stævnetiderTilString() + svømmediscipliner.get(1).stævnetiderTilString() + svømmediscipliner.get(2).stævnetiderTilString() + svømmediscipliner.get(3).stævnetiderTilString();
    }

    public ArrayList<Svømmedisciplin> getSvømmediscipliner() {
        return svømmediscipliner;
    }

    // toString-metode for at returnere en String af et Medlem-objekt
    public String toString() {
        return "Medlem{" +
                "Navn: " + navn + "\n" +
                "Fødselsdato: " + KonsolHandler.LocalDateToString(fødselsDato) + "\n" +
                "Medlemskategori: " + medlemstypeEnum + "\n" +
                "}";
    }
}