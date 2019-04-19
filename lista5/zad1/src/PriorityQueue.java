public class PriorityQueue {

    protected int queueElemCounter;
    PriorityQueueElem[] Q;

    public PriorityQueue(int max) {

        Q = new PriorityQueueElem[max];
        for (int i = 0; i < max; i++)
            Q[i] = new PriorityQueueElem();

        queueElemCounter = 0;
    }

    public boolean empty() {
        return queueElemCounter == 0;
    }

    public void insert(int x, int p) {
        int i, j;

        i = queueElemCounter++;
        j = parent(i);

        while (i > 0 && Q[j].priority > p) {
            Q[i].priority = Q[j].priority;
            Q[i].value = Q[j].value;
            i = j;
            j = parent(i);
        }

        Q[i].priority = p;
        Q[i].value = x;
    }

    public void pop() {

        if (queueElemCounter == 0)
            return;

        Q[0].value = Q[queueElemCounter - 1].value;
        Q[0].priority = Q[queueElemCounter - 1].priority;

        queueElemCounter--;

        buildHeap();
    }

    public int frontVal() {
        return queueElemCounter != 0 ? Q[0].value : -1;
    }


    public int frontPrio() {
        return queueElemCounter != 0 ? Q[0].priority : -1;
    }

    public void print() {
        for (int i = 0; i < queueElemCounter; i++) {
            String toPrint = String.format("(%d %d)", Q[i].value, Q[i].priority);
            if (i + 1 < queueElemCounter)
                toPrint += ",";
            System.out.print(toPrint);
        }
        System.out.println();
    }


    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void heapify(int i) {

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
            int buf = Q[i].value;
            Q[i].value = Q[minIdex].value;
            Q[minIdex].value = buf;

            buf = Q[i].priority;
            Q[i].priority = Q[minIdex].priority;
            Q[minIdex].priority = buf;
            heapify(minIdex);
        }
    }

    public void priority(int x, int p, int i) {
        if (i < queueElemCounter) {
            if (Q[i].priority < p) {

                if (Q[i].value == x) {
                    Q[i].value = x;
                    Q[i].priority = p;

                }

                priority(x, p, left(i));
                priority(x, p, right(i));
            }

        }
        buildHeap();
    }

    public void decreaseKey(int x) {
        for (int i = 0; i < queueElemCounter; i++) {
            if (Q[i].value == x) {
                Q[i].priority--;
                buildHeap();
                break;
            }
        }
    }

    public int extractMax() {
        int max;

        if (queueElemCounter == 0)
            return -1;

        max = Q[0].value;
        Q[0].value = Q[queueElemCounter].value;
        Q[0].priority = Q[queueElemCounter].priority;
        queueElemCounter--;
        heapify(0);
        return max;
    }

    private void buildHeap() {
        for (int k = 0; k < queueElemCounter / 2 + 1; k++) {
            heapify(k);
        }
    }


}
