package net.iamaprogrammer.notepadapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.Note;
import net.iamaprogrammer.notepadapp.api.text.highlighter.HighlightingEngine;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.JavaLanguage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpansBuilder;

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


    private static final HighlightingEngine highlighingEngine = new HighlightingEngine(new JavaLanguage());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void addNote(ActionEvent event) {
        this.note_tab_pane.getTabs().add(this.createNoteTab(new Note(
                    "New Tab",
                    "{This is some note content. }"
        )));
    }

    @FXML
    protected void openNote(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.main_container.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        this.note_tab_pane.getTabs().add(this.createNoteTab(new Note(
                        file.getName(),
                        Files.readString(file.toPath())
        )));
    }




    protected Tab createNoteTab(Note note) {
        Tab tab = new Tab(note.getTitle());
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.maxWidthProperty().bind(this.note_tab_pane.widthProperty());
        box.maxHeightProperty().bind(this.note_tab_pane.heightProperty());

        tab.setContent(box);
        CodeArea text = new CodeArea();
        applyStyles(text);
        text.setId("note-area");

        text.setParagraphGraphicFactory(LineNumberFactory.get(text));
        text.prefHeightProperty().bind(note_tab_pane.heightProperty());
        text.appendText(note.getBody());
        highlighingEngine.addTask(text, tab);

        box.getChildren().add(text);
        tab.setOnClosed(event -> {
            highlighingEngine.getProcesses().get(tab.hashCode()).interrupt();
            highlighingEngine.getProcesses().remove(tab.hashCode());
            System.out.println("Terminated thread");
        });
        return tab;
    }

    protected void applyStyles(StyleClassedTextArea text) {
        text.setStyle("-fx-font-size: "+24);
    }
}