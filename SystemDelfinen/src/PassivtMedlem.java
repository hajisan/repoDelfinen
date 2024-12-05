import java.time.LocalDate; //importerer LocalDate
public class PassivtMedlem extends Medlem{ //Her laver vi en subklasse "PassivtMedlem" som extender "Medlem"
    public PassivtMedlem(String navn, LocalDate fødselsDato, String medlemsKategori){
        super(navn, fødselsDato, medlemsKategori); //her implementerer vi superklassen "Medlem"
    }

    @Override
    public double beregnKontingent(){
        return 500.0; //pris for passive medlemmer
    }
}
