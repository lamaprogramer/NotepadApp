package net.iamaprogrammer.notepadapp.api.gui.styles;

import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.Collections;

public class RichTextStyleClass {
    private final Collection<String> styleClasses;
    private Color color;
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private boolean strikethrough = false;

    public RichTextStyleClass() {
        this.styleClasses = Collections.emptyList();
    }

    public RichTextStyleClass(Collection<String> styleClasses, Color color, boolean bold, boolean italic, boolean underline, boolean strikethrough) {
        this.styleClasses = styleClasses;
        this.color = color;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
    }
    public RichTextStyleClass(Collection<String> styleClasses) {
        this(styleClasses, null, false, false, false, false);
    }
    public RichTextStyleClass(RichTextStyleClass copy) {
        this(copy.styleClasses, copy.color, copy.bold, copy.italic, copy.underline, copy.strikethrough);
    }
    public Collection<String> getStyleClasses() {
        return this.styleClasses;
    }
    public String toCSS() {
        String css = "";
        css += this.color != null ? "-fx-fill: "+this.color.toString().replaceFirst("0x", "#")+";" : "";
        css += this.bold ? "-fx-font-weight: bold;" : "";
        css += this.italic ? "-fx-font-style: italic;" : "";
        css += this.underline ? "-fx-underline: true;" : "";
        css += this.strikethrough ? "-fx-strikethrough: true;" : "";
        return css;
    }

    public void fromStyle(TextStyles style, boolean value) {
        switch (style) {
            case BOLD -> this.bold = value;
            case ITALIC -> this.italic = value;
            case UNDERLINE -> this.underline = value;
            case STRIKETHROUGH -> this.strikethrough = value;
        }
    }
    public void fromStyle(TextStyles style) {
        switch (style) {
            case BOLD -> this.bold = !this.bold;
            case ITALIC -> this.italic = !this.italic;
            case UNDERLINE -> this.underline = !this.underline;
            case STRIKETHROUGH -> this.strikethrough = !this.strikethrough;
        }
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isBold() {
        return this.bold;
    }
    public void setBold(boolean bold) {
        this.bold = bold;
    }
    public boolean isItalic() {
        return this.italic;
    }
    public void setItalic(boolean italic) {
        this.italic = italic;
    }
    public boolean isUnderline() {
        return underline;
    }
    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
    public boolean isStrikethrough() {
        return strikethrough;
    }
    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }
}
