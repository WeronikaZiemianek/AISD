import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomErr {

    String s = "";
    int wage = 0;
    int counter = 0;
    List<RandromErrNode> vertices = new LinkedList<>();
    List<RandromErrNode> unsettled = new LinkedList<>();
    RandromErrNode node;

    public void addVertex(int n) {
        for (int i = 0; i < n; ++i) {
            node = new RandromErrNode(i, n);
            vertices.add(node);
            unsettled.add(node);
        }
    }

    public void addEdge(int u, int v, int w) {
        vertices.get(u).addNeighbourWages(v, w);
        vertices.get(v).addNeighbourWages(u, w);
    }

    public void randomErr(int n) {
        RandromErrNode temp;

        List<RandromErrNode> settled = new LinkedList<>();

        Random random = new Random();

        int rand = random.nextInt(n - 1);
        RandromErrNode source = vertices.get(rand);
        temp = new RandromErrNode(-1, 0);
        counter++;

        while (unsettled.size() > 1) {
            counter++;
            unsettled.remove(source);
            settled.add(source);

            rand = random.nextInt(unsettled.size());
            temp = unsettled.get(rand);
            counter++;

            s += source.id + " ";
            wage += source.neighbourhood[temp.id];

            source = temp;
        }

        s += source.id;
        wage += source.neighbourhood[temp.id];
    }
}
