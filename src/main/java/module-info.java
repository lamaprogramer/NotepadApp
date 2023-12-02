module net.iamaprogrammer.notepadapp {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires reactfx;
    requires org.fxmisc.richtext;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;

    opens net.iamaprogrammer.notepadapp to javafx.fxml;
    opens net.iamaprogrammer.notepadapp.api.gui to javafx.fxml;
    exports net.iamaprogrammer.notepadapp;
    exports net.iamaprogrammer.notepadapp.api;
    exports net.iamaprogrammer.notepadapp.api.gui;
    exports net.iamaprogrammer.notepadapp.api.text.highlighter;
    exports net.iamaprogrammer.notepadapp.api.gui.styles;
    exports net.iamaprogrammer.notepadapp.api.gui.styles.format;
    opens net.iamaprogrammer.notepadapp.api.gui.styles to javafx.fxml;
    exports net.iamaprogrammer.notepadapp.api.gui.editor;
    opens net.iamaprogrammer.notepadapp.api.gui.editor to javafx.fxml;
    exports net.iamaprogrammer.notepadapp.api.gui.styles.controls;
}