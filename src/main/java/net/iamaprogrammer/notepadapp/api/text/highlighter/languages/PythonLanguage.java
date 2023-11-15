package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

public class PythonLanguage implements LanguageHighlight {
    @Override
    public String[] keywords() {
        return new String[]{
                "False", "await", "else", "import", "pass",
                "None", "break", "except", "in", "raise",
                "True", "class", "finally", "is", "return",
                "and", "continue", "for", "lambda", "try",
                "as", "def", "from", "nonlocal", "while",
                "assert", "del", "global", "not", "with",
                "async", "elif", "if", "or", "yield"
        };
    }
    @Override
    public SyntaxPatterns[] syntaxPatterns() {
        return new SyntaxPatterns[]{
                SyntaxPatterns.BRACKET,
                SyntaxPatterns.PARENTHESIS,
                SyntaxPatterns.SEMICOLON,
                SyntaxPatterns.STRING,
                SyntaxPatterns.NUMBER,
                SyntaxPatterns.COMMENTPYTHON
        };
    }
}
