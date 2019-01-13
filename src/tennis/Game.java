package tennis;

class Game {
    private Player playerOne;
    private int playerOneScore;
    private Player playerTwo;
    private int playerTwoScore;

    Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    void pointWonBy(String playerName) {
        if (playerOne.getName().equals(playerName)) {
            playerOneScore++;
        }

        if (playerTwo.getName().equals(playerName)) {
            playerTwoScore++;
        }
    }

    int getPlayerOneScore() {
        return playerOneScore;
    }

    int getPlayerTwoScore() {
        return playerTwoScore;
    }

}
