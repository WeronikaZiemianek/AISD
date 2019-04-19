import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WagesWalk {

    String s = "";
    int wage = 0;
    int counter = 0;
    List<WagesWalkNode> vertices = new LinkedList<>();
    List<WagesWalkNode> unsettled = new LinkedList<>();
    WagesWalkNode node;

    public void addVertex(int n) {
        for (int i = 0; i < n; ++i) {
            node = new WagesWalkNode(i, n);
            vertices.add(node);
            unsettled.add(node);
        }
    }

    public void addEdge(int u, int v, int w) {
        vertices.get(u).addNeighbour(v, w);
        vertices.get(v).addNeighbour(u, w);

    }

    public void wagesWalk(int n) {
        int index = 0;

        WagesWalkNode source = vertices.get(index);
        counter++;
        WagesWalkNode temp;
        WagesWalkNode indexNode;
        List<WagesWalkNode> settled = new LinkedList<>();
        boolean isIn = false;
        settled.add(source);
        indexNode = new WagesWalkNode(-1, 0);
        boolean isOk = false;
        while (settled.size() < n - 1) {
            counter++;
            unsettled.remove(source);


            for (int i = 0; i < settled.size(); i++) {
                if (settled.get(i).id == source.id) {
                    isIn = true;
                    counter++;
                    break;
                }
            }
            if (!isIn)
                settled.add(source);

            isIn = false;
            while (!isOk) {
                counter++;
                indexNode.id = source.neighbourhood.Q[0].value;
                indexNode.dist = source.neighbourhood.Q[0].priority;
                source.neighbourhood.pop();
                counter++;
                index = indexNode.id;

                for (int i = 0; i < unsettled.size(); i++) {
                    if (unsettled.get(i).id == index) {
                        isOk = true;
                        counter++;
                        break;
                    }
                }
            }
            isOk = false;

            temp = vertices.get(index);
            counter++;

            s += source.id + " ";
            wage += indexNode.dist;

            source = temp;
        }

        s += source.id;
        wage += indexNode.dist;
    }
}
