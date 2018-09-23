%token TYPE
%token KEYWORD
%token ID
%left ','
%%
S: TYPE {fun1();} E ';'
	;
E: E ',' E
	|
	ID{fun2();}
	;
%%

#include "stdio.h"
#include "lex.yy.c"

int main(){
	printf("Enter declaration to validate");
	yyparse();
}

void fun1(){
	printf("%s This is a datatype",yytext);
}
void fun2(){
	printf("%s This is a identifier",yytext);
}

int yyerror(){
	printf("Invalid");
}
