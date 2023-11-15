package net.iamaprogrammer.notepadapp.api.text.highlighter;

public interface LanguageHighlight {
    String[] keywords();

    SyntaxPatterns[] syntaxPatterns();
}
