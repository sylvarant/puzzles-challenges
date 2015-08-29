/*
 * =====================================================================================
 *
 *       Filename:  hoeken.c
 *
 *    Description:  vlprog wedstrijd vraag 1
 *
 *       Compiler:  gcc
 *         Author:  ajhl
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdarg.h>

static void aprint(const char * fmt,...);

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  aprint
 *  Description:  print using variable input stream  new line is implied
 * =====================================================================================
 */
static void aprint(const char * fmt,...)
{
	va_list arguments;
	va_start(arguments,fmt);
	while(*fmt != '\0')
	{
		int i = va_arg(arguments,int);
		for(int j = 0; j < i; j++) printf("%c",*fmt);
		fmt++;
	}
	printf("\n");
	va_end(arguments);
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *  Description:  main function
 * =====================================================================================
 */
int main (int argc,char ** argv)
{
	int input;
	scanf ("%d",&input);

	if(input < 0 || input > 100) { printf("%d incorrect input",input); return 1;}
	if(input == 1) { printf("* *\n * \n* *\n"); return 0;}
	/** 
	 * print top section
	 */
	aprint("*-* *-*",1,input - 2,1,input,1,input-2,1);
	for(int i = 0;i < input - 2;i++)aprint("| / \\ |",1,input-3-i,1,input  + ((i+1)*2),1,input-3-i,1); 
	aprint("* *",1,input + ((input-1)*2),1);

	/**
	 * print middle section
	 */
	aprint(" *-*",input,1,input-2,1);
	for(int i = 0; i < (input-2) ;i++) aprint(" | |",input,1,input-2,1);
	aprint(" *-*",input,1,input-2,1);

	/**
	 * print bottom section
	 */
	aprint("* *",1,input + ((input-1)*2),1);
	for(int i = 0;i < input - 2;i++)aprint("| \\ / |",1,i,1,input  + ((input-2-i)*2),1,i,1);
	aprint("*-* *-*",1,input - 2,1,input,1,input-2,1);


	return 0;
}

