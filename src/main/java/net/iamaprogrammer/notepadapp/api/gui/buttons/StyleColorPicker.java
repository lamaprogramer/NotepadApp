package net.iamaprogrammer.notepadapp.api.gui.buttons;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.function.Function;

public class StyleColorPicker extends ColorPicker {
    private final Function<Color, Boolean> applyStyle;
    public StyleColorPicker(Function<Color, Boolean> applyStyle) {
        super();
        this.applyStyle = applyStyle;
        this.setOnAction(actionEvent -> this.applyStyle.apply(this.getValue()));
    }
}
