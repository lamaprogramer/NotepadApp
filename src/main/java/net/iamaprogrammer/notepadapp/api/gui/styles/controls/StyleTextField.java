package net.iamaprogrammer.notepadapp.api.gui.styles.controls;

import javafx.scene.control.TextField;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.StyleFormat;

import java.util.function.Function;

public class StyleTextField extends TextField implements StyleControl<StyleFormat<String>> {
//    private final String style;
//    private Function<Integer, Boolean> applyStyle;
//
//    public StyleTextField(String value, Function<StyleFormat<String>, Boolean> applicationRules) {
//        this.style = applicationRules.apply(value) ? value : "";
//        this.textProperty().addListener((observableValue, oldVal, newVal) -> {
//            if (!applicationRules.apply(newVal)) {
//                this.setText(oldVal);
//            }
//        });
//    }


    @Override
    public void applyOnPress(Function<StyleFormat<String>, Boolean> function) {
        //this.setOnAction(actionEvent -> function.apply(this.style));
    }
    @Override
    public boolean press(StyleFormat<?> styleFormat) {
        return true;
    }
    @Override
    public StyleFormat<String> getTextStyle() {
        return null;
    }

    @Override
    public String getStyleName() {
        return null;
    }
}
