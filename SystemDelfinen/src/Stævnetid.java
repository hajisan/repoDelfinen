import java.time.Duration;
import java.time.LocalDate;

public class Stævnetid extends Svømmetid{
    private String lokalitet;

    public Stævnetid(DisciplinNavne disciplin, Duration tid, LocalDate dato, String lokalitet) {
        super(disciplin, tid, dato);
        this.lokalitet = lokalitet;
    }

    public String getLokalitet() {
        return lokalitet;
    }

    public String setLokalitet() {
        return lokalitet;
    }

    @Override
    public String toString() {
        String string = "";
        return disciplin.toString() + ":" + KonsolHandler.durationToString(tid) + ":" + KonsolHandler.LocalDateToString(dato) + lokalitet;
    }
}
