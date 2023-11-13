package net.iamaprogrammer.notepadapp.api.text.highlighter;

import java.util.regex.Pattern;

public interface LanguageHighlight {
    String[] keywords();

    SyntaxPatterns[] syntaxPatterns();
}
