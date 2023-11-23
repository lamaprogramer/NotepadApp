package net.iamaprogrammer.notepadapp.api.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import net.iamaprogrammer.notepadapp.HelloApplication;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.buttons.AlignmentToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.buttons.StyleColorPicker;
import net.iamaprogrammer.notepadapp.api.gui.buttons.StyleToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichParagraphStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichTextStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.TextStyles;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Languages;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TextEditor extends VBox {
    @FXML
    private TabPane text_editor_pane;
    @FXML
    private HBox style_bar;
    private final RichTextStyleClass textStyle = new RichTextStyleClass();
    private final RichParagraphStyleClass paragraphStyle = new RichParagraphStyleClass();
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
            EditorTab tab = (EditorTab) newTab;
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
        EditorTab tab = new EditorTab(file, file.getTitle(), this.textStyle, this.paragraphStyle);
        this.text_editor_pane.getTabs().add(tab);
    }
    public void addToggleButton(String name, TextStyles style) {
        StyleToggleButton button = new StyleToggleButton(name, style, (styleCss) -> {
            this.textStyle.fromStyle(styleCss);
            return true;
        });
        button.prefWidthProperty().bind(button.heightProperty());
        this.style_bar.getChildren().add(button);
    }
    public void addToggleButton(String name, TextAlignment style) {
        AlignmentToggleButton button = new AlignmentToggleButton(name, style, (alignment) -> {
            this.paragraphStyle.setAlignment(alignment);
            RichCodeArea area = this.getRichCodeArea();

            int[] selectionIndexes = this.getParagraphSelection(area);
            this.applyToParagraphsInRange(selectionIndexes[0], selectionIndexes[1], (paragraphIndex) -> {
                area.setParagraphStyle(paragraphIndex, new RichParagraphStyleClass(this.paragraphStyle));
                return true;
            });
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

    private RichCodeArea getRichCodeArea() {
        return ((EditorTab) this.text_editor_pane.getSelectionModel().getSelectedItem()).getCodeArea();
    }
    private int[] getParagraphSelection(RichCodeArea area) {
        return new int[]{area.getCaretSelectionBind().getAnchorParIndex(), area.getCaretSelectionBind().getParagraphIndex()};
    }
    private void applyToParagraphsInRange(int index1, int index2, Function<Integer, Boolean> function) {
        int smallestIndex = Math.min(index1, index2);
        int largestIndex = Math.max(index1, index2);

        for (int i = smallestIndex; i <= largestIndex; i++) {
            function.apply(i);
        }
    }
}
