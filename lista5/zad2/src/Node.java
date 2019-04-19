import java.util.LinkedList;
import java.util.List;

public class Node {

    int id;
    int prev=Integer.MAX_VALUE;
    int dist = Integer.MAX_VALUE;
       PriorityQueue neighbourhood;

    public Node(int id, int size){
        this.id=id;
        neighbourhood = new PriorityQueue(size);
    }

    public void addNeighbour(int neighbourID, int distance){
        neighbourhood.insert(neighbourID , distance);
    }

}
