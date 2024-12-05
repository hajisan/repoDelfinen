import java.time.LocalDate; //importerer LocalDate
public class AktivJuniorMedlem extends Medlem{ ////Her laver vi en subklasse "AktivtJuniorMedlem" som extender "Medlem"

    public AktivJuniorMedlem(String navn, String ID, LocalDate fødselDato, String medlemsKategori){
        super(navn, ID, fødselDato, medlemsKategori); //her implementerer vi superklassen "Medlem"
    }

    @Override
    public double beregnKontingent(){
        return 1000.0; //pris for aktive junior medlemmer
    }
}
