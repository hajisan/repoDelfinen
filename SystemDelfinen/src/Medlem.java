import java.time.LocalDate;

// Abstrakt klasse som representerer et medlem
public abstract class Medlem {
    // Private instansvariabler for medlemsegenskaper
    private String navn;
    private String ID;
    private LocalDate fødselsDato;
    private String medlemsKategori;

    // Konstruktør for å initialisere et Medlem-objekt
    public Medlem(String navn, String ID, LocalDate fødselsDato, String medlemsKategori) {
        // Tjekker om navnet er null eller tomt og kaster en IllegalArgumentException hvis det er tilfældet
        if (navn == null || navn.isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }

        this.navn = navn;
        this.ID = ID;
        this.fødselsDato = fødselsDato;
        this.medlemsKategori = medlemsKategori;
    }

    public abstract double beregnKontingent();

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

    public LocalDate getFødselsDato() {
        return fødselsDato;
    }

    public void setFødselsDato(LocalDate fødselsDato) {
        this.fødselsDato = fødselsDato;
    }

    public String getMedlemsKategori() {
        return medlemsKategori;
    }

    public void setMedlemsKategori(String medlemsKategori) {
        this.medlemsKategori = medlemsKategori;
    }

    // toString-metode for å returnere en strengrepresentasjon av Medlem-objektet
    public String toString() {
        return "Medlem{" +
                "Navn: " + navn + "\n" +
                "ID: " + ID + "\n" +
                "Fødselsdato: " + fødselsDato + "\n" +
                "Medlemskategori: " + medlemsKategori + "\n" +
                "}";
    }
}