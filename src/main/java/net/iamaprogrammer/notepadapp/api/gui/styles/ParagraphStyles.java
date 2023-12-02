package net.iamaprogrammer.notepadapp.api.gui.styles;

public enum ParagraphStyles {
    ALIGNMENT_LEFT("-fx-text-alignment: left;"),
    ALIGNMENT_RIGHT("-fx-text-alignment: right;"),
    ALIGNMENT_CENTER("-fx-text-alignment: center;"),
    ALIGNMENT_JUSTIFY("-fx-text-alignment: justify;");

    private final String style;
    ParagraphStyles(String style) {
        this.style = style;
    }
    public String getStyle() {
        return style;
    }
}
