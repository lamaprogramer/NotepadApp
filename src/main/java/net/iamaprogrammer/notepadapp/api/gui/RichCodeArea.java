package net.iamaprogrammer.notepadapp.api.gui;

import javafx.application.Platform;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichParagraphStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.styles.RichTextStyleClass;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.model.*;
import org.reactfx.Subscription;

import java.util.Collections;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RichCodeArea extends StyledTextArea<RichParagraphStyleClass, RichTextStyleClass> {
    private final Subscription subscription;
    private LanguageHighlight language;

    public RichCodeArea(
            EditableStyledDocument<RichParagraphStyleClass, String, RichTextStyleClass> document,
            boolean preserveStyle,
            BiFunction<RichCodeArea, PlainTextChange, Boolean> apply,
            RichParagraphStyleClass defaultParagraphStyle,
            RichTextStyleClass defaultTextStyle
    ) {
        super(defaultParagraphStyle,
                (paragraph, styleClasses) -> {
                    paragraph.getStyleClass().addAll(styleClasses.getStyleClasses());
                    paragraph.setStyle(styleClasses.toCSS());
                },
                defaultTextStyle,
                (text, styleClass) -> {
                    text.getStyleClass().addAll(styleClass.getStyleClasses());
                    text.setStyle(styleClass.toCSS());
                }, document, preserveStyle);

        this.subscription = this.multiPlainChanges()
                .subscribe((changes) -> {
                    if (apply != null) {
                        apply.apply(this, changes.get(0));
                    }
                    if (this.language != null) {
                        Matcher pattern = Pattern.compile("^\\w$").matcher(changes.get(0).getInserted());
                        if (pattern.find()) {
                            this.applyHighlighting(this.getCurrentParagraph(), 0, this);
                        } else {
                            this.applyHighlighting(0, this.getText());
                        }
                    }
                });
    }

    public RichCodeArea(EditableStyledDocument<RichParagraphStyleClass, String, RichTextStyleClass> document, boolean preserveStyle, BiFunction<RichCodeArea, PlainTextChange, Boolean> apply) {
        this(document, preserveStyle, apply, new RichParagraphStyleClass(), new RichTextStyleClass());
    }
    public RichCodeArea(boolean preserveStyle, BiFunction<RichCodeArea, PlainTextChange, Boolean> apply) {
        this(new SimpleEditableStyledDocument<>(new RichParagraphStyleClass(), new RichTextStyleClass()), preserveStyle, apply);
    }
    public RichCodeArea(BiFunction<RichCodeArea, PlainTextChange, Boolean> apply) {
        this(true, apply);
    }
    public RichCodeArea(boolean preserveStyle) {
        this(new SimpleEditableStyledDocument<>(new RichParagraphStyleClass(), new RichTextStyleClass()), preserveStyle, null);
    }
    public RichCodeArea() {
        this(true);
    }


    @Override
    public void appendText(String text) {
        super.appendText(text);
        this.insertText(0, "");
        this.requestFollowCaret();
    }
    public RichCodeArea withHighlighting(LanguageHighlight language) {
        this.language = language;
        return this;
    }
    public void applyHighlighting(int start, String text) {
        if (this.language != null) {
            Platform.runLater(() -> this.setStyleSpans(start, this.render(text)));
        }
    }
    public void applyHighlighting(int paragraph, int start, StyledTextArea<RichParagraphStyleClass, RichTextStyleClass> code) {
        if (this.language != null) {
            Paragraph<RichParagraphStyleClass, String, RichTextStyleClass> text = code.getParagraph(paragraph);
            Platform.runLater(() -> {
                try {
                    this.setStyleSpans(paragraph, start, this.render(text.getText()));
                } catch (IllegalStateException ignored) {}
            });
            //String text = code.getParagraph(paragraph).getText();
        }
    }
    private String fromSyntaxPattern() {
        StringBuilder builder = new StringBuilder();
        for (SyntaxPatterns pattern : this.language.syntaxPatterns()) {
            builder.append(pattern.get());
        }
        return builder.toString();
    }
    private Pattern createPattern() {
        return Pattern.compile("(?<KEYWORD>" + "\\b("+String.join("|",this.language.keywords())+")\\b" + ")" + this.fromSyntaxPattern());
    }
    private String chooseStyleClass(Matcher matcher) {
        if (matcher.group("KEYWORD") != null) {
            return "keyword";
        }
        for (SyntaxPatterns pattern : this.language.syntaxPatterns()) {
            if (matcher.group(pattern.name()) != null) {
                return pattern.name().toLowerCase();
            }
        }
        return null;
    }
    public StyleSpans<RichTextStyleClass> render(String text) {
        Matcher matcher = this.createPattern().matcher(text);
        int lastMatch = 0;

        StyleSpansBuilder<RichTextStyleClass> builder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass = this.chooseStyleClass(matcher);
            builder.add(new RichTextStyleClass(Collections.emptyList()), matcher.start() - lastMatch);
            builder.add(new RichTextStyleClass(Collections.singleton(styleClass)), matcher.end() - matcher.start());
            lastMatch = matcher.end();
        }
        return builder.create();
    }
}
