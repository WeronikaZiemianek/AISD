import java.util.LinkedList;
import java.util.List;

public class MSTree {
    TNode[] tab; //tab list sąsiedztwa
    List<Edge> edgeList = new LinkedList<>();
    int itemCount;
    int weight; //waga całego drzewa

    public MSTree(int n) {
        tab = new TNode[n];
        for (int i = 0; i < n; i++) tab[i] = null;
        itemCount = n - 1;
        weight = 0;
    }

    public void addEdge(Edge e) {
        TNode p;

        weight += e.priority;
        p = new TNode(tab[e.v1]);
        p.v = e.v2;
        p.weight = e.priority;
        tab[e.v1] = p;

        p = new TNode(tab[e.v2]);
        p.v = e.v1;
        p.weight = e.priority;
        tab[e.v2] = p;

        Edge temp = new Edge();
        temp.v1 = e.v1;
        temp.v2 = e.v2;
        temp.priority = e.priority;
        edgeList.add(temp);
    }

    public void print() {
        System.out.println();
        for (Edge e : edgeList) {
            System.out.println(e.v1 + " " + e.v2 + " " + e.priority);
        }
        System.out.println(weight);
    }
}
