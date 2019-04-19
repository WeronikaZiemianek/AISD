/**
 * Created by Zaket on 29.05.2018.
 */
public class DSStruct {
    public DSNode[] Z;

    public DSStruct(int n) {
        Z = new DSNode[n];
        for (int i = 0; i < n; i++)
            Z[i] = new DSNode();
    }

    public void MakeSet(int v) {
        Z[v].up = v;
        Z[v].rank = 0;
    }

    public int FindSet(int v) {
        if (Z[v].up != v)
            Z[v].up = FindSet(Z[v].up);
        return Z[v].up;
    }

    public void UnionSets(Edge e) {
        int ru, rv;

        ru = FindSet(e.v1);
        rv = FindSet(e.v2);

        if (ru != rv) {
            if (Z[ru].rank > Z[rv].rank)
                Z[rv].up = ru;
            else {
                Z[ru].up = rv;
                if (Z[ru].rank == Z[rv].rank) Z[rv].rank++;
            }
        }
    }
}
