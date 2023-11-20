package net.iamaprogrammer.notepadapp.api.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.iamaprogrammer.notepadapp.HelloApplication;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Languages;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;

public class TextEditor extends VBox {
    @FXML
    private TabPane text_editor_pane;
    @FXML
    private ButtonBar style_bar;
    private final RichTextStyleClass textStyle = new RichTextStyleClass();

    public TextEditor() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("text_editor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            this.initGraphics();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initGraphics() {
        this.text_editor_pane.prefHeightProperty().bind(this.heightProperty().subtract(this.style_bar.heightProperty()));
        this.style_bar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        this.setStyleBarVisible(false);
    }
    public boolean isStyleBarVisible() {
        return this.style_bar.isVisible();
    }
    public void setStyleBarVisible(boolean visible) {
        this.style_bar.setVisible(visible);
        this.style_bar.setManaged(visible);
    }

    public void addTab(EditorFile file, boolean lineNumbers) {
        LanguageHighlight highlight = Languages.generateFromFile(file.getTitle());
        CodeTab tab = new CodeTab(highlight, file.getTitle());
        VBox box = new VBox();
        tab.setContent(box);

        RichCodeArea textArea = new RichCodeArea((instance, change) -> {
            int to = change.getInsertionEnd();
            int from = to - change.getInserted().length();
            if (from < to) {
                instance.setStyle(from, to, new RichTextStyleClass(this.textStyle));
            }
            return true;
        }).withHighlighting(tab.getLanguage());
        textArea.prefHeightProperty().bind(box.heightProperty());

        textArea.setStyle("-fx-font-size: "+24);
        textArea.setId("note-area");
        if (lineNumbers) {
            textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea)); // Line numbers.
        }
        textArea.appendText(file.getBody());

        tab.setCodeArea(textArea);
        box.getChildren().add(tab.getCodeArea());
        this.text_editor_pane.getTabs().add(tab);
    }
    public void addButton(Styles style) {
        StyleButton button = new StyleButton(style, (styleCss) -> {
            this.textStyle.fromStyle(styleCss);
            return true;
        });
        this.style_bar.getButtons().add(button);
    }
}
