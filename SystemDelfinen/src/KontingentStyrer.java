import java.util.ArrayList;

public class KontingentStyrer {

    //Metode der beregner og returnerer summen af alle medlemmers kontingenter
    public static double beregnForventetIndkomst() {
        double forventetIndkomst = 0;
        for (Medlem medlem : FilStyrer.læsAlleMedlemmer()) {
            forventetIndkomst += medlem.getMedlemsKategori().getKontingent();
        }
        return forventetIndkomst;
    }

    //Metode der returnerer en liste over alle klubmedlemmer der er i restance
    public static ArrayList<Medlem> findOverskredneBetalinger() {
        ArrayList<Medlem> medlemmerIRestance = new ArrayList<>();
        for (Medlem medlem : FilStyrer.læsAlleMedlemmer()) {
            if (medlem.getRestance()) medlemmerIRestance.add(medlem);
        }
        return medlemmerIRestance;
    }
}
