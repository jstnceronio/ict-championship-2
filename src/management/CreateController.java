package management;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {
    public Button btnCreate;
    public TextField txtTitle;
    public ChoiceBox<Game> cbGame;
    public Label lblError;
    public Spinner<Integer> spSize;

    public void createTournament() throws IOException {
        if (!areFieldsValid()) {
            return;
        }
        Tournament tournamentToAdd = new Tournament(0, txtTitle.getText(), cbGame.getValue(), spSize.getValue(), null, 0);
        DBUtils.addTournament(tournamentToAdd);
        // switch scene
        Management m = new Management();
        m.changeScene("./tournament-list.fxml", "Tournament list");
    }

    private boolean areFieldsValid() {
        if (txtTitle.getText().isBlank()) {
            lblError.setText("Title can't be blank");
            return false;
        }
        if (cbGame.getSelectionModel().isEmpty()) {
            lblError.setText("A game must be chosen");
            return false;
        }
        if (txtTitle.getText().isBlank()) {
            lblError.setText("Title can't be blank");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbGame.setItems(DBUtils.getGameList());

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 100);
        valueFactory.setValue(2);
        spSize.setValueFactory(valueFactory);
    }
}
