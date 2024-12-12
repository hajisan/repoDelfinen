import java.time.Duration;
import java.time.LocalDate;

public class Svømmetid {
    protected Duration tid; // Tid som en Duration
    protected DisciplinNavne disciplin; // Disciplin (fx Rygcrawl, Butterfly)
    protected LocalDate dato; // Dato for svømmetiden

    // Constructor
    public Svømmetid(DisciplinNavne disciplin, Duration tid, LocalDate dato) {
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
    }

    // Getters
    public Duration getTid() {
        return tid;
    }

    public DisciplinNavne getDisciplin() {
        return disciplin;
    }

    public LocalDate getDato() {
        return dato;
    }

    @Override
    public String toString() {
        String string = "";
        return disciplin.toString() + ":" + KonsolHandler.durationToString(tid) + ":" + KonsolHandler.LocalDateToString(dato);
    }
}
