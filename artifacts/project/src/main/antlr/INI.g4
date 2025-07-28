grammar INI;

start: (config)*;

config: (section (item)*) | (section) | (item);

section: LBRACKET title RBRACKET;

title: (STRING | NOQUOTEDSTRING);

item: key EQUALS value;

key: (STRING | NOQUOTEDSTRING);

value: (STRING | NOQUOTEDSTRING);

COMMENT: ';' (~('\n'|'\r'))* -> skip;

/*
 * Taken from the JSON grammar from "The Definitive ANTLR 4 Reference" by Terence Parr:
 */
STRING
   : '"' (ESC | SAFECODEPOINT)* '"'
   ;


fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;


fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;


fragment HEX
   : [0-9a-fA-F]
   ;


fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;

LBRACKET: '[';

RBRACKET: ']';

EQUALS: '=';

NOQUOTEDSTRING: (~('"' | '\n' | '\r' | ';' | '[' | ']' | '='))* ~('"' | '\n' | '\r' | ';' | '[' | ']' | '=' | ' ');

WHITESPACE: (' ' | '\t' | LINEBREAK)+ -> skip;

fragment LINEBREAK: '\r'? '\n';