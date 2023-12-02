package net.iamaprogrammer.notepadapp.api.gui.styles.controls;

import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.function.Function;

public interface StyleControl<S extends StyleFormat<?>> {
    void applyOnPress(Function<S, Boolean> function);
    boolean press(StyleFormat<?> styleFormat);
    S getTextStyle();
    String getStyleName();
    default StyleType getStyleType() {
        return StyleType.TEXT;
    }
}

