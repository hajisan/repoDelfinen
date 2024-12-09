public class KontingentStyrer {

    //Metode der beregner og returnerer summen af alle medlemmers kontingenter
    public static double beregnForventetIndkomst() {
        double forventetIndkomst = 0;
        for (Medlem medlem : FilStyrer.læsAlleMedlemmer()) {
            forventetIndkomst += medlem.getMedlemsKategori().getKontingent();
        }
        return forventetIndkomst;
    }

}
