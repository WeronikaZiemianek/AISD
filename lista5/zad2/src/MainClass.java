import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

    public static void main(String args[]) throws IOException {

        int n;
        int m;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj liczbe wierzcholkow");
        n = Integer.parseInt(br.readLine());
        System.out.println("Podaj liczbe krawedzi");
        m = Integer.parseInt(br.readLine());

        Graph graph = new Graph();

        graph.addVertex(n);

        System.out.println("Podaj wierzcholki wedlug formatu: v1 v2 w (enter i kolejny)");
        for(int i = 0; i<m; i++)
        {
            String temp = br.readLine();
            String[] tab = temp.split(" ");
            graph.addEdge(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]),Integer.parseInt(tab[2]));
        }

        System.out.println("Podaj wierzcholek startowy");
        int start = Integer.parseInt(br.readLine());

        if(start > n){
            System.out.println("Podano za duzy wierzcholek startowy");
            return;
        }

        graph.findAllShortestPaths(start, n);
    }
}
