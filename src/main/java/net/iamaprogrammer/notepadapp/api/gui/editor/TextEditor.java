package net.iamaprogrammer.notepadapp.api.gui.editor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import net.iamaprogrammer.notepadapp.HelloApplication;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.RichCodeArea;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichParagraphStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichTextStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.controls.StyleColorPicker;
import net.iamaprogrammer.notepadapp.api.gui.styles.controls.StyleToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.styles.controls.StyleType;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.Alignment;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.FormatColor;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextEditor extends VBox {
    @FXML
    private EditorTabPane text_editor_pane;
    @FXML
    private HBox style_bar;

    private final RichTextStyleClass textStyle;
    private final RichParagraphStyleClass paragraphStyle;
    public TextEditor() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("text_editor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            this.initGraphics();

            this.textStyle = new RichTextStyleClass();

            Alignment alignment = new Alignment();
            Map<String, StyleFormat<?>> defaultValues = new HashMap<>();
            defaultValues.put(alignment.getName(), alignment);

            this.paragraphStyle = new RichParagraphStyleClass(Collections.emptyList(), new HashMap<>(), defaultValues);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void initGraphics() {
        this.text_editor_pane.prefHeightProperty().bind(this.heightProperty()
                        .subtract(this.style_bar.isManaged() ? this.style_bar.heightProperty().doubleValue() : 0)
                );
        this.style_bar.setStyle("-fx-padding: 0; -fx-font-size: 18;");
        this.text_editor_pane.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTab, newTab) -> {
            EditorTab tab = (EditorTab) newTab;
            this.setStyleBarVisible(tab.getLanguage() == null);
        });
        //paragraphStyle.updateStyle(new Alignment(TextAlignment.LEFT), true);
    }

    public void addTab(EditorFile file, boolean lineNumbers) {
        EditorTab tab = new EditorTab(file, file.getTitle(), this.textStyle, this.paragraphStyle);
        RichCodeArea area = tab.getCodeArea();
        area.caretPositionProperty().addListener((observableValue, previousPosition, currentPosition) -> {
            RichTextStyleClass currentTextStyle = area.getStyleAtPosition(currentPosition);
            RichParagraphStyleClass currentParagraphStyle = area.getParagraphStyleForInsertionAt(currentPosition);
            System.out.println(currentPosition);
            System.out.println(currentParagraphStyle.toCSS());

            this.text_editor_pane.getStyleManipulators().forEach((key, button) -> {
                if (button.getStyleType() == StyleType.TEXT) {
                    boolean success = button.press(currentTextStyle.getStyle(button.getStyleName()));
                    if (success) {
                        StyleFormat<?> currentTextStyleFormat = currentTextStyle.getStyle(button.getStyleName());
                        StyleFormat<?> otherTextStyleFormat = this.textStyle.getStyle(button.getStyleName());

                        if (currentTextStyleFormat != null && otherTextStyleFormat != null) {
                            if (!currentTextStyleFormat.equals(otherTextStyleFormat)) {
                                this.textStyle.updateStyle(currentTextStyleFormat, true);
                            }
                        } else {
                            if (currentTextStyleFormat != null) {
                                this.textStyle.updateStyle(currentTextStyleFormat.getDefault(), true);
                            } else {
                                if (otherTextStyleFormat != null) {
                                    this.textStyle.updateStyle(otherTextStyleFormat.getDefault(), false);
                                }
                            }
                        }
                    }

                } else if (button.getStyleType() == StyleType.PARAGRAPH) {
                    StyleFormat<?> currentParagraphStyleFormat = currentParagraphStyle.getStyle(button.getStyleName());
                    if (currentParagraphStyleFormat != null) {
                        if (button.press(currentParagraphStyleFormat)) {
                            this.paragraphStyle.updateStyle(currentParagraphStyleFormat, true);
                        }
                    } else {
                        StyleFormat<?> defaultInstance = button.getTextStyle().getDefault();
                        if (button.press(defaultInstance)) {
                            this.paragraphStyle.updateStyle(defaultInstance, true);
                        }
                    }
                }
            });
        });
        this.text_editor_pane.getTabs().add(tab);
    }

    public void addToggleButton(FontIcon icon, StyleFormat<?> style) {
        StyleToggleButton<StyleFormat<?>> button = new StyleToggleButton<>("", style);
        button.applyOnPress((styleCss) -> {
            //this.textStyle.setFromStyle(styleCss, button.isSelected());
            this.textStyle.updateStyle(styleCss, button.isSelected());
            RichCodeArea area = this.getRichCodeArea();

            if (area != null) {
                int[] selectionIndexes = this.getTextSelection(area);
                int smallestIndex = Math.min(selectionIndexes[0], selectionIndexes[1]);
                int largestIndex = Math.max(selectionIndexes[0], selectionIndexes[1]);
                area.setStyle(smallestIndex, largestIndex, new RichTextStyleClass(this.textStyle));
            }
            return true;
        });

        button.setGraphic(icon);
        button.prefWidthProperty().bind(button.heightProperty());
        this.text_editor_pane.getStyleManipulators().put(button.getTextStyle().getName(), button);
        this.style_bar.getChildren().add(button);
    }




    public void addAlignmentButtons() {
        ToggleGroup group = new ToggleGroup();
        group.selectedToggleProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });

        for (TextAlignment textAlignment : TextAlignment.values()) {
            StyleToggleButton<StyleFormat<TextAlignment>> button = new StyleToggleButton<>("", new Alignment(textAlignment), StyleType.PARAGRAPH);
            button.applyOnPress((alignment) -> {
                this.paragraphStyle.updateStyle(alignment, button.isSelected());
                RichCodeArea area = this.getRichCodeArea();

                if (area != null) {
                    int[] selectionIndexes = this.getParagraphSelection(area);
                    int smallestIndex = Math.min(selectionIndexes[0], selectionIndexes[1]);
                    int largestIndex = Math.max(selectionIndexes[0], selectionIndexes[1]);

                    for (int i = smallestIndex; i <= largestIndex; i++) {
                        area.setParagraphStyle(i, new RichParagraphStyleClass(this.paragraphStyle));
                    }
                }
                return true;
            });
            switch (textAlignment) {
                case LEFT -> {
                    button.setGraphic(new FontIcon(MaterialDesignF.FORMAT_ALIGN_LEFT));
                    button.setSelected(true);
                }
                case RIGHT -> button.setGraphic(new FontIcon(MaterialDesignF.FORMAT_ALIGN_RIGHT));
                case CENTER -> button.setGraphic(new FontIcon(MaterialDesignF.FORMAT_ALIGN_CENTER));
                case JUSTIFY -> button.setGraphic(new FontIcon(MaterialDesignF.FORMAT_ALIGN_JUSTIFY));
            }
            button.setToggleGroup(group);
            button.prefWidthProperty().bind(button.heightProperty());
            this.text_editor_pane.getStyleManipulators().put(button.getTextStyle().getValue().name(), button);
            this.style_bar.getChildren().add(button);
        }
    }
    public void addColorPicker() {
        StyleColorPicker button = new StyleColorPicker();
        button.applyOnPress((color) -> {
            this.textStyle.updateStyle(color, true);
            RichCodeArea area = this.getRichCodeArea();

            if (area != null) {
                int[] selectionIndexes = this.getTextSelection(area);
                int smallestIndex = Math.min(selectionIndexes[0], selectionIndexes[1]);
                int largestIndex = Math.max(selectionIndexes[0], selectionIndexes[1]);

                area.setStyle(smallestIndex, largestIndex, new RichTextStyleClass(this.textStyle));
            }
            return true;
        });
        button.setValue(Color.BLACK);
        this.text_editor_pane.getStyleManipulators().put("COLOR", button);
        this.style_bar.getChildren().add(button);
    }

    public boolean isStyleBarVisible() {
        return this.style_bar.isVisible() && this.style_bar.isManaged();
    }
    public void setStyleBarVisible(boolean visible) {
        this.style_bar.setVisible(visible);
        this.style_bar.setManaged(visible);
    }
    private RichCodeArea getRichCodeArea() {
        EditorTab tab = (EditorTab) this.text_editor_pane.getSelectionModel().getSelectedItem();
        return tab != null ? tab.getCodeArea() : null;
    }
    private int[] getParagraphSelection(RichCodeArea area) {
        return new int[]{area.getCaretSelectionBind().getAnchorParIndex(), area.getCaretSelectionBind().getParagraphIndex()};
    }
    private int[] getTextSelection(RichCodeArea area) {
        return new int[]{area.getCaretSelectionBind().getAnchorPosition(), area.getCaretSelectionBind().getPosition()};
    }
}
