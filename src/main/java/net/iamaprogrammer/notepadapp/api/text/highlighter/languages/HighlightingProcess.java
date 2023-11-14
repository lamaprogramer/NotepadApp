package net.iamaprogrammer.notepadapp.api.text.highlighter.languages;

import javafx.application.Platform;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;

import java.util.Collection;
import java.util.function.Function;

public class HighlightingProcess extends Thread {
    private boolean inFocus = true;
    private final CodeArea area;
    private final Function<String, StyleSpans<Collection<String>>> render;

    public HighlightingProcess(CodeArea area, Function<String, StyleSpans<Collection<String>>> render) {
        this.render = render;
        this.area = area;
//        this.area.textProperty().addListener((observable, oldValue, newValue) -> {
//            this.inFocus = !newValue.isEmpty();
//            System.out.println(this.inFocus);
//        });
        this.start();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            if (this.inFocus) {
                this.inFocus = false;
                System.out.println("Set style at thread: "+this.getId());
                Platform.runLater(() -> {
                    this.area.clearStyle(0, this.area.getLength());
                    this.area.setStyleSpans(0, render.apply(this.area.getText()));
                });
            }
        }
    }
}
