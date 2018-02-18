#include <unistd.h>
typedef struct freenode
{
	size_t size;
	struct freenode *next;
} freenode;
struct freenode *head = NULL;
struct freenode *tail = NULL;
void *my_alloc(size_t size)
{
	struct freenode *temp;
	if (size < 1)
	{
		return NULL;
	}
	size += 8;
	if (size < 32)
	{
		size = 32;
	}
	else if (size % 16 != 0)
	{
		size = ((size / 16) + 1) * 16;
	}
	if (head == NULL)
	{
		temp = (freenode*)sbrk(4096);
		if (temp == (void*)-1)
		{
			return NULL;
		}
		temp->size = 8;
		temp->size += size;
		temp->next = tail;
		head = temp;
	}
	else
	{
		while (head)
		{
			if (head->size >= size)
			{
				temp = head;
				// need to add mend = (char *)sbrk(((size/4096)+1)*4096);
				if (tail != NULL)
				{
					tail->next = head;
				}
				else
				{
					tail = head;
				}
			}
			head = head->next;
		}
	}
	return (void*)(temp + 1);
}
void my_free(void *ptr)
{
	if (ptr == NULL)
	{
		return;
	}
	struct	freenode* node = (struct freenode*) ptr -1;
	if ((size_t*)ptr + node->size < sbrk(32))
	{
		if (head == tail)
		{
			head = NULL;
			tail = NULL;
		}
		else
		{
			while (head)
			{
				if (head->next == tail)
				{
					head->next == NULL;
					tail = head;
				}
				
				head = head->next;
			}
		}
		sbrk(32 - sizeof(struct freenode) - node->size);
		return;
	}
}
