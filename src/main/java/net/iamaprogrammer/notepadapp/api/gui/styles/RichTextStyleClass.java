package net.iamaprogrammer.notepadapp.api.gui.styles;

import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RichTextStyleClass {
    private final Collection<String> styleClasses;
    private final Map<String, StyleFormat<?>> textStyles;

    public RichTextStyleClass(Collection<String> styleClasses, Map<String, StyleFormat<?>> textStyles) {
        this.styleClasses = styleClasses;
        this.textStyles = textStyles;
    }
    public RichTextStyleClass(Collection<String> styleClasses) {
        this(styleClasses, new HashMap<>());
    }
    public RichTextStyleClass(RichTextStyleClass copy) {
        this(copy.styleClasses, new HashMap<>(copy.textStyles));
    }
    public RichTextStyleClass() {
        this(Collections.emptyList());
    }
    public Collection<String> getStyleClasses() {
        return this.styleClasses;
    }

    public String toCSS() {
        StringBuilder css = new StringBuilder();
        for (StyleFormat<?> format : this.textStyles.values()) {
            css.append(format.getCSS());
        }
        return css.toString();
    }
    public void updateStyle(StyleFormat<?> style, boolean criteria) {
        if (this.textStyles.get(style.getName()) == null || criteria) {
            this.textStyles.put(style.getName(), style);
        } else {
            this.textStyles.remove(style.getName());
        }
    }
    public <S extends StyleFormat<?>> S getStyle(String name) {
        return (S)this.textStyles.get(name);
    }
}
