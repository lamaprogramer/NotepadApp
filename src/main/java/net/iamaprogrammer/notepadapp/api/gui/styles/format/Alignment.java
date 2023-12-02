package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Alignment implements StyleFormat<TextAlignment> {
    private final TextAlignment alignment;
    public Alignment(TextAlignment alignment) {
        this.alignment = alignment;
    }
    public Alignment() {
        this(TextAlignment.LEFT);
    }

    @Override
    public TextAlignment getValue() {
        return this.alignment;
    }
    @Override
    public String getCSS() {
        return this.alignment != null ? "-fx-text-alignment: "+this.alignment.name().toLowerCase()+";" : "";
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public StyleFormat<TextAlignment> getDefault() {
        return new Alignment();
    }

    @Override
    public boolean compareCSS(String css) {
        //System.out.println(this.getCSS());
        //System.out.println(css);
        return this.getCSS().equals(css);
    }
}
