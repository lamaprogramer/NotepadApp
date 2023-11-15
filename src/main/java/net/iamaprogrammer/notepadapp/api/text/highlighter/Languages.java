package net.iamaprogrammer.notepadapp.api.text.highlighter;

import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.CLanguage;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.CppLangauge;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.JavaLanguage;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.PythonLanguage;

public class Languages {
    public static LanguageHighlight generateFromFile(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".")+1);
        switch (extension) {
            case "java" -> {return new JavaLanguage();}
            case "py" -> {return new PythonLanguage();}
            case "c" -> {return new CLanguage();}
            case "cc", "cpp", "o", "h" -> {return new CppLangauge();}
            default -> {return null;}
        }
    }
}
