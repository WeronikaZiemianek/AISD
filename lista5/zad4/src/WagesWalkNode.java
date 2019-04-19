

public class WagesWalkNode {

    int id;
    int dist = Integer.MAX_VALUE;
    PriorityQueue neighbourhood;

    public WagesWalkNode(int id, int size){
        this.id=id;
        neighbourhood = new PriorityQueue(size);
    }

    public void addNeighbour(int neighbourID, int distance){
        neighbourhood.insert(neighbourID , distance);
    }

}
