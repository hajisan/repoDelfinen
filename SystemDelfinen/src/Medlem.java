import java.time.LocalDate;

// Abstrakt klasse som representerer et medlem
public abstract class Medlem {
    // Private instansvariabler for medlemsegenskaper
    private String navn;
    private String ID;
    private LocalDate fødselsdato;
    private String medlemskategori;

    // Konstruktør for å initialisere et Medlem-objekt
    public Medlem(String navn, String ID, LocalDate fødselsdato, String medlemskategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }

        this.navn = navn;
        this.ID = ID;
        this.fødselsdato = fødselsdato;
        this.medlemskategori = medlemskategori;
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