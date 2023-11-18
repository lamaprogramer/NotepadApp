module net.iamaprogrammer.notepadapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires reactfx;
    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.fxmisc.richtext;

    opens net.iamaprogrammer.notepadapp to javafx.fxml;
    exports net.iamaprogrammer.notepadapp;
    exports net.iamaprogrammer.notepadapp.api;
    exports net.iamaprogrammer.notepadapp.api.gui;
    exports net.iamaprogrammer.notepadapp.api.text.highlighter;
}