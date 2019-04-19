import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Graph {

    List<Node> vertices;
    Node node;


    public Graph() {
        vertices = new LinkedList<>();
    }

    public void addVertex(int n) {
        for (int i = 0; i < n; ++i) {
            node = new Node(i, n);
            vertices.add(node);
        }
    }

    public void addEdge(int u, int v, int w) {
        OptionalInt indexU = IntStream.range(0, vertices.size())
                .filter(i -> vertices.get(i).id == u)
                .findFirst();
        OptionalInt indexV = IntStream.range(0, vertices.size())
                .filter(i -> vertices.get(i).id == v)
                .findFirst();


        if (indexU != OptionalInt.empty())
            vertices.get(indexU.getAsInt()).addNeighbour(v, w);
        if (indexV != OptionalInt.empty())
            vertices.get(indexV.getAsInt()).addNeighbour(u, w);
    }

    public void dijkstraShortestPath(int start, int end, int n) {

        Node source = vertices.get(start);

        PriorityQueue settled = new PriorityQueue(n);
        PriorityQueue unsettled = new PriorityQueue(n);
        source.dist = 0;

        unsettled.insert(source.id, source.dist);
        settled.insert(source.id, source.dist);


        while (settled.queueElemCounter < vertices.size()) {

            unsettled.pop();
            for (int c = 0; c < source.neighbourhood.queueElemCounter; c++) {
                PriorityQueueElem elem = source.neighbourhood.Q[c];
                boolean isIn = false;

                for (int i = 0; i < settled.queueElemCounter; i++) {
                    if (settled.Q[i].value == elem.value) {
                        isIn = true;
                        break;
                    }
                }

                if (!isIn)
                 {
                    for (int i = 0; i < unsettled.queueElemCounter; i++) {
                        if (unsettled.Q[i].value == elem.value) {
                            isIn = true;
                            break;
                        }
                    }
                     if (!isIn)
                         unsettled.insert(elem.value, elem.priority);

                }
                if ((source.dist + elem.priority) < vertices.get(elem.value).dist) {
                    vertices.get(elem.value).dist = source.dist + elem.priority;
                    vertices.get(elem.value).prev = source.id;
                }

            }

            settled.insert(unsettled.frontVal(), unsettled.frontPrio());

            OptionalInt x = IntStream.range(0, vertices.size())
                    .filter(i -> vertices.get(i).id == unsettled.Q[0].value)
                    .findFirst();

            if (x != OptionalInt.empty())
                source = vertices.get(x.getAsInt());
        }
    }

    public void findAllShortestPaths(int start, int n) {


        String pathString = "";

        for(int i = 0; i < n; i++) {
            if (!vertices.get(i).neighbourhood.empty())
                dijkstraShortestPath(start, i, n);
            System.out.println(i+ " "+ vertices.get(i).dist);
        }
    }
}
