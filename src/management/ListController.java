package management;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    public TableView<Tournament> tvTournaments;
    public TableColumn<Tournament, String>  colTitle;
    public TableColumn<Tournament, String> colGame;
    public TableColumn<Tournament, String>  colWinner;
    public Button btnView;
    public Button btnCreate;

    private Tournament selectedTournament = null;

    public void createTournament(ActionEvent actionEvent) throws IOException {
        Management m = new Management();
        m.changeScene("./create-tournament.fxml", "Create Tournament");
    }

    public void viewTournament(ActionEvent actionEvent) throws IOException {
        Management m = new Management();
        m.switchToTournamentView(selectedTournament);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnView.setDisable(true);
        showTournaments();

        tvTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tournament>() {
            @Override
            public void changed(ObservableValue<? extends Tournament> observableValue, Tournament tournament, Tournament t1) {
                if (tvTournaments.getSelectionModel().getSelectedItem() != null) {
                    btnView.setDisable(false);

                    selectedTournament = tvTournaments.getSelectionModel().getSelectedItem();
                }
            }
        });
    }

    private void showTournaments() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGame.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getGameId().getName()));
        colWinner.setCellValueFactory(new PropertyValueFactory<>("game"));
        colWinner.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getWinnerParticipantId().getName()));
        tvTournaments.setItems(DBUtils.getTournamentList());
    }
}
