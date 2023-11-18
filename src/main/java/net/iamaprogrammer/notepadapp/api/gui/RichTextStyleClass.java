package net.iamaprogrammer.notepadapp.api.gui;

import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.Collections;

public class RichTextStyleClass {
    private final Collection<String> styleClasses;



    private Color color;
    private boolean bold = false;
    private boolean italic = false;

    public RichTextStyleClass() {
        this.styleClasses = Collections.emptyList();
    }

    public RichTextStyleClass(Collection<String> styleClasses) {
        this.styleClasses = styleClasses;
    }
    public Collection<String> getStyleClasses() {
        return this.styleClasses;
    }
    public boolean isBold() {
        return this.bold;
    }
    public RichTextStyleClass setBold(boolean bold) {
        this.bold = bold;
        return this;
    }
    public boolean isItalic() {
        return this.italic;
    }
    public RichTextStyleClass setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }
    Collection<String> toList() {
        return this.styleClasses;
    }
    public String toCSS() {
        String css = "";
        css += this.color != null ? "-fx-fill: "+this.color.toString().replaceFirst("0x", "#") : "";
        css += this.bold ? "-fx-font-weight: bold;" : "";
        css += this.italic ? "-fx-font-style: italic;" : "";

        return css;
    }
    public Color getColor() {
        return color;
    }
    public RichTextStyleClass setColor(Color color) {
        this.color = color;
        return this;
    }
}
