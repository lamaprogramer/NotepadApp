package net.iamaprogrammer.notepadapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.Styles;
import net.iamaprogrammer.notepadapp.api.gui.TextEditor;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Templates;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private GridPane main_container;
    @FXML
    private TextEditor text_editor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.text_editor.addToggleButton("B", Styles.BOLD);
        this.text_editor.addToggleButton("I", Styles.ITALIC);
        this.text_editor.addToggleButton("U", Styles.UNDERLINE);
        this.text_editor.addToggleButton("S", Styles.STRIKETHROUGH);
        this.text_editor.addColorPicker();
        this.addNote();
    }
    @FXML
    protected void addNote() {
        this.text_editor.addTab(new EditorFile("New Tab", Templates.get("python")), true);
    }
    @FXML
    protected void openNote() throws IOException {
        // Select file from user's desktop.
        Stage stage = (Stage) this.main_container.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        this.text_editor.addTab(new EditorFile(
                        file.getName(),
                        Files.readString(file.toPath())
        ), true);
    }
}