#include <iostream>
#include <stdlib.h>
#include <string>
#include <time.h>
#include <fstream>

using namespace std;

int compCounter;
int swapCounter;

void insertSortAsc(int tab[], int size)
{
    int i, key;


    for (int j = 1; j < size; j++)
    {
        key = tab[j];
        i = j - 1;
        while ((i >= 0) && (tab[i] > key))
        {
            cerr << "cmp : " << tab[i] << " > " << key << "\n";
            tab[i + 1] = tab[i];
            cerr << "swap : " << tab[i] << " " << tab[i + 1] << "\n";
            i--;
            compCounter++;
            swapCounter++;
        }
        compCounter++;

        tab[i + 1] = key;

    }
}

void insertSortDesc(int tab[], int size)
{
    int i, key;

    for (int j = 1; j < size; j++)
    {
        key = tab[j];
        i = j - 1;
        while ((i >= 0) && (tab[i] < key))
        {
            cerr << "cmp : " << tab[i] << " > " << key << "\n";
            tab[i + 1] = tab[i];
            cerr << "swap : " << tab[i] << " " << tab[i + 1] << "\n";
            i--;
            compCounter++;
            swapCounter++;
        }
        compCounter++;
        tab[i + 1] = key;
    }
}

void quickSortDesc(int tab[], int left, int right) {
    int i = left;
    int j = right;
    int temp;
    int pivot = tab[(left + right) / 2];

    while (i <= j) {
        while (tab[i] < pivot)
        {
            cerr << "cmp : " << tab[i] << " < " << pivot << "\n";
            i++;
            compCounter++;
        }
        compCounter++;
        while (tab[j] > pivot)
        {
            cerr << "cmp : " << tab[j] << " > " << pivot << "\n";
            j--;
            compCounter++;
        }
        compCounter++;

        if (i <= j) {
            swapCounter++;
            cerr << "swap : " << tab[i] << " " << tab[j] << "\n";
            temp = tab[i];
            tab[i] = tab[j];
            tab[j] = temp;
            i++;
            j--;
        }
    };

    if (left < j + 1)
        quickSortDesc(tab, left, j);
    if (i < right)
        quickSortDesc(tab, i, right);
}

void quickSortAsc(int tab[], int left, int right) {
    int i = left;
    int j = right;
    int temp;
    int pivot = tab[(left + right) / 2];

    while (i <= j) {
        while (tab[i] > pivot)
        {
            cerr << "cmp : " << tab[i] << " > " << pivot << "\n";
            i++;
            compCounter++;
        }
        compCounter++;
        while (tab[j] < pivot)
        {
            cerr << "cmp : " << tab[i] << " < " << pivot << "\n";
            j--;
            compCounter++;
        }
        compCounter++;
        if (i <= j) {
            swapCounter++;
            cerr << "swap : " << tab[i] << " " << tab[j] << "\n";
            temp = tab[i];
            tab[i] = tab[j];
            tab[j] = temp;
            i++;
            j--;
        }
    };

    if (left < j + 1)
        quickSortAsc(tab, left, j);
    if (i < right)
        quickSortAsc(tab, i, right);
}

void  mergeAsc(int pocz, int sr, int kon, int tab[], int t[])
{
    int i, j, q;
    for (i = pocz; i <= kon; i++) 
        t[i] = tab[i];
    i = pocz; j = sr + 1; q = pocz;    
    while (i <= sr && j <= kon) {         
        cerr << "cmp : " << tab[i] << " < " << t[j] << "\n";
        if (t[i] < t[j])    
        {
            tab[q++] = t[i++];
            cerr << "swap : " << tab[i++] << "\n";
        }
        else
        {
            tab[q++] = t[j++];
            cerr << "swap : " << tab[i++] << "\n";
        }
        compCounter++;
        swapCounter++;
    }
    while (i <= sr)
    {
        cerr << "swap : " << tab[i++] << "\n";
        tab[q++] = t[i++];
        swapCounter++;
    }

}
void mergeSortAsc(int pocz, int kon,int tab[], int t[])
{
    int sr;
    if (pocz < kon)
    {
        sr = (pocz + kon) / 2;
        mergeSortAsc(pocz, sr,tab,t);    
        mergeSortAsc(sr + 1, kon,tab,t);  
        mergeAsc(pocz, sr, kon,tab,t);   
    }
}

void  mergeDesc(int pocz, int sr, int kon,int tab[],int t[])
{
    int i, j, q;
    for (i = pocz; i <= kon; i++) 
        t[i] = tab[i];
    i = pocz; j = sr + 1; q = pocz;                 
    while (i <= sr && j <= kon) {         
        cerr << "cmp : " << tab[i] << " > " << t[j] << "\n";
        if (t[i] > t[j])       
        {
            tab[q++] = t[i++];
            cerr << "swap : " << tab[i++] << "\n";
        }
        else
        {
            tab[q++] = t[j++];
            cerr << "swap : " << tab[i++] << "\n";
        }
        compCounter++;
        swapCounter++;
    }
    while (i <= sr)
    {
        swapCounter++;
        cerr << "swap : " << tab[i++] << "\n";
        tab[q++] = t[i++];
    }

}
void mergeSortDesc(int pocz, int kon,int tab[], int t[])
{
    int sr;
    if (pocz < kon)
    {
        sr = (pocz + kon) / 2;
        mergeSortDesc(pocz, sr, tab, t);    
        mergeSortDesc(sr + 1, kon, tab, t);  
        mergeDesc(pocz, sr, kon,tab,t);   
    }
}

void swap(int* a, int* b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
    swapCounter++;
}

int partitionAsc(int tab[], int pocz, int kon, int* leftPivot)
{
    if (tab[pocz] > tab[kon])
        swap(&tab[pocz], &tab[kon]);
    compCounter++;
    // p is the left pivot, and q is the right pivot.
    int j = pocz + 1;
    int g = kon - 1, k = pocz + 1, p = tab[pocz], q = tab[kon];
    while (k <= g) {
        compCounter++;

      
        compCounter++;
        if (tab[k] < p) {
            swap(&tab[k], &tab[j]);
            j++;
        }

        else if (tab[k] >= q) {
            compCounter++;
            while (tab[g] > q && k < g)
                g--;
            swap(&tab[k], &tab[g]);
            g--;
            compCounter += 2;
            if (tab[k] < p) {
                swap(&tab[k], &tab[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

    
    swap(&tab[pocz], &tab[j]);
    swap(&tab[kon], &tab[g]);

    // returning the indeces of the pivots.
    *leftPivot = j; // because we cannot return two elements
                    // from a function.

    return g;
}
void DualPivotQuickSortAsc(int tab[], int pocz, int kon)
{
    if (pocz < kon) {
        
        int leftPivot, rightPivot;
        rightPivot = partitionAsc(tab, pocz, kon, &leftPivot);
        DualPivotQuickSortAsc(tab, pocz, leftPivot - 1);
        DualPivotQuickSortAsc(tab, leftPivot + 1, rightPivot - 1);
        DualPivotQuickSortAsc(tab, rightPivot + 1, kon);
    }
}

int partitionDesc(int tab[], int pocz, int kon, int* leftPivot)
{
    compCounter++;
    if (tab[pocz] < tab[kon])
        swap(&tab[pocz], &tab[kon]);
    
    int j = pocz + 1;
    int g = kon - 1, k = pocz + 1, p = tab[pocz], q = tab[kon];
    while (k <= g) {
        compCounter++;

        
        compCounter++;
        if (tab[k] > p) {
            swap(&tab[k], &tab[j]);
            j++;
        }

        else if (tab[k] <= q) {
            compCounter++;
            while (tab[g] < q && k < g)
                g--;
            swap(&tab[k], &tab[g]);
            g--;
            compCounter += 2;
            if (tab[k] > p) {
                swap(&tab[k], &tab[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

  
    swap(&tab[pocz], &tab[j]);
    swap(&tab[kon], &tab[g]);

    *leftPivot = j; 

    return g;
}
void DualPivotQuickSortDesc(int tab[], int pocz, int kon)
{
    if (pocz < kon) {
     
        int leftPivot, rightPivot;
        rightPivot = partitionDesc(tab, pocz, kon, &leftPivot);
        DualPivotQuickSortDesc(tab, pocz, leftPivot - 1);
        DualPivotQuickSortDesc(tab, leftPivot + 1, rightPivot - 1);
        DualPivotQuickSortDesc(tab, rightPivot + 1, kon);
    }
}


bool IsSorted(int tab[], int siz, int order)
{
    for (int i = 0; i < siz - 1; i++)
        if (order == 1)
        {
            if (tab[i] < tab[i + 1])
                return false;
        }
        else
        {
            if (tab[i] > tab[i + 1])
                return false;
        }
    return true;
}
void showResult(int tab[], int dl)
{
    cout << dl << "\n";
    for (int i = 0; i < dl; i++)
        cout << tab[i] << "  ";
    cout << endl;
}

int main(int argc, char **argv)
{
    string type, comp, filename;
    int k;
    int dl;
    int buff;
    compCounter = 0;
    swapCounter = 0;
    int * tab;
    int * t;

    double howMuchTime = 0.00;

    string temp;
    for (int i = 0; i < argc - 2; i++)
    {
        temp = argv[i];
        if (temp == "--type")
        {
            type = argv[i + 1];
            i++;
        }
        if (temp == "--comp")
        {
            comp = argv[i + 1];
            buff = i + 2;
        }
        if (temp == "--stat")
        {
            filename = argv[i + 1];
            k = atoi(argv[i + 2]);
        }
    }
    int order;
    if (comp == ">=")
        order = 1;
    else
        order = 0;

    if (filename == "")
    {
        if (argc > 5)
        {
            dl = atoi(argv[buff]);
            tab = new int[dl];
            t = new int[dl];

            for (int i = 6; i < argc; i++)
                tab[i - 6] = atoi(argv[i]);

            if (type == "insert")
            {
                if (order != 1)
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    insertSortAsc(tab, dl);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                }
                else
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    insertSortDesc(tab, dl);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
            }
            else if (type == "quick")
            {
                if (order == 1)
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    quickSortAsc(tab, 0, dl - 1);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
                else
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    quickSortDesc(tab, 0, dl - 1);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
            }
            else if (type == "merge")
            {
                if (order != 1)
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    mergeSortAsc(0, dl -1,tab, t);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
                else
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    mergeSortDesc(0, dl -1,tab, t);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
            }
            else if (type == "dual")
            {
                if (order != 1)
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    DualPivotQuickSortAsc(tab, 0, dl -1);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
                else
                {
                    howMuchTime = 0;
                    clock_t start = clock();
                    DualPivotQuickSortDesc(tab, 0, dl -1);
                    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                }
            }

            if (IsSorted(tab, dl, order))
            {
                cout << "Porownania: " << compCounter << "\n";
                cout << "Podmiany: " << swapCounter << "\n";
                cout << "Czas: " << howMuchTime << "ms \n";
                showResult(tab, dl);
            }
            else
                cout << "nie posz³o";

            delete[] tab;
            delete[] t;
        }
    }
    else
    {
        fstream file;
        file.open(filename.c_str(), ios::out);

        file << "Type: " << type << endl
            << "Comp:" << comp << endl
            << "Liczba niezależnych powtórzeń: " << k << endl << endl;

        srand(time(NULL));

        for (int i = 100; i <= 10000; i += 100)
        {
            compCounter = 0;
            swapCounter = 0;
            howMuchTime = 0;

            int* tabCpy = new int[i];
            tab = new int[i];
            t = new int[i];

            for (int w = 0; w < k; w++)
            {

                for (int j = 0; j < i; j++)
                {
                    tab[j] = rand() % 10000;
                    tabCpy[j] = tab[j];
                }


                if (type == "insert")
                {
                    if (order == 1)
                    {
                        clock_t start = clock();
                        insertSortAsc(tabCpy, i);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                    }
                    else
                    {
                        clock_t start = clock();
                        insertSortDesc(tabCpy, i);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                    }
                }
                else if (type == "quick")
                {
                    if (order == 1)
                    {
                        clock_t start = clock();
                        quickSortAsc(tabCpy, 0, i - 1);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                    }
                    else
                    {
                        clock_t start = clock();
                        quickSortDesc(tabCpy, 0, i - 1);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
                    }
                }
                else if (type == "merge")
                {
                    if (order != 1)
                    {
                        clock_t start = clock();
                        mergeSortAsc(0, i - 1, tabCpy, t);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                    }
                    else
                    {
                        clock_t start = clock();
                        mergeSortDesc(0, i - 1, tabCpy, t);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                    }
                }
                else if (type == "dual")
                {
                    if (order != 1)
                    {
                        clock_t start = clock();
                        DualPivotQuickSortAsc(tabCpy, 0, i - 1);
                        howMuchTime = howMuchTime + ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                    }
                    else
                    {
                        clock_t start = clock();
                        DualPivotQuickSortDesc(tabCpy, 0, i - 1);
                        howMuchTime = ((1000 * (clock() - start)) / (double)CLOCKS_PER_SEC);
                    }
                }
            }

            file << "Rozmiar tablicy: " << i << endl
            << "Porównania: " << compCounter << endl
            << "Zamiany: " << swapCounter << endl
            << "Czas " << howMuchTime << endl << endl;

           // file << swapCounter << endl;
            delete[] tabCpy;
            delete[] tab;
            delete[] t;
        }

        file.close();
    }

    return 0;
}
