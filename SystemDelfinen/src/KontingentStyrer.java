import java.io.IOException;
import java.util.ArrayList;

public class KontingentStyrer {
    FilStyrer filStyrer;

    public KontingentStyrer(FilStyrer filStyrer) {
        this.filStyrer = filStyrer;
    }

    //Metode der beregner og returnerer summen af alle medlemmers kontingenter
    public double beregnForventetIndkomst() throws IOException {
        double forventetIndkomst = 0;
        for (Medlem medlem : filStyrer.læsAlleMedlemmer()) {
            forventetIndkomst += medlem.getMedlemsKategori().getKontingent();
        }
        return forventetIndkomst;
    }

    //Metode der returnerer en liste over alle klubmedlemmer der er i restance
    public ArrayList<Medlem> findOverskredneBetalinger() throws IOException {
        ArrayList<Medlem> medlemmerIRestance = new ArrayList<>();
        for (Medlem medlem : filStyrer.læsAlleMedlemmer()) {
            if (medlem.getRestance()) medlemmerIRestance.add(medlem);
        }
        return medlemmerIRestance;
    }
}
