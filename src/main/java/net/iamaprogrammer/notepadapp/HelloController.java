package net.iamaprogrammer.notepadapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.iamaprogrammer.notepadapp.api.EditorFile;
import net.iamaprogrammer.notepadapp.api.gui.CodeTab;
import net.iamaprogrammer.notepadapp.api.gui.RichTextStyleClass;
import net.iamaprogrammer.notepadapp.api.gui.Styles;
import net.iamaprogrammer.notepadapp.api.gui.TextEditor;
import net.iamaprogrammer.notepadapp.api.text.highlighter.Templates;
import net.iamaprogrammer.notepadapp.api.text.highlighter.languages.PythonLanguage;

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
//    @FXML
//    private TabPane note_tab_pane;
//    @FXML
//    private ButtonBar style_bar;

    private final RichTextStyleClass textStyle = new RichTextStyleClass();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addNote();

//        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
//        colorPicker.setOnAction(actionEvent -> {
//            this.textStyle.setColor(colorPicker.getValue());
//        });
//        this.style_bar.getButtons().add(colorPicker);
    }

    @FXML
    protected void addNote() {
        this.text_editor.addTab(new EditorFile("New Tab", Templates.get("python")), true);
        this.text_editor.addButton(Styles.BOLD);
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

    protected CodeTab createNoteTab(EditorFile note) {
//        LanguageHighlight highlight = Languages.generateFromFile(note.getTitle());
//        CodeTab tab = new CodeTab(highlight, note.getTitle());
//        VBox box = new VBox();
//        box.setAlignment(Pos.CENTER);
//        box.maxWidthProperty().bind(this.note_tab_pane.widthProperty());
//        box.maxHeightProperty().bind(this.note_tab_pane.heightProperty());
//        tab.setContent(box);
//
//        RichCodeArea textArea = new RichCodeArea((instance, change) -> {
//            int to = change.getInsertionEnd();
//            int from = to - change.getInserted().length();
//            if (from < to) {
//                instance.setStyle(from, to, new RichTextStyleClass(this.textStyle));
//            }
//            return true;
//        }).withHighlighting(tab.getLanguage());
//        textArea.setStyle("-fx-font-size: "+24);
//        textArea.setId("note-area");
//        // Update highlighting engine.
//        textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea)); // Line numbers.
//        textArea.prefHeightProperty().bind(note_tab_pane.heightProperty());
//        textArea.appendText(note.getBody());
//        //text.setTextInsertionStyle(Collections.);
//        tab.setCodeArea(textArea);
//
//        box.getChildren().add(tab.getCodeArea());
//        return tab;
        return new CodeTab(new PythonLanguage(), "");
    }
}