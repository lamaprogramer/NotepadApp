package net.iamaprogrammer.notepadapp.api.gui;

import javafx.css.Style;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.function.Function;

public class StyleButton extends ToggleButton {

    private final Styles style;
    private final Function<Styles, Boolean> applyStyle;
    public StyleButton(Styles style, Function<Styles, Boolean> applyStyle) {
        super();
        this.style = style;
        this.applyStyle = applyStyle;
    }
    @Override
    public void fire() {
        super.fire();
        this.applyStyle.apply(this.style);
    }
}
