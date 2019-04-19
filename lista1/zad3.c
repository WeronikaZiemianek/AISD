#pragma warning(disable:4996)
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>

struct node
{
	struct node* next;
	struct node* before;
	int val;
};

struct list
{
	struct node* head;
};

void clear(struct list* lista)
{
	if (lista->head != NULL)
	{
		struct node* temp = lista->head;

		while (temp->next != temp->before)
		{
			temp = temp->next;
			free(lista->head);
			lista->head = temp;
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

struct list* initLista(struct list* lista)
{
	deleteList(lista);
	lista = (struct list*)malloc(sizeof(struct list));
	lista->head = NULL;
	return lista;
}

void push(int wartosc, struct list* lista)
{
	struct node* el = (struct node*)malloc(sizeof(struct node));
	el->val = wartosc;

	if (lista->head == NULL)
	{
		lista->head = el;
		el->next = el;
		el->before = el;
	}
	else
	{
		el->next = lista->head->next;
		lista->head->next->before = el;
		lista->head->next = el;
		el->before = lista->head;
		lista->head = el;
	}
}

void pushWrapper(struct list* lista)
{
	printf("Podaje wartoœ do dodania");
	int val;
	scanf("%d", &val);
	push(val, lista);
}

void fillList(struct list* lista)
{
	srand(time(NULL));

	for (int i = 0; i < 1000; i++)
		push(rand()%1000, lista);
}

void findByMoveBack(int val, struct list* lista)
{
	if (lista->head != NULL)
	{
		struct node* temp = lista->head;
		while (temp->before != lista->head)
		{
			if (temp->val == val)
				return;
			temp = temp->before;
		}
	}
}

void howLongRandom(struct list* lista)
{
	printf("Measurement for a random value\n");
	int howMuchTime = 0;
	clock_t start = clock();
	srand(time(NULL));
	for (int i = 0; i < 10000; i++)
	{
		ElementAt(rand() % 1000,1000, lista);
	}
	howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
	printf("%d ms\n", howMuchTime);
}

void howLongSpecific(struct list* lista)
{
	srand(time(NULL));
	int val;
	printf("Podaj index elementu\n");
	scanf("%d", &val);
	printf("Measurement for a specific value\n");
	int howMuchTime = 0;
	clock_t start = clock();
	if(val < 500)
    {
	for (int i = 0; i < 10000; i++)
	{
		ElementAtBack(val, lista);
	}
    }
    else
    {

	for (int i = 0; i < 10000; i++)
	{
		ElementAtFront(val, lista);
	}

    }
    howMuchTime = howMuchTime + ((1000 * (clock() - start)) / CLOCKS_PER_SEC);
	printf("%d ms\n", howMuchTime);
}


struct node* printList(struct list* lista)
{
	if (lista->head != NULL)
	{
		struct node* temp = lista->head;
		while (temp->next != lista->head)
		{
			printf("%d ", temp->val);
			temp = temp->next;
		}
		printf("%d ", temp->val);
	}
}

struct list* merge(struct list* lista1, struct list* lista2)
{
	if (lista1 != NULL && lista2 != NULL)
	{
		struct node* temp = lista2->head;
		while (temp->next != lista2->head)
		{
			push(temp->val, lista1);
			temp = temp->next;
		}
		push(temp->val, lista1);
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
int ElementAtFront(int index,struct list* lista)
{
    struct node* temp = lista->head;
    for(int i=0;i<index -1;i++)
        {
                temp = temp-> next;
        }
        return temp->val;
}
int ElementAtBack(int index,struct list* lista)
{
    struct node* temp = lista->head;
    for(int i=0;i<index -1;i++)
        {
                temp = temp-> before;
        }
        return temp->val;
}

int ElementAt(int index,int dl,struct list* list)
{
    if(index>dl)
        return 0;

    if(dl/2 > index)
    {
       return ElementAtFront(index,list);
            }
    else
    {
       return ElementAtBack(index,list);
    }
}


int main()
{
	struct list* lista = NULL;
	lista = initLista(lista);

	struct list* lista2 = NULL;
	lista2 = initLista(lista2);

	int islistCreated = 0;

	unsigned char znak;

	do
	{
		printf("Enter the number: \n\n");
		printf("Lista 1 \n");
		printf("1. Fill list\n");
		printf("2. Show list1\n\n");
		printf("Lista 2 \n");
		printf("3. Fill list\n");
		printf("4. Show list2\n\n");

		printf("5. Merge list2 to list1\n");
		printf("6. Merge list1 to list2\n\n");

		printf("7. Measure the time for a random element\n");
		printf("8. Measure the time for a specific element\n");

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
			printList(lista);
			break;
		case '3':
			fillList(lista2);
			break;
		case '4':
			printList(lista2);
			break;
		case '5':
			merge(lista, lista2);
			break;
		case '6':
			merge(lista2, lista);
			break;
		case '7':
			howLongRandom(lista);
			break;
		case '8':
			howLongSpecific(lista);
			break;
		}
	} while (znak != 27);

	clear(lista);
	deleteList(lista);
	return 0;
}
