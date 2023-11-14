package net.iamaprogrammer.notepadapp.api.gui;

import javafx.scene.control.Tab;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import org.fxmisc.richtext.CodeArea;

public class CodeTab extends Tab {
    private final LanguageHighlight language;
    private CodeArea codeArea;

    public CodeTab(LanguageHighlight language, String text) {
        super(text);
        this.language = language;
    }

    public void setCodeArea(CodeArea codeArea) {
        this.codeArea = codeArea;
    }
    public CodeArea getCodeArea() {
        return this.codeArea;
    }
    public LanguageHighlight getLanguage() {
        return language;
    }
}
