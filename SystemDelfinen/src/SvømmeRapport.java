import java.util.ArrayList;

public class SvømmeRapport extends Rapport {



    @Override
    void generer() {

        ArrayList<Medlem> medlemmer = FilStyrer.læsAlleMedlemmer();

        System.out.println("(------------------Svømme Rapport------------------)");
        for (Medlem medlem : medlemmer){
            System.out.println("Medlem: " + medlem.navn + "Stævnetider:" + medlem.stævnetider + "Træningstider"+ medlem.træningstider);
        }
        System.out.println("(------------------Slut Rapport------------------)");



    }
}
