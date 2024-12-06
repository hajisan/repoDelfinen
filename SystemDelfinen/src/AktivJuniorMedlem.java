import java.time.LocalDate; //importerer LocalDate
public class AktivJuniorMedlem extends Medlem{ ////Her laver vi en subklasse "AktivtJuniorMedlem" som extender "Medlem"

    public AktivJuniorMedlem(String navn, LocalDate fødselDato, String køn, String medlemskategori){
        super(navn, fødselDato, køn, medlemskategori); //her implementerer vi superklassen "Medlem"
    }

    @Override
    public double beregnKontingent(){
        return 1000.0; //pris for aktive junior medlemmer
    }
}