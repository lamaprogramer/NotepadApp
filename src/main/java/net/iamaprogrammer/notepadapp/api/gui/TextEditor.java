package net.iamaprogrammer.notepadapp.api.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import net.iamaprogrammer.notepadapp.HelloApplication;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.buttons.StyleColorPicker;
import net.iamaprogrammer.notepadapp.api.gui.buttons.StyleToggleButton;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Languages;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;
import java.util.Collections;

public class TextEditor extends VBox {
    @FXML
    private TabPane text_editor_pane;
    @FXML
    private HBox style_bar;
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
        this.text_editor_pane.prefHeightProperty().bind(this.heightProperty().subtract(
                this.style_bar.isManaged() ? this.style_bar.heightProperty().doubleValue() : 0
        ));
        this.style_bar.setStyle("-fx-padding: 0; -fx-font-size: 18;");
        this.text_editor_pane.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTab, newTab) -> {
            CodeTab tab = (CodeTab) newTab;
            this.setStyleBarVisible(tab.getLanguage() == null);
        });
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
        textArea.setWrapText(true);

        textArea.setStyle("-fx-font-size: 24;");
        textArea.setId("note-area");
        if (lineNumbers) {
            textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea)); // Line numbers.
        }
        textArea.appendText(file.getBody());
        //textArea.setParagraphStyle(textArea.getCurrentParagraph(), Collections.singleton("-fx-text-alignment: center;"));
        //textArea.setStyle("-fx-text-alignment: center;");
        tab.setCodeArea(textArea);
        box.getChildren().add(tab.getCodeArea());
        this.text_editor_pane.getTabs().add(tab);
    }
    public void addToggleButton(String name, Styles style) {
        StyleToggleButton button = new StyleToggleButton(name, style, (styleCss) -> {
            this.textStyle.fromStyle(styleCss);
            return true;
        });
        button.prefWidthProperty().bind(button.heightProperty());
        this.style_bar.getChildren().add(button);
    }
    public void addColorPicker() {
        StyleColorPicker button = new StyleColorPicker((color) -> {
            this.textStyle.setColor(color);
            return true;
        });
        button.setValue(Color.BLACK);
        this.style_bar.getChildren().add(button);
    }
}
