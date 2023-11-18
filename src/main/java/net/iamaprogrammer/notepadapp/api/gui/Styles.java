package net.iamaprogrammer.notepadapp.api.gui;

public enum Styles {
    BOLD("-fx-font-weight: bold;"),
    ITALIC("-fx-font-style: italic;");

    private final String styleClass;

    Styles(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }
}
