package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

public class CLanguage implements LanguageHighlight {
    @Override
    public String[] keywords() {
        return new String[]{
                "alignas", "alignof", "auto", "bool", "break",
                "case", "char", "const", "constexpr", "continue",
                "default", "do", "double", "else", "enum",
                "extern", "false", "float", "for", "goto",
                "if", "inline", "int", "long", "nullptr",
                "register", "restrict", "return", "short", "signed",
                "sizeof", "static", "static_assert", "struct", "switch",
                "thread_local", "true", "typedef", "typeof", "typeof_unqual",
                "union", "unsigned", "void", "volatile", "while",
                "_Alignas", "_Alignof", "_Atomic", "_BitInt", "_Bool",
                "_Complex", "_Decimal128", "_Decimal32", "_Decimal64", "_Generic",
                "_Imaginary", "_Noreturn", "_Static_assert", "_Thread_local"
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
                SyntaxPatterns.NUMBER,
                SyntaxPatterns.COMMENT
        };
    }
}
