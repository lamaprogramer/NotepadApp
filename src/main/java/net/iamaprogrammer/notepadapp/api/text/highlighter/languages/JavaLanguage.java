package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

import java.util.regex.Pattern;

public class JavaLanguage implements LanguageHighlight {


    @Override
    public String[] keywords() {
        return new String[]{"abstract",	"continue",	"for", "new", "switch",
            "assert", "default", "goto", "package",	"synchronized",
            "boolean", "do", "if", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte",	"else",	"import", "public", "throws",
            "case",	"enum",	"instanceof","return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally","long","strictfp","volatile",
            "const", "float", "native", "super", "while"
        };
    }

    @Override
    public SyntaxPatterns[] syntaxPatterns() {
        return new SyntaxPatterns[]{
                SyntaxPatterns.BRACE,
                SyntaxPatterns.BRACKET,
                SyntaxPatterns.PARENTHESIS,
                SyntaxPatterns.SEMICOLON,
                SyntaxPatterns.STRING,
                SyntaxPatterns.NUMBER
        };
    }
}
