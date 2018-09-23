%{
/* NLP lexer */
#include<stdio.h>
#include"y.tab.h"
%}
%%
[\t ]+ ;
is|am|are { return(VERB); }
very|simply|gently { return(ADVERB); }
to|from|behind { return(PREPOSITION);}
if|then|and { return(CONJUNCTION);}
their|my|your { return(ADJECTIVE); }
I|you|he { return(PRONOUN); }
[a-zA-Z]+ { return(NOUN); }
.|\n { ECHO; }
%%

int yywrap()
{
return(1);
}
