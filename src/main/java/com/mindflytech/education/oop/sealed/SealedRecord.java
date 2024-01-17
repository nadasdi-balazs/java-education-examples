package com.mindflytech.education.oop.sealed;

//Compile error:
//Illegal combination of modifiers: 'sealed' and 'final'
//Modifier 'non-sealed' not allowed here
//Modifier 'final' is redundant for records
public final record SealedRecord() implements SealedInterface {
}
