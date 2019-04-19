public class HMap {

    RBTree<String>[] tab = new RBTree[10];

    public HMap(){
        for(int i=0;i<tab.length;i++)
            tab[i] = new RBTree<>();
    }

    public void insert(String elem)
    {
        int i = f(elem);
        tab[i].insert(elem);
    }

    public void delete(String elem)
    {
        RBTreeElem<String> temp = tab[f(elem)].find(elem);
        tab[f(elem)].delete(temp);
    }

    public void printFind(String elem){
        tab[f(elem)].printFind(elem);
    }

    public RBTreeElem<String> find(String elem){
        return tab[f(elem)].find(elem);
    }

    public void min(){
        System.out.println("");
    }
    public void max(){
        System.out.println("");
    }
    public void inorder(){
        System.out.println("");
    }
    public void successor(){
        System.out.println("");
    }

    private int f(String s){
        int h = 0;
        int c = 29;
        for(int i=0; i<s.length()-1;i++)
        {
            h = (c * h + s.charAt(i)) % 10;
        }
        return h;
    }

}