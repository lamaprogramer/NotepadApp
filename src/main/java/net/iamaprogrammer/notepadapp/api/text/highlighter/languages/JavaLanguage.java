package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

import java.util.regex.Pattern;

public class JavaLanguage implements LanguageHighlight {


    @Override
    public String[] keywords() {
        return new String[]{"package", "import", "public", "private", "protected", "class", "enum", "record"};
    }

    @Override
    public SyntaxPatterns[] syntaxPatterns() {
        return new SyntaxPatterns[]{
                SyntaxPatterns.BRACE,
                SyntaxPatterns.BRACKET,
                SyntaxPatterns.PARENTHESIS,
                SyntaxPatterns.SEMICOLON
        };
    }
}
