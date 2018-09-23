%%
System|public|static|main|out|println {return KEYWORD;}
int|float|void|char|long {return TYPE;}
[_a-zA-Z]+[_a-zA-Z0-9]* {return ID;}
,|; {return yytext[0];}
\n {return 0;}
[ \t] ;
[.] {return 0;} 
%%


int yywrap(void){
	return 1;
}
