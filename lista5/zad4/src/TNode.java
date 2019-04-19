public class TNode {
    public int v;
    public int weight;
    public TNode next;

    public TNode(TNode next) {
        this.next = next;
    }
}
