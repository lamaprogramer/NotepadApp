package net.iamaprogrammer.notepadapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.styles.TextStyles;
import net.iamaprogrammer.notepadapp.api.gui.editor.TextEditor;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.Bold;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.Italic;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.Strikethrough;
import net.iamaprogrammer.notepadapp.api.gui.styles.format.Underline;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Templates;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;

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
        this.text_editor.addToggleButton(new FontIcon(MaterialDesignF.FORMAT_BOLD), Bold.INSTANCE);
        this.text_editor.addToggleButton(new FontIcon(MaterialDesignF.FORMAT_ITALIC), Italic.INSTANCE);
        this.text_editor.addToggleButton(new FontIcon(MaterialDesignF.FORMAT_UNDERLINE), Underline.INSTANCE);
        this.text_editor.addToggleButton(new FontIcon(MaterialDesignF.FORMAT_STRIKETHROUGH), Strikethrough.INSTANCE);

        this.text_editor.addAlignmentButtons();
        //this.text_editor.addToggleButton("P", TextAlignment.RIGHT);
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