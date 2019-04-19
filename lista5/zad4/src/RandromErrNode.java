public class RandromErrNode {

    int id;
    int[] neighbourhood;

    public RandromErrNode(int id, int n){
        this.id=id;
        neighbourhood = new int[n];
    }

    public void addNeighbourWages(int id, int w){
        neighbourhood[id]=w;
    }

}
