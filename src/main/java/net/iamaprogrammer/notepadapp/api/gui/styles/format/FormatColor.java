package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class FormatColor implements StyleFormat<Color> {
    private final Color color;
    public FormatColor() {
        this.color = Color.BLACK;
    }
    public FormatColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getValue() {
        return this.color;
    }
    @Override
    public String getCSS() {
        return this.color != null ? "-fx-fill: " + this.color.toString().replaceFirst("0x", "#") + ";" : "";
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
    @Override
    public StyleFormat<Color> getDefault() {
        return new FormatColor();
    }
}
