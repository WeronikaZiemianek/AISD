#include <iostream>
#include <stdlib.h>
#include <string>
#include <time.h>
#include <fstream>
#include <windows.h>

using namespace std;

int compCounter = 0;

int binarySearch(int n[], int l, int r, int v) {

	int m = (l + r) / 2;

	compCounter++;
	if ((l == r - 1) && (n[l] != v))
		return 0;

	compCounter++;
	if (v == n[m])
		return 1;

	else {
		compCounter++;
		if (v<n[m])
			return binarySearch(n, l, m, v);
		else
			return binarySearch(n, m, r, v);
	}
}

int main(int argc, char** argv) {

	int *tab;
	int howMuchTime = 0;
	int v = 5;

	if (argc > 1)
		v = atoi(argv[1]);

	fstream file;
	file.open("wyniki.txt", ios::out);

	for (int n = 1000; n <= 100000; n += 1000)
	{
		compCounter = 0;
		tab = new int[n];

		for(int ele = 0; ele < n; ele++)
		{
			tab[ele] = ele;
		}



		howMuchTime = 0;
		clock_t start = clock();
		cout << binarySearch(tab, 0, n, v) << endl;
		howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);

		delete[] tab;



		/*file << "Rozmiar tablicy: " << n << endl
			<< "Porównania: " << compCounter << endl
			<< "Czas " << howMuchTime << endl << endl;*/
			file << howMuchTime << endl;
	}
	return 0;
}
