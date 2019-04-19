import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String args[]) throws IOException {

        String type = args[0];
        if(!(type.contains("-p") || (type.contains( "-k")))) return;

        int n, m;
        Edge e;
        TNode p;
        int i, v;
        List<Edge> edgeList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj liczbe wierzcholkow");
        n = Integer.parseInt(br.readLine());
        System.out.println("Podaj liczbe krawedzi");
        m = Integer.parseInt(br.readLine());

        System.out.println("Podaj wierzcholki wedlug formatu: v1 v2 w (enter i kolejny)");
        for(i = 0; i<m; i++)
        {
            String temp = br.readLine();
            String[] tab = temp.split(" ");
            Edge edge = new Edge();
            edge.v1 = Integer.parseInt(tab[0]);
            edge.v2 = Integer.parseInt(tab[1]);
            edge.priority = Integer.parseInt(tab[2]);
            edgeList.add(edge);
        }

        Queue Q = new Queue(m);
        MSTree T = new MSTree(n);
        MSTree G = new MSTree(n);
        DSStruct Z = new DSStruct(n);


        if(type.contains("-p")) {

            for(Edge elem: edgeList) {
                G.addEdge(elem);
            }

            boolean[] visited = new boolean[n];
            v = 0;
            visited[v] = true;

            for (i = 1; i < n; i++) {
                p = G.tab[v];
                while (p != null) {
                    if (!visited[p.v]) {
                        e = new Edge();
                        e.v1 = v;
                        e.v2 = p.v;
                        e.priority = p.weight;
                        Q.insert(e);
                    }
                    p = p.next;
                }
                do {
                    e = new Edge();
                    e.v1 = Q.top().v1;
                    e.v2 = Q.top().v2;
                    e.priority = Q.top().priority;
                    Q.pop();
                } while (visited[e.v2]);

                T.addEdge(e);
                visited[e.v2] = true;
                v = e.v2;
            }
        }
        else if(type.contains("-k"))
        {
            for(i = 0; i < n; i++)
                Z.MakeSet(i);

            for(Edge elem: edgeList) {
                Q.insert(elem);
            }

            for(i = 0; i< n-1; i++)
            {
                do{
                    e = new Edge();
                    e.v1 = Q.top().v1;
                    e.v2 = Q.top().v2;
                    e.priority = Q.top().priority;
                    Q.pop();
                } while(Z.FindSet(e.v1) == Z.FindSet(e.v2));
                T.addEdge(e);
                Z.UnionSets(e);
            }
        }
        T.print();
    }
}
