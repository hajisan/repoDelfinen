import java.time.Duration;
import java.time.LocalDate;

public class Svømmetid {
    private Duration tid; // Tid som en Duration
    private disciplinNavne disciplin; // Disciplin (fx Rygcrawl, Butterfly)
    private LocalDate dato; // Dato for svømmetiden

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

        // Tilføj svømmetiden til medlemmets træningsresultater
        medlem.getSvømmetider().add(nySvømmetid);

        // Feedback til konsollen
        System.out.println("Svømmetid tilføjet: " + formatDuration(nyTid) + " i " +
                disciplin + " for medlem: " + medlem.getNavn());
    }

    // Hjælpefunktion til at formatere Duration som en læsbar streng
    private static String formatDuration(Duration duration) {
        long minutter = duration.toMinutes();
        long sekunder = duration.minusMinutes(minutter).getSeconds();
        return String.format("%02d:%02d", minutter, sekunder);
    }

}
