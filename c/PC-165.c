/*
 * =====================================================================================
 *
 *       Filename:  PC-165.c
 *
 *    Description:  Program Challenge
 *
 *        Version:  1.0
 *        Created:  02/24/2011 04:55:56 PM
 *       Compiler:  gcc
 *
 *         Author:  ajhl 
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>

static void fill(char ** matrix,int x,int y,char r,char c,int m,int n);

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *  Description:  main function
 * =====================================================================================
 */
int main(int argc,char ** argv)
{
	FILE * fp =	fopen("/home/adriaan/Projects/challenge/Challenge-IO/110105.inp","r");
	if(fp == NULL) exit(EXIT_FAILURE);

	char * ln = NULL;
	size_t len = 0;
	char ** matrix = NULL;
	char * name = malloc(sizeof(char) * 250);
	int m,n;
	int x,y,y1,y2,x1,x2;
	char c;
	m = n = 0;

	while(getline(&ln,&len,fp) != -1){
		
		switch(*ln){

			case 'I' : sscanf(ln,"I %i %i\n",&m,&n);
					   matrix = malloc(m * sizeof(char *));
					   for(int i = 0; i < m ; i++)
							matrix[i] = malloc(n*sizeof(char));
					   for(int i = 0; i < m; i++){
							for(int j = 0; j < n ; j++) matrix[i][j] ='O';
					   break;
			
			case 'C' : for(int i = 0; i < m; i++) {
							for(int j = 0; j < n ; j++) matrix[i][j] ='O';
					   }
					   break;
			
			case 'L' : sscanf(ln,"L %i %i %c\n",&x,&y,&c);
					   matrix[x][y] = c;
					   break;

			case 'V' : sscanf(ln,"V %i %i %i %c\n",&x,&y1,&y2,&c);
					   for(int i = (y1-1); i < y2; i++){
							matrix[i][x] = c;
					   }
					   break;
			case 'H' : sscanf(ln,"H %i %i %i %c\n",&x1,&x2,&y,&c);
					   for(int i = (x1-1); i < x2; i++){
							matrix[y][i] = c;
					   }
					   break;
			case 'K' : sscanf(ln,"K %i %i %i %i %c\n",&x1,&y1,&x2,&y2,&c);
					   for(int i = (x1-1) ; i < x2; i++){
						for(int j = (y1-1); j < y2; j++)
							matrix[i][j] = c;
                       }
					   break;
			
			case 'F' : sscanf(ln,"F %i %i %c\n",&x,&y,&c);
					   char r = matrix[x][y];
					   fill(matrix,x,y,r,c,m,n);
					   break;
			
			case 'S' :  sscanf(ln,"S %s\n",name);
					    printf("%s\n",name);
						for(int i = 0; i < m ; i++) printf("%s\n",matrix[i]);
						break;

			case 'X' : exit(EXIT_SUCCESS);
					   break;
		}
	}
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  fill
 *  Description:  fill a region
 * =====================================================================================
 */
static void fill(char ** matrix,int x,int y,char r,char c,int m,int n)
{
	static int env[2][8] = { -1,-1,-1,0,0,1,1,1 , 1,0,-1,1,-1,1,0,-1};
	matrix[x][y] = c;
	for (int i = 0; i < 8 ; i++){
		int a = x + env[1][i];
		int b = y + env[0][i];
		if( a >= 0 && b >= 0 && a < m && b < n && matrix[a][b] == r) {fill(matrix,x,y,r,c,m,n);}
	}
}
