/*
 * =====================================================================================
 *
 *       Filename:  quicksort.c
 *
 *    Description:  quicksort
 *
 *        Created:  01/10/2011 09:01:59 PM
 *       Compiler:  gcc -std=c99
 *
 *         Author:  ajhl
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum{ELN = 9};

static int * quicksort (int * in,size_t size)
{
	if(size <= 1){return in;}
	else{
		size_t pos = (size/2);
		int * temp   = (int *) malloc(size*sizeof(int));
		int left = 0,right = (size) , pivot = in[pos];
		for(int i  = 0; i < size;i++){
			if (in[i] <= pivot){ temp[left++] = in[i]; }
			else { temp[--right] = in[i]; }
		}
		memcpy(in,quicksort(temp,left),left * sizeof(int));
		memcpy(in+left,quicksort(temp+left,size - left),(size-left)*sizeof(int));
		free(temp);
		return in;
	}
}

int main(int argc,char ** argv)
{
	int data[] = { 9,6,27,34,15,67,897,1,23};
	int * result = quicksort(data,ELN);
	int expected[] = {1,6,9,15,23,27,34,67,897}; 

	printf("is sorted : %d\n",memcmp(expected,result,ELN*sizeof(int)));

	return 0;
}
