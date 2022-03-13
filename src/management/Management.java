package management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Management extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("tournament-list.fxml"));
        primaryStage.setTitle("Tournament List");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void changeScene(String fxml, String title) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setTitle(title);
        stg.getScene().setRoot(pane);
    }

    public void switchToTournamentView(Tournament t) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tournament-overview.fxml"));
        Parent pane = loader.load();

        OverviewController overviewController = loader.getController();
        overviewController.setTournament(t);
        System.out.println("Selected " + t.getTitle());
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
