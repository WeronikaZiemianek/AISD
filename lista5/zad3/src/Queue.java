public class Queue {

    Edge[] Q;
    protected int queueElemCounter;

    public Queue(int m) {

        Q = new Edge[m];
        for (int i = 0; i < m; i++)
            Q[i] = new Edge();

        queueElemCounter = 0;

    }

    public void empty() {
        if (queueElemCounter == 0) {
            System.out.println("1");
            return;
        }
        System.out.println("0");
    }

    public Edge top() {
        return Q[0];
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public void heapify(int i) {

        int l = left(i);
        int r = right(i);
        int minIdex = i;
        int minPrioElem = Q[i].priority;

        if (l < queueElemCounter && Q[l].priority >= 0 && minPrioElem > Q[l].priority) {
            minIdex = l;
            minPrioElem = Q[l].priority;
        }

        if (r < queueElemCounter && Q[r].priority >= 0 && minPrioElem > Q[r].priority) {
            minIdex = r;
        }

        //jeśli rodzic nie jest najmniejsztm priorytetem

        if (minIdex != i) {
            int buf = Q[i].v1;
            int buf2 = Q[i].v2;
            Q[i].v1 = Q[minIdex].v1;
            Q[i].v2 = Q[minIdex].v2;
            Q[minIdex].v1 = buf;
            Q[minIdex].v2 = buf2;

            buf = Q[i].priority;
            Q[i].priority = Q[minIdex].priority;
            Q[minIdex].priority = buf;
            heapify(minIdex);
        }

    }

    public void print() {
        String s = "";
        for (int i = 0; i < queueElemCounter; i++)
            s += "(" + Q[i].v1+","+Q[i].v2 + "," + Q[i].priority + ")";
        System.out.println(s);
    }



    public void buildHeap() {
        for (int k = 0; k < queueElemCounter / 2 + 1; k++) {
            heapify(k);

        }
    }


    public void pop() {

        if (queueElemCounter == 0)
            return;

        Q[0].v1 = Q[queueElemCounter - 1].v1;
        Q[0].v2 = Q[queueElemCounter - 1].v2;
        Q[0].priority = Q[queueElemCounter - 1].priority;

        queueElemCounter--;

        buildHeap();
    }

    public void insert(Edge e) {
        int i, j;

        i = queueElemCounter++;
        j = parent(i);

        while (i > 0 && Q[j].priority > e.priority) {
            Q[i].priority = Q[j].priority;
            Q[i].v1 = Q[j].v1;
            Q[i].v2 = Q[j].v2;
            i = j;
            j = parent(i);
        }

        Q[i] = e;
    }


    public void decreaseKey(int x,int y)
    {
        for(int i=0; i<queueElemCounter; i++){
          if(Q[i].v1==x && Q[i].v2==y){
              Q[i].priority--;
              buildHeap();
              break;
          }
        }
    }

    public Edge extractMax(){
        Edge max;

        if(queueElemCounter==0)
            return null;

            max=Q[0];
            Q[0].v1=Q[queueElemCounter].v1;
            Q[0].v2=Q[queueElemCounter].v2;
            Q[0].priority=Q[queueElemCounter].priority;
            queueElemCounter--;
            heapify(0);
            return max;

    }
}
