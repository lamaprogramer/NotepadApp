package net.iamaprogrammer.notepadapp.api.text.highlighter;

import javafx.application.Platform;
import net.iamaprogrammer.notepadapp.api.gui.CodeTab;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HighlightingEngine {
    private LanguageHighlight language;
    private CodeArea codeArea;

    public void setSelectedTab(CodeTab selectedTab) {
        if (selectedTab != null) {
            this.language = selectedTab.getLanguage();
            this.codeArea = selectedTab.getCodeArea();
            if (this.language != null) {
                this.applyHighlighting();
            }
        }
    }

    public void applyHighlighting() {
        if (this.language != null) {
            Platform.runLater(() -> {
                this.codeArea.setStyleSpans(0, this.render(this.codeArea.getText()));
            });
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

    private String findStyleClass(Matcher matcher) {
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

    public StyleSpans<Collection<String>> render(String text) {
        Matcher matcher = this.createPattern().matcher(text);
        int lastMatch = 0;

        StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass = this.findStyleClass(matcher);
            builder.add(Collections.emptyList(), matcher.start() - lastMatch);
            builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastMatch = matcher.end();
        }
        return builder.create();
    }
}
