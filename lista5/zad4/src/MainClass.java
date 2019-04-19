import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainClass {

    public static void main(String args[]) throws IOException {

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore;
        long usedMemoryAfter;
        int n;
        int m;
        long start, stop;
        List<Edge> l = new LinkedList<>();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj liczbe wierzcholkow");
        n = Integer.parseInt(br.readLine());
        m = n * (n - 1) / 2;

        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++) {
                Edge edge = new Edge();
                edge.v1 = i;
                edge.v2 = j;
                edge.priority = j + i;
                l.add(edge);
            }


        start = System.currentTimeMillis();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        RandomErr re = new RandomErr();
        re.addVertex(n);

        for (Edge elem : l) {
            re.addEdge(elem.v1, elem.v2, elem.priority);
        }

        re.randomErr(n);
        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        stop = System.currentTimeMillis();
        print(re.counter, re.wage, usedMemoryAfter-usedMemoryBefore, stop - start);

        start = System.currentTimeMillis();
        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        WagesWalk ww = new WagesWalk();
        ww.addVertex(n);

        for (Edge elem : l) {
            ww.addEdge(elem.v1, elem.v2, elem.priority);
        }

        ww.wagesWalk(n);
        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        stop = System.currentTimeMillis();
        print(ww.counter, ww.wage, usedMemoryAfter-usedMemoryBefore, stop - start);

//        start = System.currentTimeMillis();
//        Edge e;
//        TNode p;
//        Queue Q = new Queue(m);
//        MSTree T = new MSTree(n);
//        MSTree G = new MSTree(n);
//
//
//        for (Edge elem : l) {
//            Edge temp = new Edge();
//            temp.v1 = elem.v1;
//            temp.v2 = elem.v2;
//            temp.priority = elem.priority;
//            G.addEdge(temp);
//        }
//
//        boolean[] visited = new boolean[n];
//        int v = 0;
//        visited[v] = true;
//
//        for (int i = 1; i < n; i++) {
//            p = G.tab[v];
//            while (p != null) {
//                if (!visited[p.v]) {
//                    e = new Edge();
//                    e.v1 = v;
//                    e.v2 = p.v;
//                    e.priority = p.weight;
//                    Q.insert(e);
//                }
//                p = p.next;
//            }
//            do {
//                e = new Edge();
//                e.v1 = Q.top().v1;
//                e.v2 = Q.top().v2;
//                e.priority = Q.top().priority;
//                Q.pop();
//            } while (visited[e.v2]);
//
//            T.addEdge(e);
//            visited[e.v2] = true;
//            v = e.v2;
//        }
//        stop = System.currentTimeMillis();
//        print(0, T.weight, 0, stop - start);
//
//        start = System.currentTimeMillis();
//        Q = new Queue(m);
//        T = new MSTree(n);
//        DSStruct Z = new DSStruct(n);
//
//
//        for (int i = 0; i < n; i++)
//            Z.MakeSet(i);
//
//        for (Edge elem : l) {
//            Q.insert(elem);
//        }
//
//        for (int i = 0; i < n - 1; i++) {
//            do {
//                e = new Edge();
//                e.v1 = Q.top().v1;
//                e.v2 = Q.top().v2;
//                e.priority = Q.top().priority;
//                Q.pop();
//            } while (Z.FindSet(e.v1) == Z.FindSet(e.v2));
//            T.addEdge(e);
//            Z.UnionSets(e);
//        }
//        stop = System.currentTimeMillis();
//
//        ALaEuler ala = new ALaEuler(T.edgeList,n);
    }

    public static void print(int k, int W, long M, long t) {
        System.out.println(k + " " + W + " " + M + " " + t);
    }
}
