/*
 * =====================================================================================
 *
 *       Filename:  transitiveClosure.c
 *
 *    Description:  implements transitive closure
 *
 *        Created:  10/21/2010 11:19:45 AM
 *       Compiler:  gcc -std=c99
 *
 *         Author:  ajhl
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct s_edge
{
	char one;
	char two;
} edge;

const edge null = {0};

static edge * transitive_closure(const edge * given,size_t basesize,const char * vertices);
static inline bool comp_edge(const edge * a,const edge * b);


int main(int argc,char ** argv)
{
	
	edge data[] = { {'A','B'},{'B','C'},{'D','E'},{'C','D'} ,{'E','A'}};
	char * vertices = "ABCDE";

	edge * result = transitive_closure(data,5,vertices);
	for(const edge * it = result; !comp_edge(it,&null);it++)
	{
		printf("%c->%c ",it->one,it->two);
	}
	printf("\n");

	free(result);
}

static edge * transitive_closure(const edge * given, size_t basesize,const char * vertices)
{
	edge * ret = NULL;
	int ret_pos  = 0;
	
	int n_vertices = strlen(vertices);
	for(const char * it = vertices; *it ; it++)
	{
		edge * found = (edge *) calloc(n_vertices,sizeof(edge));
		int found_pos = 0;
		char * targets = (char *) calloc(n_vertices+1,sizeof(char));
		targets[0] = *it;
		int targets_pos = 1;
		bool self = false;		
		int old_target_pos;
		
		do
		{
			old_target_pos = targets_pos;
			for(size_t i = 0 ; i < basesize ; i++)
			{
				if(strchr(targets,given[i].one) != NULL)
				{
					if(strchr(targets,given[i].two) == NULL)
					{
						edge new = {.one = *it , .two = given[i].two};
						found[found_pos++] = new;	
						targets[targets_pos++] = given[i].two;
					}
					else if( given[i].two == *it && !self)
					{
						edge new = {.one = *it , .two = *it};
						found[found_pos++] = new;	
						self = true;
					}
				}
			}
		}
		while(targets_pos != old_target_pos);
			
		ret = (edge *) realloc(ret,(ret_pos+found_pos+1) * sizeof(edge));
		for(size_t i = 0;i <found_pos ; ret_pos++,i++)
		{
			ret[ret_pos] = found[i];
		}
		free(found);
		free(targets);
	}

	ret[ret_pos] = null;
	return ret;
}

static inline bool comp_edge(const edge * a,const edge * b)
{
	return (a->one == b->one) && (a->two == b->two);
}

