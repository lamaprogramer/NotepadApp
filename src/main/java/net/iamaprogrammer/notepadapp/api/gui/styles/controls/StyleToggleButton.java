package net.iamaprogrammer.notepadapp.api.gui.styles.controls;

import javafx.scene.control.ToggleButton;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.function.Function;

public class StyleToggleButton<S extends StyleFormat<?>> extends ToggleButton implements StyleControl<S> {
    private final S style;
    private final StyleType styleType;
    private Function<S, Boolean> applyStyle;
    private boolean shouldApplyStyle = true;
    public StyleToggleButton(String name, S style, StyleType styleType) {
        super(name);
        this.style = style;
        this.styleType = styleType;
    }
    public StyleToggleButton(String name, S style) {
        this(name, style, StyleType.TEXT);
    }
    @Override
    public StyleType getStyleType() {
        return this.styleType;
    }
    @Override
    public void fire() {
        super.fire();
        if (this.applyStyle != null && this.shouldApplyStyle) {
            this.applyStyle.apply(this.style);
        }
    }
    @Override
    public void applyOnPress(Function<S, Boolean> function) {
        this.applyStyle = function;
    }
    @Override
    public boolean press(StyleFormat<?> styleFormat) {
        this.setShouldApplyStyle(false);
        boolean press = styleFormat != null && styleFormat.compareCSS(this.style.getCSS());
        //System.out.println(press ? styleFormat.getCSS() : "null");
        if (this.isSelected() != press) {
            this.fire();
            return true;
        }
        this.setSelected(press);
        this.setShouldApplyStyle(true);
        return false;
    }

    @Override
    public S getTextStyle() {
        return this.style;
    }

    @Override
    public String getStyleName() {
        return this.style.getName();
    }

    public boolean shouldApplyStyle() {
        return shouldApplyStyle;
    }
    public void setShouldApplyStyle(boolean shouldApplyStyle) {
        this.shouldApplyStyle = shouldApplyStyle;
    }
}
