package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBUtils {

    private static Participant undecidedParticipant = new Participant(0, "undecided", true, false);

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tournament_management", "root", "root");
    }

    public static void addTournament(Tournament t) {
        try {
            Connection connection = getConnection();
            PreparedStatement psInsert = connection.prepareStatement(
                    "INSERT INTO tournament (title, gameId, size, tournamentState) VALUES (?, ?, ?, ?)"
            );
            psInsert.setString(1, t.getTitle());
            psInsert.setInt(2, t.getGameId().getId());
            psInsert.setInt(3, t.getSize());
            psInsert.setInt(4, t.getTournamentState());
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Game> getGameList() {
        ObservableList<Game> gameList = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM game");
            ResultSet rs = psSelect.executeQuery();
            Game game;
            while (rs.next()) {
                game = new Game(
                        rs.getInt("gameId"),
                        rs.getString("name")
                );
                gameList.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameList;
    }

    public static ObservableList<Tournament> getTournamentList() {
        ObservableList<Tournament> tournamentList = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM tournament");
            ResultSet rs = psSelect.executeQuery();
            Tournament tournament;
            while (rs.next()) {
                Game game = getGameFromId(rs.getInt("gameId"));
                Participant participant = getParticipantFromId(rs.getInt("winnerParticipantId")) != null ?
                        getParticipantFromId(rs.getInt("winnerParticipantId")) :
                        undecidedParticipant;
                tournament = new Tournament(
                        rs.getInt("tournamentId"),
                        rs.getString("title"),
                        game,
                        rs.getInt("size"),
                        participant,
                        rs.getInt("tournamentState")
                );
                tournamentList.add(tournament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournamentList;
    }

    public static Game getGameFromId(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM game WHERE gameId = ?");
            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();
            Game game = null;
            if (rs.next()) {
                game = new Game(
                        rs.getInt("gameId"),
                        rs.getString("name")
                );
            }
            return game;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // smth went wrong :(
    }

    public static Participant getParticipantFromId(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM participant WHERE participantId = ?");
            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();
            Participant participant = null;
            if (rs.next()) {
                participant = new Participant(
                        rs.getInt("participantId"),
                        rs.getString("name"),
                        rs.getBoolean("isTemporary"),
                        rs.getBoolean("isTeam")
                );
            }
            return participant;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // smth went wrong :(
    }
}
