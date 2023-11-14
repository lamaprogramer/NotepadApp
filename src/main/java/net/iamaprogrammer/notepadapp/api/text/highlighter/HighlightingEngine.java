package net.iamaprogrammer.notepadapp.api.text.highlighter;

import javafx.scene.control.Tab;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.HighlightingProcess;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HighlightingEngine {
    private final Map<Integer, HighlightingProcess> PROCESSES = new HashMap<>();
    private LanguageHighlight language;

    public HighlightingEngine(LanguageHighlight language) {
        this.language = language;

//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(() -> {
//
//        });
    }

    public void addTask(CodeArea area, Tab tab) {
        HighlightingProcess process = new HighlightingProcess(area, this::render);
        PROCESSES.put(tab.hashCode(), process);
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
    public Map<Integer, HighlightingProcess> getProcesses() {
        return this.PROCESSES;
    }
}
