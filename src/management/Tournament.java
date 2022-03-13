package management;

public class Tournament {
    private int id;
    private String title;
    private Game gameId;
    private int size;
    private Participant winnerParticipantId;
    private int tournamentState;

    public Tournament(int id, String title, Game gameId, int size, Participant winnerParticipantId, int tournamentState) {
        this.id = id;
        this.title = title;
        this.gameId = gameId;
        this.size = size;
        this.winnerParticipantId = winnerParticipantId;
        this.tournamentState = tournamentState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Participant getWinnerParticipantId() {
        return winnerParticipantId;
    }

    public void setWinnerParticipantId(Participant winnerParticipantId) {
        this.winnerParticipantId = winnerParticipantId;
    }

    public int getTournamentState() {
        return tournamentState;
    }

    public void setTournamentState(int tournamentState) {
        this.tournamentState = tournamentState;
    }
}
