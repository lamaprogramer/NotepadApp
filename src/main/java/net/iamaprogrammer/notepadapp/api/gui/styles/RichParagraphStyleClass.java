package net.iamaprogrammer.notepadapp.api.gui.styles;

import net.iamaprogrammer.notepadapp.api.gui.styles.format.Alignment;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RichParagraphStyleClass {
    private final Collection<String> styleClasses;
    private final Map<String, StyleFormat<?>> defaultStyles;
    private final Map<String, StyleFormat<?>> paragraphStyles;


    public RichParagraphStyleClass(Collection<String> styleClasses, Map<String, StyleFormat<?>> paragraphStyles, Map<String, StyleFormat<?>> defaultStyles) {
        this.styleClasses = styleClasses;
        this.defaultStyles = defaultStyles;

        if (!defaultStyles.isEmpty()) {
            defaultStyles.forEach(paragraphStyles::putIfAbsent);
        }
        this.paragraphStyles = paragraphStyles;
    }
    public RichParagraphStyleClass(Collection<String> styleClasses, Map<String, StyleFormat<?>> paragraphStyles) {
        this(styleClasses, paragraphStyles, new HashMap<>());
    }
    public RichParagraphStyleClass(Map<String, StyleFormat<?>> paragraphStyles) {
        this(Collections.emptyList(), paragraphStyles);
        //System.out.println(this.paragraphStyles);
    }
    public RichParagraphStyleClass(Collection<String> styleClasses) {
        this(styleClasses, new HashMap<>());
    }
    public RichParagraphStyleClass(RichParagraphStyleClass copy) {
        this(copy.styleClasses, new HashMap<>(copy.paragraphStyles), new HashMap<>(copy.defaultStyles));
    }
    public RichParagraphStyleClass() {
        this(Collections.emptyList());
    }

    public Collection<String> getStyleClasses() {
        return this.styleClasses;
    }
    public String toCSS() {
        StringBuilder css = new StringBuilder();
        for (StyleFormat<?> format : this.paragraphStyles.values()) {
            css.append(format.getCSS());
        }
        return css.toString();
    }
    public void updateStyle(StyleFormat<?> style, boolean criteria) {
        if (this.paragraphStyles.get(style.getName()) == null || criteria) {
            this.paragraphStyles.put(style.getName(), style);
        } else {
            this.paragraphStyles.remove(style.getName());
        }
    }
    public void addStyle(StyleFormat<?> style) {
        this.paragraphStyles.put(style.getName(), style);
    }
    public <S extends StyleFormat<?>> S getStyle(String name) {
        //System.out.println(name);
        return (S)this.paragraphStyles.get(name);
    }
}
