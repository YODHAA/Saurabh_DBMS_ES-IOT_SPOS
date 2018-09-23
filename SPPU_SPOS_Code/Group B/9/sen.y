%token COMP
%token SIMP

%%
S: COMP {comp();}
	|
	SIMP{simp();}
	;
%%

#include "stdio.h"
#include "lex.yy.c"

int main(){
	printf("Enter statement\n");
	while(1){
		yyparse();
	}
	return 0;
}

void comp(){
	printf("Compount statement");
}
void simp(){
	printf("Simple statement");
}
