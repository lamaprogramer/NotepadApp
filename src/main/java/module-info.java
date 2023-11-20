module net.iamaprogrammer.notepadapp {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires reactfx;
    requires org.fxmisc.richtext;

    opens net.iamaprogrammer.notepadapp to javafx.fxml;
    opens net.iamaprogrammer.notepadapp.api.gui to javafx.fxml;
    exports net.iamaprogrammer.notepadapp;
    exports net.iamaprogrammer.notepadapp.api;
    exports net.iamaprogrammer.notepadapp.api.gui;
    exports net.iamaprogrammer.notepadapp.api.text.highlighter;
}