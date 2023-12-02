package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import javafx.scene.text.TextAlignment;

public class Underline implements StyleFormat<Boolean> {
    private static final String CSS = "-fx-underline: true;";
    public static final Underline INSTANCE = new Underline();
    private Underline() {}


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
        return new Underline();
    }
}
