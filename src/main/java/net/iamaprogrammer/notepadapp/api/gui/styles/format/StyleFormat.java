package net.iamaprogrammer.notepadapp.api.gui.styles.format;

import java.util.ArrayList;

public interface StyleFormat<S> {
    String getCSS();
    String getName();
    StyleFormat<S> getDefault();
    default S getValue() {
        return null;
    }
    default boolean compareCSS(String css) {
        return true;
    }
}
