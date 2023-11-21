package net.iamaprogrammer.notepadapp.api.gui.buttons;

import javafx.scene.control.ToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.Styles;

import java.util.function.Function;

public class StyleToggleButton extends ToggleButton {

    private final Styles style;
    private final Function<Styles, Boolean> applyStyle;
    public StyleToggleButton(String name, Styles style, Function<Styles, Boolean> applyStyle) {
        super(name);
        this.style = style;
        this.applyStyle = applyStyle;
    }
    @Override
    public void fire() {
        super.fire();
        this.applyStyle.apply(this.style);
    }
}
