package Controller;

import Model.Medlem;

import java.io.IOException;
import java.util.ArrayList;

public class KontingentStyrer {

    public KontingentStyrer() {
    }

    //Metode der beregner og returnerer summen af alle medlemmers kontingenter
    public double beregnForventetIndkomst(ArrayList<Medlem> liste) throws IOException {
        double forventetIndkomst = 0;
        for (Medlem medlem : liste) {
            forventetIndkomst += medlem.getMedlemsKategori().getKontingent();
        }
        return forventetIndkomst;
    }

    //Metode der returnerer en liste over alle klubmedlemmer der er i restance
    public ArrayList<Medlem> findOverskredneBetalinger() throws IOException {
        ArrayList<Medlem> medlemmerIRestance = new ArrayList<>();
        for (Medlem medlem : FilStyrer.læsAlleMedlemmer()) {
            if (medlem.getRestance()) medlemmerIRestance.add(medlem);
        }
        return medlemmerIRestance;
    }
}
