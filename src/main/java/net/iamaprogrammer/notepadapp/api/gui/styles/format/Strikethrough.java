package net.iamaprogrammer.notepadapp.api.gui.styles.format;

public class Strikethrough implements StyleFormat<Boolean> {
    private static final String CSS = "-fx-strikethrough: true;";
    public static final Strikethrough INSTANCE = new Strikethrough();
    private Strikethrough() {}


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
        return new Strikethrough();
    }
}
