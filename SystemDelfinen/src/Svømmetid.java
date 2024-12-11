import java.time.Duration;
import java.time.LocalDate;

public class Svømmetid {
    protected Duration tid; // Tid som en Duration
    protected disciplinNavne disciplin; // Disciplin (fx Rygcrawl, Butterfly)
    protected LocalDate dato; // Dato for svømmetiden

    // Constructor
    public Svømmetid(disciplinNavne disciplin, Duration tid, LocalDate dato) {
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
    }

    // Getters
    public Duration getTid() {
        return tid;
    }

    public disciplinNavne getDisciplin() {
        return disciplin;
    }

    public LocalDate getDato() {
        return dato;
    }

    // Metode til at tilføje svømmetid til medlemmet
    public static void tilføjTidTilMedlem(Medlem medlem, long minutter, long sekunder, disciplinNavne disciplin, LocalDate dato) {
        if (medlem == null) {
            throw new IllegalArgumentException("Medlem må ikke være null.");
        }

        // Opret en Duration for tid
        Duration nyTid = Duration.ofMinutes(minutter).plusSeconds(sekunder);

        // Opret en ny Svømmetid
        Svømmetid nySvømmetid = new Svømmetid(disciplin, nyTid, dato);

        // Feedback til konsollen
        System.out.println("Svømmetid tilføjet: " + KonsolHandler.durationToString(nyTid) + " i " +
                disciplin + " for medlem: " + medlem.getNavn());
    }

    @Override
    public String toString() {
        String string = "";
        return disciplin.toString() + ":" + KonsolHandler.durationToString(tid) + ":" + KonsolHandler.LocalDateToString(dato);
    }
}
