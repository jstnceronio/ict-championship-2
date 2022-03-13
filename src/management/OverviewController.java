package management;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    public Button btnExport;
    public Label lblTitle;
    public Label lblGame;
    private Tournament tournament;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
        lblTitle.setText(tournament.getTitle());
        lblGame.setText(tournament.getGameId().getName());
    }
}
