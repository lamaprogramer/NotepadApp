package net.iamaprogrammer.notepadapp.api.gui.editor;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.RichCodeArea;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichParagraphStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichTextStyleClass;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Languages;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.SimpleEditableStyledDocument;

public class EditorTab extends Tab {
    private final LanguageHighlight language;
    private RichCodeArea codeArea;

    public EditorTab(EditorFile file, String text, RichTextStyleClass textStyle, RichParagraphStyleClass paragraphStyle) {
        super(text);
        this.language = Languages.generateFromFile(file.getTitle());

        VBox box = new VBox();
        this.setContent(box);
        RichCodeArea textArea = new RichCodeArea(
                new SimpleEditableStyledDocument<>(new RichParagraphStyleClass(), new RichTextStyleClass()),
                true,
                (instance, change) -> {
                    int to = change.getInsertionEnd();
                    int from = to - change.getInserted().length();
                    if (from < to) {
                        instance.setStyle(from, to, new RichTextStyleClass(textStyle));
                    }
                    return true;
                }).withHighlighting(this.getLanguage());

        textArea.prefHeightProperty().bind(box.heightProperty());
        textArea.setWrapText(true);

        textArea.setStyle("-fx-font-size: 24;");
        textArea.setId("note-area");

        textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea)); // Line numbers.
        textArea.appendText(file.getBody());
        this.setCodeArea(textArea);
        box.getChildren().add(this.getCodeArea());
    }

    public void setCodeArea(RichCodeArea codeArea) {
        this.codeArea = codeArea;
    }
    public RichCodeArea getCodeArea() {
        return this.codeArea;
    }
    public LanguageHighlight getLanguage() {
        return language;
    }
}
