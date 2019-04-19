#include <iostream>
#include <stdlib.h>
#include <string>
#include <time.h>
#include <math.h>
#include <fstream>
#include <windows.h>
#include <climits>

using namespace std;

ofstream file;

int swapRandomize = 0;
int comperRandomize = 0;
int swapSelect = 0;
int comperSelect = 0;

void TtoFile(int t[],int size)
{
    for(int i =0;i<size;i++)
    {
        file << t[i] << " ";
    }
    file << endl;
}

void permutacja(int t[], int max)
{
	int i, j, k;

	if (max < 2)
		return;

	/* wyznaczanie pierwszego od prawej elementu
	* mniejszego niz jego sasiad z prawej strony
	*/
	i = max - 1;
	while ((i > 0) && (t[i - 1] >= t[i]))
		i--;

	/* wyznaczanie elementu wiekszego od znalezionego */
	if (i > 0) {
		j = max;
		while ((j > 0) && (t[j - 1] <= t[i - 1]))
			j--;
	}

	/* zamiana miejscami dwoch znalezionych wyzej elementow */
	if ((i > 0) && (j > 0)) {
		k = t[i - 1];
		t[i - 1] = t[j - 1];
		t[j - 1] = k;
	}

	/* odbicie lustrzane szeregu elementow od indeksu i+1 do konca
	tablicy
	*/
	for (i++, j = max; i < j; i++, j--) {
		k = t[i - 1];
		t[i - 1] = t[j - 1];
		t[j - 1] = k;
	}

}

int partitionRandomized(int tab[], int p, int r) {
	int x = tab[r];
	int i = p - 1;
	for (int j = p; j < r; j++) {
		if (tab[j] <= x) {
			i++;
			file << "swap: " << tab[i] << " i " << tab[j] << endl;
			swap(tab[i], tab[j]);
			TtoFile(tab,p);
		}
	}
	file << "swap: " << tab[i + 1] << " i " << tab[r] << endl;
	swap(tab[i + 1], tab[r]);
	TtoFile(tab,p);
	return i + 1;
}


int Randomized_Partition(int tab[], int p, int r) {
	int i = rand() % r + 1; //flag for debugging
	swapRandomize++;
	file << "swap: " << tab[i] << " i " << tab[r] << endl;
	swap(tab[i], tab[r]);
	TtoFile(tab,p);
	return partitionRandomized(tab, p, r);
}

int Randomized_Select(int a[], int p, int r, int i)
{
    file << "k" << i << endl;
	comperRandomize++;
	file << "comper: " << p << " i " << r << endl;
	if (p == r)
		return a[p];
	int q = Randomized_Partition(a, p, r);
	int k = q - p + 1;
	comperRandomize++;
	file << "comper: " << i << " i " << k << endl;
	if (i == k)
		return a[q];
	else if (i < k)
		return Randomized_Select(a, p, q - 1, i);
	comperRandomize++;
	return Randomized_Select(a, q + 1, r, i - k);
}

int partitionSelect(int tab[], int p, int r)
{
	int x = tab[r], i = p;
	for (int j = p; j <= r - 1; j++) {
		file << "comper: " << tab[j] << " i " << x << endl;
		comperSelect++;
		if (tab[j] <= x) {
			swapSelect++;
			file << "swap: " << tab[i] << " i " << tab[j] << endl;
			swap(tab[i], tab[j]);
			TtoFile(tab,p);
			i++;
		}
	}
	swapSelect++;
	file << "swap: " << tab[i] << " i " << tab[r] << endl;
	swap(tab[i], tab[r]);
	TtoFile(tab,p);
	return i;
}


int Select(int tab[], int p, int r, int k)
{
    file << "k" << k << endl;
	// If k is smaller than number of
	// elements in array
	comperSelect++;
	file << "comp: " << k << " i " << r - p + 1 << endl;
	if (k > 0 && k <= r - p + 1) {

		// Partition the array around last
		// element and get position of pivot
		// element in sorted array
		int index = partitionSelect(tab, p, r);

		// If position is same as k
		comperSelect++;
		file << "comp: " << index - p << " i " << k - 1 << endl;
		if (index - p == k - 1)
			return tab[index];

		// If position is more, recur
		// for left subarray
		file << "comper: " << index - p << " i " << k - 1 << endl;
		comperSelect++;
		if (index - p > k - 1)
			return Select(tab, p, index - 1, k);

		// Else recur for right subarray
		return Select(tab, index + 1, r,
			k - index + p - 1);
	}

	// If k is more than number of
	// elements in array
	return INT_MAX;
}

int main(int argc, char** argv)
{
	file.open("log.txt", ios::out);
	srand(time(NULL));


	ofstream out("Project_Data.txt");

	string temp;
	bool IsRandom = true;
	int size = 0;
	int k = 0;

	for (int i = 0; i < argc - 2; i++)
	{
		temp = argv[i];
		if (temp == "-p")
		{
			size = atoi(argv[i + 1]);
			k = atoi(argv[i + 2]);
			IsRandom = true;
		}
		if (temp == "-r")
		{
			size = atoi(argv[i + 1]);
			k = atoi(argv[i + 2]);
			IsRandom = false;
		}
	}
	if (k > size)
	{
		cout << "Podajno bledne dane wejsciowe" << endl;
		return 1;
	}

	int *tab = new int[size];



	if (IsRandom)
	{
		for (int i = 0; i < size; i++)
		{
			tab[i] = i + 1;
			permutacja(tab, i);
		}
	}
	else
	{
		for (int i = 0; i < size; i++)
		{
			tab[i] = rand() % size;
		}
	}

	cout << "RandomizedSelect: " << endl;
	int result = Randomized_Select(tab, 0, size - 1, k);
	cout << "AllSwaps: " << swapRandomize << endl;
	cout << "AllComper: " << comperRandomize << endl;

	cout << "Select: " << endl;
	int result2 = Select(tab, 0, size - 1, k);
	cout << "AllSwaps: " << swapSelect << endl;
	cout << "AllComper: " << comperSelect << endl;


	//cout << result << " " << result2 << endl;

	cout << size << endl << k << endl;

	for (int i = 0; i < size; i++)
	{
		if (tab[i] == result)
			cout << "[" << tab[i] << "] ";
		else
			cout << tab[i] << " ";
	}
	cout << endl;

	// Badanie maksymalnej, średniej i minimalnej liczby porównań dla algorytmów

	swapRandomize = 0;
	swapSelect = 0;
	comperRandomize = 0;
	comperSelect = 0;

	int MinSwapRandomize = INT_MAX;
	int MaxSwapRandomize = 0;
	double AvrSwapRandomize = 0;
	double StdDevSwapRandomize = 0;
	int StdDevSwapRandomizeTab[1000];

	int MinComperRandomize = INT_MAX;
	int MaxComperRandomize = 0;
	double AvrComperRandomize = 0;
	double StdDevComperRandomize = 0;
	int StdDevComperRandomizeTab[1000];

	int MinSwapSelect = INT_MAX;
	int MaxSwapSelect = 0;
	double AvrSwapSelect = 0;
	double StdDevSwapSelect = 0;
	int StdDevSwapSelectTab[1000];

	int MinComperSelect = INT_MAX;
	int MaxComperSelect = 0;
	double AvrComperSelect = 0;
	double StdDevComperSelect = 0;
	int StdDevComperSelectTab[1000];


	for (int i = 0; i < 1000; i++)
	{
		int tempSwap = swapRandomize;
		StdDevSwapRandomizeTab[i] = tempSwap;
		int tempComper = comperRandomize;
		StdDevComperRandomizeTab[i] = tempComper;
		Randomized_Select(tab, 0, size - 1, k);
		if (swapRandomize - tempSwap > MaxSwapRandomize)
			MaxSwapRandomize = swapRandomize - tempSwap;
		if (swapRandomize - tempSwap < MinSwapRandomize)
			MinSwapRandomize = swapRandomize - tempSwap;
		if (comperRandomize - tempComper > MaxComperRandomize)
			MaxComperRandomize = comperRandomize - tempComper;
		if (comperRandomize - tempComper < MinComperRandomize)
			MinComperRandomize = comperRandomize - tempComper;

	}
	AvrSwapRandomize = (double)swapRandomize / 1000;
	AvrComperRandomize = (double)comperRandomize / 1000;

	for (int i = 0; i < 1000; i++) {
		StdDevSwapRandomize += (StdDevSwapRandomizeTab[i] - AvrSwapRandomize)*(StdDevSwapRandomizeTab[i] - AvrSwapRandomize);
		StdDevComperRandomize += (StdDevComperRandomizeTab[i] - AvrComperRandomize)*(StdDevComperRandomizeTab[i] - AvrComperRandomize);

	}
	StdDevSwapRandomize = sqrt(StdDevSwapRandomize / 1000);
	StdDevComperRandomize = sqrt(StdDevSwapRandomize / 1000);

	cout << endl;
	cout << "Randomize Select" << endl
		<< "Swap " << endl
		<< "Min " << MinSwapRandomize << endl
		<< "Max " << MaxSwapRandomize << endl
		<< "Avr " << AvrSwapRandomize << endl
		<< "StdDev " << StdDevSwapRandomize << endl << endl
		<< "Comper" << endl
		<< "Min " << MinComperRandomize << endl
		<< "Max " << MaxComperRandomize << endl
		<< "Avr " << AvrComperRandomize << endl
		<< "StdDev " << StdDevComperRandomize << endl << endl;


	for (int i = 0; i < 1000; i++)
	{
		int tempSwap = swapSelect;
		StdDevSwapSelectTab[i] = swapSelect;
		int tempComper = comperSelect;
		StdDevComperSelectTab[i] = tempComper;
		// wywołanie select jeszcze źle działa
		Select(tab, 0, size - 1, k);
		if (swapSelect - tempSwap > MaxSwapSelect)
			MaxSwapSelect = swapSelect - tempSwap;
		if (swapSelect - tempSwap < MinSwapSelect)
			MinSwapSelect = swapSelect - tempSwap;
		if (comperSelect - tempComper > MaxComperSelect)
			MaxComperSelect = comperSelect - tempComper;
		if (comperSelect - tempComper < MinComperSelect)
			MinComperSelect = comperSelect - tempComper;
	}
	AvrSwapSelect = (double)swapSelect / 1000;
	AvrComperSelect = (double)comperSelect / 1000;

	for (int i = 0; i < 1000; i++) {
		StdDevSwapSelect += (StdDevSwapSelectTab[i] - AvrSwapSelect)*(StdDevSwapSelectTab[i] - AvrSwapSelect);
		StdDevComperSelect += (StdDevComperSelectTab[i] - AvrComperSelect)*(StdDevComperSelectTab[i] - AvrComperSelect);

	}
	StdDevSwapRandomize = sqrt(StdDevSwapRandomize / 1000);
	StdDevComperRandomize = sqrt(StdDevSwapRandomize / 1000);

	cout << endl;
	cout << "Select" << endl
		<< "Swap " << endl
		<< "Min " << MinSwapSelect << endl
		<< "Max " << MaxSwapSelect << endl
		<< "Avr " << AvrSwapSelect << endl
		<< "StdDev " << StdDevSwapSelect << endl << endl
		<< "Comper" << endl
		<< "Min " << MinComperSelect << endl
		<< "Max " << MaxComperSelect << endl
		<< "StdDev " << StdDevComperSelect << endl << endl;


	return 0;
}
