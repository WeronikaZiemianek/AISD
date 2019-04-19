#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

struct node
{
	struct node* next;
	int val;
};

struct queue
{
	struct node* tail;
	struct node* head;
};

void push(int wartosc, struct queue* kolejka)
{
	struct node* el = (struct node*)malloc(sizeof(struct node));
	el->val = wartosc;

	if (kolejka->tail == NULL)
	{
		el->next = NULL;
		kolejka->tail = el;
		kolejka->head = el;
	}
	else
	{
		el->next = kolejka->tail;
		kolejka->tail = el;
	}
}


int pop(struct queue* kolejka)
{
	if (kolejka->head == NULL)
	{
		printf("The list is empty ");
		return 0;
	}
	int val = kolejka->head->val;

	if (kolejka->head == kolejka->tail)
	{
		free(kolejka->head);
		kolejka->head = NULL;
		kolejka->tail = NULL;
		return val;
	}
	struct node* temp = kolejka->tail;
	while (temp->next != kolejka->head)
	{
		temp = temp->next;
	}
	free(kolejka->head);
	temp->next = NULL;
	kolejka->head = temp;
	return val;
}

void clear(struct queue* kolejka)
{
	if (kolejka->tail != NULL)
	{
		struct node* temp = kolejka->tail;

		while (temp->next != NULL)
		{
			temp = temp->next;
			free(kolejka->tail);
			kolejka->tail = temp;
		}
	}
}


void deleteList(struct queue* kolejka)
{
	if (kolejka != NULL)
	{
		clear(kolejka);
		free(kolejka);
	}
}

struct queue* initLista(struct queue* kolejka)
{
	deleteList(kolejka);
	kolejka = (struct queue*)malloc(sizeof(struct queue));
	kolejka->tail = NULL;
	kolejka->head = NULL;
	return kolejka;

}

void pushWrapper(struct list* lista)
{
	printf("Enter the value: \n");
	int val;
	scanf("%d", &val);
	push(val, lista);
}


int main()
{
	struct queue* lista = NULL;
	lista = initLista(lista);

	int isQueueCreated = 0;

	unsigned char znak;

    do
	{
		printf("Enter the number: \n");
		printf("1. Push element \n");
		printf("2. Pop element \n");

		znak = getch();

		while (kbhit())
		{
			znak = getch();
        }
        switch(znak)
        {
            case '1':
                pushWrapper(lista);
                break;
            case '2':
                printf("%d\n", pop(lista));
                break;
        }
	} while (znak != 27);

    clear(lista);
    deleteList(lista);

	return 0;
}
