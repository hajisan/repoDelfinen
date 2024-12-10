import java.time.LocalDate;
import java.util.ArrayList;

// Abstrakt klasse som representerer et medlem
public class Medlem {
    // Private instansvariabler for medlemsegenskaper
    protected String navn;
    protected LocalDate fødselsDato;
    protected Medlemstyper medlemstypeEnum;
    protected ArrayList<Svømmetid> træningstider = new ArrayList<>(); // Initialiseret
    protected ArrayList<Stævnetid> stævnetider = new ArrayList<>(); // Initialiseret
    protected boolean restance = false;
    protected static ArrayList<Svømmedisciplin> svømmediscipliner = new ArrayList<>();



    // Konstruktør for at initialisere et Medlem-objekt
    public Medlem(String navn, LocalDate fødselsDato, String medlemsKategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt.");
        }
        this.navn = navn;
        this.fødselsDato = fødselsDato;

        switch (medlemsKategori.toLowerCase().trim()) {
            case "aktiv":
                if (LocalDate.now().minusYears(18).isBefore(this.fødselsDato))
                    this.medlemstypeEnum = Medlemstyper.AKTIV_JUNIOR;
                else if (LocalDate.now().minusYears(18).isAfter(this.fødselsDato) &&
                        LocalDate.now().minusYears(60).isBefore(this.fødselsDato))
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR;
                else
                    this.medlemstypeEnum = Medlemstyper.AKTIV_SENIOR_60PLUS;
                break;
            case "passiv":
                this.medlemstypeEnum = Medlemstyper.PASSIV;
                break;
            default:
                throw new IllegalArgumentException(
                        "Medlemskategori " + medlemsKategori + " kan ikke genkendes. Skriv venligst enten aktiv eller passiv."
                );
        }
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
        this.træningstider.add(træningstid);
    }

    public void tilføjStævnetid(Stævnetid stævnetid) {
        this.stævnetider.add(stævnetid);
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

    public void setRestance() {
        this.restance = !this.restance;
    }

    public ArrayList<Svømmetid> getSvømmetider() {
        return træningstider;
    }

    public ArrayList<Stævnetid> getStævnetider() {
        return stævnetider;
    }

    public Enum<Medlemstyper> getMedlemstypeEnum() {
        return medlemstypeEnum;
    }



    // toString-metode for at returnere en String af et Medlem-objekt
    public String toString() {
        return "Medlem{" +
                "Navn: " + navn + "\n" +
                "Fødselsdato: " + KonsolHandler.LocalDateToString(fødselsDato) + "\n" +
                "Medlemskategori: " + medlemstypeEnum + "\n" +
                "}";
    }


    public static ArrayList<Svømmedisciplin> getSvømmediscipliner() {
        return svømmediscipliner;
    }
}