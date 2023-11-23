package net.iamaprogrammer.notepadapp.api.gui.buttons;

import javafx.scene.control.ToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.styles.TextStyles;

import java.util.function.Function;

public class StyleToggleButton extends ToggleButton {
    private final TextStyles style;
    private final Function<TextStyles, Boolean> applyStyle;
    public StyleToggleButton(String name, TextStyles style, Function<TextStyles, Boolean> applyStyle) {
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
