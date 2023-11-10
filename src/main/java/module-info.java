module net.iamaprogrammer.notepadapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens net.iamaprogrammer.notepadapp to javafx.fxml;
    exports net.iamaprogrammer.notepadapp;
    exports net.iamaprogrammer.notepadapp.api;
}