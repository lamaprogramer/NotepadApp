package net.iamaprogrammer.notepadapp.api.text.highlighter;

public enum SyntaxPatterns {
    PARENTHESIS("\\(|\\)"),
    BRACKET("\\[|\\]"),
    BRACE("\\{|\\}"),
    SEMICOLON("\\;"),
    STRING("\"([^\"\\\\]|\\\\.)*\""),

    NUMBER("\\b[0-9]*\\.[0-9]*\\b|\\b[0-9]*\\b");

    private final String pattern;
    SyntaxPatterns(String pattern) {
        this.pattern = "|(?<"+this.name()+">"+pattern+")";
    }

    public String get() {
        return pattern;
    }
}
