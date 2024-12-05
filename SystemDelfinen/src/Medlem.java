import java.time.LocalDate;
import java.util.ArrayList;

// Abstrakt klasse som representerer et medlem
public abstract class Medlem {
    // Private instansvariabler for medlemsegenskaper
    protected String navn;
    protected String ID;
    protected LocalDate fødselsdato;
    protected String medlemskategori;
    protected ArrayList<Svømmetid> træningstider;
    protected ArrayList<Stævnetid> stævnetider;

    // Konstruktør for å initialisere et Medlem-objekt
    public Medlem(String navn, String ID, LocalDate fødselsdato, String medlemskategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt.");
        }

        this.navn = navn;
        this.ID = ID;
        this.fødselsdato = fødselsdato;
        this.medlemskategori = medlemskategori;
    }

    public void tilføjTræningstid(Svømmetid træningstid) {
        this.træningstider.add(træningstid);
    }

    public void tilføjStævnetid(Stævnetid stævnetid) {
        this.stævnetider.add(stævnetid);
    }

    //Returnerer en specifik subklasseobjekt af Medlem-klassen baseret på ønsket medlemskategori og -fødselsdato
    public static Medlem opretMedlem(String navn, LocalDate fødselsdato, String medlemskategori) {
        //Minimerer risiko for fejlinput ved at fjerne case sensitivitet og unødventige mellemrumsindtastninger
        medlemskategori = medlemskategori.toLowerCase().trim();
        switch (medlemskategori) {
            //Kaster en IllegalArgumentException, hvis medlemskategori er null
            case null:
                throw new IllegalArgumentException("Medlemskategori må ikke være tom.");
                break;
            //Kaster en IllegalArgumentException, hvis medlemskategori er en tom String
            case "":
                throw new IllegalArgumentException("Medlemskategori må ikke være tom.");
                break;
            //Hvis der er valgt et aktivt medlem, så vælges der mellem AktivJuniorMedlem og AktivSeniorMedlem baseret
            //på, om medlemmet er over 18 år gammelt. Derefter returneres der et passende Medlem-objekt
            case "aktiv":
                if (LocalDate.now().minusYears(18).isBefore(fødselsdato)) {
                    return new AktivJuniorMedlem(navn, fødselsdato);
                } else {
                    return new AktivSeniorMedlem(navn, fødselsdato);
                }
                break;
            //Hvis der er valgt et passivt medlem, så returneres der et nyt PassivtMedlem-objekt
            case "passiv":
                return new PassivtMedlem(navn, fødselsdato);
            break;
            //Kaster en IllegalArgumentException, hvis der er indtastet en ikke-tom String der ikke specifikt er "aktiv" eller "passiv"
            default:
                throw new IllegalArgumentException("Medlemskategori " + medlemskategori + " kan ikke genkendes. Skriv venligst enten aktiv eller passiv.");
        }

    }

    // Getter og Setter metoder
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDate getFødselsdato() {
        return fødselsdato;
    }

    public void setFødselsdato(LocalDate fødselsdato) {
        this.fødselsdato = fødselsdato;
    }

    public String getMedlemskategori() {
        return medlemskategori;
    }

    public void setMedlemskategori(String medlemskategori) {
        this.medlemskategori = medlemskategori;
    }

    // toString-metode for å returnere en strengrepresentasjon av Medlem-objektet
    public String toString() {
        return "Medlem{" +
                "Navn: " + navn + "\n" +
                "ID: " + ID + "\n" +
                "Fødselsdato: " + fødselsdato + "\n" +
                "Medlemskategori: " + medlemskategori + "\n" +
                "}";
    }
}