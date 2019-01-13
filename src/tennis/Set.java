package tennis;

import java.util.*;

class Set {
    private List<Game> games = new ArrayList<>();
    private int currentGameNo;
    private Player playerOne;
    private Player playerTwo;

    Set(Player playerOne, Player playerTwo) {
        currentGameNo = 0;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        games.add(new Game(playerOne, playerTwo));
    }

    public int getCurrentGameNo() {
        return currentGameNo;
    }

    void pointWonBy(String playerName) {
        games.get(currentGameNo).pointWonBy(playerName);
    }

    void nextGame() {
        currentGameNo++;
        games.add(new Game(playerOne, playerTwo));
    }

    int getPlayerOneScore() {
        return games.get(currentGameNo).getPlayerOneScore();
    }

    int getPlayerTwoScore() {
        return games.get(currentGameNo).getPlayerTwoScore();
    }

}