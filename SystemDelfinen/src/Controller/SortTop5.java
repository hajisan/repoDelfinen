package Controller;

import Model.Svømmetid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortTop5 {

    // Denne metode forventer en liste af Model.Svømmetid-objekter og returnerer de top 5 hurtigste
    public static ArrayList<Svømmetid> top5Svømmere(ArrayList<Svømmetid> alleTider) {
        if (alleTider == null || alleTider.isEmpty()) { //Hurtig kontrol om er overhovedet er nogle i listen
            throw new IllegalArgumentException("Tidslisten er tom eller null.");
        }

        // Sorterer tiden i stigende rækkefølge (bedste tid først) og returnerer top 5
        return alleTider.stream()
                .sorted(Comparator.comparing(Svømmetid::getTid)) // Sorterer efter tid (laveste først)
                .limit(5) // Tager top 5
                .collect(Collectors.toCollection(ArrayList::new)); // Samler resultaterne i en ArrayList
    }
}
