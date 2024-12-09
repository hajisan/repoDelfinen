import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTop5 {

    // Tom konstruktør
    public SortTop5() {
    }

    public static List<Svømmetid> top5Svømmere(disciplinNavne alleTider) {
        // Sortere tiden i aftagende rækkefølge (bedste tid først) og viser top 5 svømmere og tider
        return alleTider.stream()
                .sorted(Comparator.comparing(Svømmetid::getTid)) // Sort by time (laveste først)
                .limit(5) // Tager top 5
                .collect(Collectors.toCollection(ArrayList::new));

    }

}
