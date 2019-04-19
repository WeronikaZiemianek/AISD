import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ALaEuler {

    List<Edge> edgeList;
    int[] tab;
    List<Integer> path;

    public ALaEuler(List<Edge> edgeList, int n) {
        this.edgeList = new LinkedList<>(edgeList);
        tab = new int[n];

        for (Edge elem : edgeList) {
            tab[elem.v1]++;
            tab[elem.v2]++;
        }

        path = new LinkedList<>();
        for(int i = 0; i< n; i++)
        {
            if(tab[i] == 1)
            {
                path.add(i);
                break;
            }
        }


    }
}
