#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>

struct node
{
	struct node* next;
	int val;
};

struct list
{
	struct node* tail;
	struct node* head;
};

void clear(struct list* lista)
{
	if (lista->tail != NULL)
	{
		struct node* temp = lista->tail;

		while (temp->next != NULL)
		{
			temp = temp->next;
			free(lista->tail);
			lista->tail = temp;
		}
	}
}

void deleteList(struct list* lista)
{
	if (lista != NULL)
	{
		clear(lista);
		free(lista);
	}
}

struct list* initLista(struct list* lista) // zapsute
{
	deleteList(lista);
	lista = (struct list*)malloc(sizeof(struct list));
	lista->tail = NULL;
	lista->head = NULL;
	return lista;

}

void push(int wartosc, struct list* lista)
{
	struct node* el = (struct node*)malloc(sizeof(struct node));
	el->val = wartosc;

	if (lista->tail == NULL)
	{
		el->next = NULL;
		lista->tail = el;
		lista->head = el;
	}
	else
	{
		el->next = lista->tail;
		lista->tail = el;
	}
}

void specificWrapper(struct list* lista)
{
	printf("Podaj index do pobrania\n");
	int val;
	scanf("%d", &val);
	howLongSpecific(lista,val);
}

void fillList(struct list* lista)
{
	for (int i = 0; i < 1000; i++)
		push(rand() % 100, lista);
}

struct node* printList(struct list* lista)
{
    struct node* temp = lista->tail;
	while (temp->next != NULL)
	{
		printf("%d ", temp->val);
		temp = temp->next;
	}
	printf("%d ", temp->val);
}


void findElement(int wartosc, struct list* lista)
{
	struct node* temp;

		temp = lista->tail;
		while (temp != lista->head)
		{
			if (temp->val == wartosc)
				break;
			if (temp->next == NULL)
			{
				printf("Value does not exist.");
			}
			temp = temp->next;
		}

}

void howLongRandom(struct list* lista)
{
	printf("Measurement for a random value\n");
	int howMuchTime = 0;
	clock_t start = clock();
	srand(time(NULL));
	for (int i = 0; i < 100000; i++)
	{
		ElementAt(rand() % 1000, lista);
	}
	howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
	printf("%d ms\n", howMuchTime);
}


void howLongSpecific(struct list* lista, int wartosc)
{
	printf("Measurement for a specific value\n");
	int howMuchTime = 0;
	clock_t start = clock();
	for (int i = 0; i < 100000; i++)
	{
		ElementAt(wartosc, lista);
	}
	howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
	printf("%d ms\n", howMuchTime);
}


struct list* merge(struct list* lista1, struct list* lista2)
{
	if (lista1 != NULL && lista2 != NULL)
	{
		struct node* temp = lista2->tail;
		while (temp != NULL)
		{
			push(temp->val, lista1);
			temp = temp->next;
		}

		return lista1;
	}
	else
	{
		if (lista1 == NULL)
		{
			if (lista2 == NULL)
				return initLista(lista1);
			else
				return lista2;
		}
		else
			return lista1;
	}
}

int ElementAt(int index,struct list* lista)
{
    struct node* temp = lista->tail;
	for(int i=0;i<index -1;i++)
    {
            temp = temp-> next;
    }
    return temp->val;
}


int main()
{
    srand(time(NULL));
	struct list* lista = NULL;
	lista = initLista(lista);
	struct list* lista2 = NULL;
	lista2 = initLista(lista2);

	unsigned char znak;

	do
	{
		printf("Enter the number: \n\n");
		printf("Lista 1 \n");
		printf("1. Fill list\n");
		printf("2. Measure the time for a specific element\n");
		printf("3. Measure the time for a random element\n");
		printf("Lista 2 \n");
		printf("4. Fill list\n");
		printf("5. Merge List 1 with List 2\n");
		printf("6. Print list\n");


		znak = _getch();

		while (_kbhit())
		{
			znak = _getch();
		}
		switch (znak)
		{
		case '1':
			fillList(lista);
			break;
		case '2':
			specificWrapper(lista);
			break;
		case '3':
			howLongRandom(lista);
			break;
		case '4':
			fillList(lista2);
			break;
		case '5':
			merge(lista, lista2);
            break;
        case '6':
            printList(lista);
            break;
	}
	} while (znak != 27);

	clear(lista);
	deleteList(lista);
	return 0;
}


