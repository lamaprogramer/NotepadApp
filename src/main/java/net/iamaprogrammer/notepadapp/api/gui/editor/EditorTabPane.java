package net.iamaprogrammer.notepadapp.api.gui.editor;

import javafx.scene.control.TabPane;
import net.iamaprogrammer.notepadapp.api.gui.styles.controls.StyleControl;

import java.util.HashMap;
import java.util.Map;

public class EditorTabPane extends TabPane {
    private final Map<String, StyleControl<?>> styleManipulators = new HashMap<>();

    public Map<String, StyleControl<?>> getStyleManipulators() {
        return this.styleManipulators;
    }
}
