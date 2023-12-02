package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import javafx.scene.text.TextAlignment;

public class Bold implements StyleFormat<Boolean> {
    private static final String CSS = "-fx-font-weight: bold;";
    public static final Bold INSTANCE = new Bold();
    private Bold() {}

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
        return new Bold();
    }
}
