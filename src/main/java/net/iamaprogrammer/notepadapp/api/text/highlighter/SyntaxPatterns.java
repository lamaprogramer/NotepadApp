package net.iamaprogrammer.notepadapp.api.text.highlighter;

public enum SyntaxPatterns {
    PARENTHESIS("\\(|\\)"),
    BRACKET("\\[|\\]"),
    BRACE("\\{|\\}"),
    SEMICOLON("\\;");

    private final String pattern;
    SyntaxPatterns(String pattern) {
        this.pattern = "|(?<"+this.name()+">"+pattern+")";
    }

    public String get() {
        return pattern;
    }
}
