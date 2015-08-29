/**
 * Challenge 2
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static void print_m(int ** matr,int m,int n);
static void update_field(int ** matr,int r,int c,int m,int n);

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *  Description:  main function
 * =====================================================================================
 */
int main(int argc,char ** argv)
{
	FILE * fp =	fopen("/home/adriaan/Projects/challenge/Challenge-IO/110102.inp","r");
	if(fp == NULL) exit(EXIT_FAILURE);
	
	char * ln = NULL;
	size_t len = 0;
	unsigned int field = 0;
	int m,n,r;
	m = n = r =0;
	int ** matrix = NULL;

	while(getline(&ln,&len,fp) != -1){
		if(sscanf(ln,"%i %i\n",&m,&n) == 2){
			if(m == 0) exit(EXIT_SUCCESS);
			if(field > 0) printf("\n");
			printf("Field #%d\n",++field);
			matrix = (int **) malloc(m *sizeof(int *));
			for(int i = 0; i < m ; i++)
				matrix[i] = (int *) calloc(n ,sizeof(int));
		}
		else{
			int c = 0;
			for(char * ptr = ln; *ptr != '\n' && *ptr != '\0'; ptr++,c++){
				if(*ptr == '*'){
					update_field(matrix,r,c,m,n);
				}
			}
			r++;
			if(r == m){ 
				print_m(matrix,m,n);			
				for(int i = 0; i < m ; i++)
					free(matrix[i]);
				free(matrix);
				m = n = r = 0;
			}
		}
	}
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  print_m
 *  Description:  print out matrix
 * =====================================================================================
 */
static void print_m(int ** matrix,int m,int n)
{
	for(int i = 0; i < m; i++){
		for(int j = 0; j < n ; j++){
			if(matrix[i][j] == -1) printf("*");
			else printf("%d",matrix[i][j]);
		}
		printf("\n");
	}
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  update_field
 *  Description:  
 * =====================================================================================
 */
static void update_field(int ** matrix,int r,int c,int m,int n)
{	
	static int round[2][8] = { -1,-1,-1,0,0,1,1,1 , 1,0,-1,1,-1,1,0,-1};
	matrix[r][c] = -1;
	for (int i = 0; i < 8 ; i++){
		int a = r + round[1][i];
		int b = c + round[0][i];
		if( a >= 0 && b >= 0 && a < m && b < n && matrix[a][b] != -1)  matrix[a][b] += 1;
	}
}
