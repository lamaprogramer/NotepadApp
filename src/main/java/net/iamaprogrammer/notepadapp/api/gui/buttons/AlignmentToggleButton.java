package net.iamaprogrammer.notepadapp.api.gui.buttons;

import javafx.scene.control.ToggleButton;
import javafx.scene.text.TextAlignment;
import net.iamaprogrammer.notepadapp.api.gui.styles.TextStyles;

import java.util.function.Function;

public class AlignmentToggleButton extends ToggleButton {
    private final TextAlignment alignment;
    private final Function<TextAlignment, Boolean> applyStyle;
    public AlignmentToggleButton(String name, TextAlignment alignment, Function<TextAlignment, Boolean> applyStyle) {
        super(name);
        this.alignment = alignment;
        this.applyStyle = applyStyle;
    }
    @Override
    public void fire() {
        super.fire();
        this.applyStyle.apply(this.alignment);
    }
}
