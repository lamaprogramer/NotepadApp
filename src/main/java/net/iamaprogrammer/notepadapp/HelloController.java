package net.iamaprogrammer.notepadapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.Note;
import net.iamaprogrammer.notepadapp.api.gui.CodeTab;
import net.iamaprogrammer.notepadapp.api.text.highlighter.LanguageHighlight;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Languages;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Templates;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private GridPane main_container;
    @FXML
    private TabPane note_tab_pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.note_tab_pane.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTab, newTab) ->
                HelloApplication.highlightingEngine.setSelectedTab((CodeTab) newTab));
    }

    @FXML
    protected void addNote() {
        // Create new note.
        this.note_tab_pane.getTabs().add(this.createNoteTab(new Note("New Tab", Templates.get("python"))));
    }

    @FXML
    protected void openNote() throws IOException {
        // Select file from user's desktop.
        Stage stage = (Stage) this.main_container.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        this.note_tab_pane.getTabs().add(this.createNoteTab(new Note(
                        file.getName(),
                        Files.readString(file.toPath())
        )));
    }

    protected CodeTab createNoteTab(Note note) {
        LanguageHighlight highlight = Languages.generateFromFile(note.getTitle());
        CodeTab tab = new CodeTab(highlight, note.getTitle());
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.maxWidthProperty().bind(this.note_tab_pane.widthProperty());
        box.maxHeightProperty().bind(this.note_tab_pane.heightProperty());
        tab.setContent(box);

        CodeArea text = new CodeArea();
        text.setStyle("-fx-font-size: "+24);
        text.setId("note-area");
        // Update highlighting engine.
        text.textProperty().addListener((observable, oldValue, newValue) ->
                HelloApplication.highlightingEngine.applyHighlighting());

        text.setParagraphGraphicFactory(LineNumberFactory.get(text)); // Line numbers.
        text.prefHeightProperty().bind(note_tab_pane.heightProperty());
        text.appendText(note.getBody());
        tab.setCodeArea(text);

        box.getChildren().add(tab.getCodeArea());
        return tab;
    }
}