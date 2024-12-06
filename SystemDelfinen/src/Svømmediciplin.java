
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

abstract class Svømmedisciplin {
    protected String disciplinNavn;
    protected ArrayList<Svømmetid> træningsResultat;

    // Constructor
    public Svømmedisciplin(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
        this.træningsResultat = new ArrayList<>();
    }

    // Registrerer tiden for et medlem
    public void registrerTid(Medlem medlem, Duration tid, String dato) {
        if (medlem == null || tid == null) {
            throw new IllegalArgumentException("Medlem eller tid må ikke være nul.");
        }

        // Opretter en ny Svømmetid og tilføjer den til disciplinens træningsresultater
        Svømmetid svømmetid = new Svømmetid(tid, disciplinNavn, KonsolHandler.stringToLocalDate(dato));
        medlem.tilføjTræningstid(svømmetid);  // Tilføj tid til medlemmet
        træningsResultat.add(svømmetid);  // Tilføj tid til disciplinens liste
    }

    // Returnerer topresultater (de bedste tider) - Implementeres af konkrete discipliner
    public abstract ArrayList<Svømmetid> getTopResultater(int antal);

    // Hjælpefunktion til at formatere Duration som en læsbar streng
    private String formatDuration(Duration duration) {
        long minutter = duration.toMinutes();
        long sekunder = duration.minusMinutes(minutter).getSeconds();
        return String.format("%02d:%02d", minutter, sekunder);
    }

    // Returnerer disciplinens navn
    public String getDisciplinNavn() {
        return disciplinNavn;
    }
}

