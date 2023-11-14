package net.iamaprogrammer.notepadapp.api.text.highlighter;

import javafx.application.Platform;
import net.iamaprogrammer.notepadapp.api.gui.CodeTab;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HighlightingEngine {
    private final ExecutorService service;
    private LanguageHighlight language;
    private CodeTab selectedTab;
    private CodeArea codeArea;
    private boolean changeMade = true;

    public HighlightingEngine() {
        this.service = Executors.newSingleThreadExecutor();

        this.service.execute(() -> {
            while (!this.service.isShutdown()) {
                if (this.selectedTab != null && this.language != null && this.changeMade) {
                    this.changeMade = false;
                    System.out.println("Set style at tab: "+this.selectedTab);
                    Platform.runLater(() -> {
                        this.codeArea.clearStyle(0, this.codeArea.getLength());
                        this.codeArea.setStyleSpans(0, this.render(this.codeArea.getText()));
                    });
                }
            }
        });
    }

    public void setSelectedTab(CodeTab selectedTab) {
        this.selectedTab = selectedTab;
        if (selectedTab != null) {
            this.language = selectedTab.getLanguage();
            this.codeArea = selectedTab.getCodeArea();
            this.changeMade = true;
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
        //builder.add(Collections.emptyList(), text.length() - lastMatch);
        StyleSpans<Collection<String>> styles = builder.create();
        //System.out.println(styles);
        return styles;
    }

    public LanguageHighlight getLanguage() {
        return language;
    }

    public void setLanguage(LanguageHighlight language) {
        this.language = language;
    }
    public ExecutorService getService() {
        return this.service;
    }
    public void setChangeMade(boolean bool) {
        this.changeMade = true;
    }
}
