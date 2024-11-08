package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FindTextButtonController {
    @FXML Button button;
    @FXML TextArea writeText;
    @FXML TextArea foundedText;
    String text;


    private final Stage stage = new Stage();
    private final FileChooser chooser = new FileChooser();
    @FXML
    public void findText() {
            File selectedFile = chooser.showOpenDialog(stage);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), StandardCharsets.UTF_8));
                int c;
                text = new String();
                while ((c = reader.read()) != -1) {
                    char ch = (char) c;
                    text += ch;
                }

                foundedText.setText(text);
            } catch (Exception ignored) {

            };
    }

    int counter = 0;
    @FXML
    public void find() {
        IndexRange range = foundedText.getSelection();
        int start = range.getEnd();
        int end = foundedText.getText().indexOf(writeText.getText(), start);
        if(end == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "End of search, amount of words finded: " + counter);
            alert.show();
            foundedText.selectRange(0, 0);
            counter = 0;
            return;
        }

        foundedText.selectRange(end, end + writeText.getLength());
        counter++;
    }
}
