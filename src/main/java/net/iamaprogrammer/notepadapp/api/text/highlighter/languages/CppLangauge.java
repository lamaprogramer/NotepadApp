package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

public class CppLangauge implements LanguageHighlight {
    @Override
    public String[] keywords() {
        return new String[]{
                "alignas11", "alignof11", "and", "and_eq", "asm",
                "atomic_cancel", "TS", "atomic_commit", "TS", "atomic_noexcept",
                "TS", "auto", "bitand", "bitor", "bool",
                "break", "case", "catch", "char", "char8_t20",
                "char16_t11", "char32_t11", "class", "compl", "concept20",
                "const", "consteval20", "constexpr11", "constinit20", "const_cast",
                "continue", "co_await20", "co_return20", "co_yield20", "decltype11",
                "default", "delete", "do", "double", "dynamic_cast",
                "else", "enum", "explicit", "export", "extern",
                "false", "float", "for", "friend", "goto",
                "if", "inline", "int", "long", "mutable",
                "namespace", "new", "noexcept11", "not", "not_eq",
                "nullptr11", "operator", "or", "or_eq", "private",
                "protected", "public", "reflexpr", "TS", "register",
                "reinterpret_cast", "requires20", "return", "short", "signed",
                "sizeof", "static", "static_assert11", "static_cast", "struct",
                "switch", "synchronized", "TS", "template", "this",
                "thread_local11", "throw", "true", "try", "typedef",
                "typeid", "typename", "union", "unsigned", "using",
                "virtual", "void", "volatile", "wchar_t", "while",
                "xor", "xor_eq"
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
