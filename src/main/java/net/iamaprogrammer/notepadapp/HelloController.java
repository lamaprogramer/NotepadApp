package net.iamaprogrammer.notepadapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.Note;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HelloController {

    @FXML
    private VBox main_container;
    @FXML
    private TabPane note_tab_pane;

    @FXML
    protected void addNote(ActionEvent event) {
        this.note_tab_pane.getTabs().add(this.createNoteTab(new Note(
                    "New Tab",
                    "This is some note content."
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
        box.maxWidthProperty().bind(this.note_tab_pane.widthProperty());
        box.maxHeightProperty().bind(this.note_tab_pane.heightProperty());

        tab.setContent(box);
        Text text = new Text(note.getBody());
        text.wrappingWidthProperty().bind(box.maxWidthProperty().divide(2));
        box.getChildren().add(text);
        box.setAlignment(Pos.CENTER);
        return tab;
    }
}