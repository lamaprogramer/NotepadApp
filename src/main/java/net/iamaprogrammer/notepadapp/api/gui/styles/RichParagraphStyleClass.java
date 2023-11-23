package net.iamaprogrammer.notepadapp.api.gui.styles;

import javafx.scene.text.TextAlignment;

import java.util.Collection;
import java.util.Collections;

public class RichParagraphStyleClass {
    private final Collection<String> styleClasses;
    private TextAlignment alignment = TextAlignment.LEFT;
    public RichParagraphStyleClass(Collection<String> styleClasses, TextAlignment alignment) {
        this.styleClasses = styleClasses;
        this.alignment = alignment;
    }
    public RichParagraphStyleClass(Collection<String> styleClasses) {
        this.styleClasses = styleClasses;
    }
    public RichParagraphStyleClass(RichParagraphStyleClass copy) {
        this(copy.styleClasses, copy.alignment);
    }
    public RichParagraphStyleClass() {
        this(Collections.emptyList());
    }

    public String toCSS() {
        String css = "";
        css += alignment != null ? "-fx-text-alignment: "+this.alignment.name().toLowerCase()+";" : "";
        return css;
    }

    public void fromStyle(TextStyles style, boolean value) {
        switch (style) {

        }
    }
    public void fromStyle(TextStyles style) {
        switch (style) {

        }
    }
    public Collection<String> getStyleClasses() {
        return styleClasses;
    }
    public TextAlignment getAlignment() {
        return this.alignment;
    }
    public void setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
    }
}
