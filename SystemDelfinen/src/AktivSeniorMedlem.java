import java.time.LocalDate; //importerer LocalDate
public class AktivSeniorMedlem extends Medlem{ //Her laver vi en subklasse "AktivtSeniorMedlem" som extender "Medlem"

    public AktivSeniorMedlem(String navn, String ID, LocalDate fødselsDato, String medlemsKategori){
        super(navn,ID,fødselsDato,medlemsKategori); //her implementerer vi superklassen "Medlem"
    }

    @Override
    public double beregnKontingent() {
        int alder = LocalDate.now().getYear() - getFødselsDato().getYear(); //udregning for at finde alder
        if (alder >= 60) {
            return 1600.0 * 0.75; // 25 % rabat for medlemmer over 60 år
        }
        return 1600.0; // Pris for aktive senior medlemmer under 60 år
    }

}