package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import javafx.scene.text.TextAlignment;

public class Italic implements StyleFormat<Boolean> {
    private static final String CSS = "-fx-font-style: italic;";
    public static final Italic INSTANCE = new Italic();
    private Italic() {}


    @Override
    public String getCSS() {
        return CSS;
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
    @Override
    public StyleFormat<Boolean> getDefault() {
        return new Italic();
    }
}
