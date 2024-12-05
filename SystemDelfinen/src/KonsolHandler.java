import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class KonsolHandler {

    public KonsolHandler() {

    }

    public Medlem opretMedlem(String navn, String fødselsdato, String medlemskategori) {
        return Medlem.opretMedlem(navn, stringToLocalDate(fødselsdato), medlemskategori);
    }

    public void tilføjTidTilMedlem(Medlem medlem, long sekunder, long millisekunder) {
        medlem.tilføjTid(Duration.ofSeconds(sekunder, millisekunder*1000));
    }

    public ArrayList<Duration> visSvømmetider(Medlem medlem) {
        return medlem.getSvømmetider();
    }

    public ArrayList<Duration> visStævnetider(Medlem medlem) {
        return medlem.getStævnetider();
    }

    public LocalDate stringToLocalDate(String dato) {
        //Try-catch-blok der skal sørge for, at dato-Stringen er i formatet dd/mm/yyyy
        try {
            return LocalDate.of(Integer.parseInt(dato.split("/")[0]), Integer.parseInt(dato.split("/")[1]), Integer.parseInt(dato.split("/")[2]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ugyldigt format. Indtast venligst dato i formatet dd/mm/yyyy");
        }
    }
}


