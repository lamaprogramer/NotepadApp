package net.iamaprogrammer.notepadapp.api.gui.styles.controls;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.FormatColor;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.function.Function;

public class StyleColorPicker extends ColorPicker implements StyleControl<StyleFormat<Color>> {
    private Function<StyleFormat<Color>, Boolean> applyStyle;
    public StyleColorPicker() {
        super();
        this.setOnAction(actionEvent -> this.applyStyle.apply(new FormatColor(this.getValue())));
    }

    @Override
    public void applyOnPress(Function<StyleFormat<Color>, Boolean> function) {
        this.applyStyle = function;
    }

    @Override
    public boolean press(StyleFormat<?> styleFormat) {
        if (styleFormat != null) {
            this.setValue(styleFormat.getValue() != null ? (Color) styleFormat.getValue() : Color.BLACK);
            return true;
        }
        return false;
    }

    @Override
    public StyleFormat<Color> getTextStyle() {
        return new FormatColor(this.getValue());
    }

    @Override
    public String getStyleName() {
        return new FormatColor().getName();
    }
}
