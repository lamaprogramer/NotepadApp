package net.iamaprogrammer.notepadapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.text.highlighter.HighlightingEngine;
import net.iamaprogrammer.notepadapp.api.text.highlighter.SyntaxPatterns;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final HighlightingEngine highlightingEngine = new HighlightingEngine();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("styles/syntax.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println(SyntaxPatterns.BRACKET);
        launch();

    }
}