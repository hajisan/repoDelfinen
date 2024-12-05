import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class KonsolHandler {

    //Constructor -- Tom indtil videre
    public KonsolHandler() {

    }

    //Opretter et nyt medlem med Factory-metoden fra Medlem-klassen
    public Medlem opretMedlem(String navn, String fødselsdato, String medlemskategori) {
        return Medlem.opretMedlem(navn, stringToLocalDate(fødselsdato), medlemskategori);
    }

    //Tilføjer en tid til det valgte medlem ud fra tid i sekunder og millisekunder
    public void tilføjTidTilMedlem(Medlem medlem, long sekunder, long millisekunder) {
        //medlem.tilføjTid(Duration.ofSeconds(sekunder, millisekunder*1000));
    }

    //Tilføjer en træningstid til det valgte medlem
    public void tilføjSvømmetidTilMedlem(Medlem medlem, Duration tid, String disciplin, LocalDate dato) {
        Svømmetid svømmetid = new Svømmetid(tid, disciplin, dato);
        medlem.tilføjTræningstid(svømmetid);
    }

    //Tilføjer en Stævnetid til det valgte medlem
    public void tilføjStævnetidTilMedlem(Medlem medlem, Duration tid, String disciplin, LocalDate dato, String lokalitet) {
        Stævnetid stævnetid = new Stævnetid(tid, disciplin, dato, lokalitet);
        medlem.tilføjStævnetid(stævnetid);
    }

    //Returnerer en ArrayList af et givent medlems Svømmetider
    public ArrayList<Svømmetid> visSvømmetider(Medlem medlem) {
        return medlem.getSvømmetider();
    }

    //Returnerer en ArrayList af et givent medlems Stævnetider
    public ArrayList<Stævnetid> visStævnetider(Medlem medlem) {
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


