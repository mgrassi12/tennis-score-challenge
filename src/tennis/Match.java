package tennis;

public class Match {
    private Set set;
    private Player playerOne;
    private Player playerTwo;

    public Match(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
        set = new Set(playerOne, playerTwo);
    }

    public Set getSet() {
        return set;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public String score() {
        String matchStatus;
        String gameStatus;
        String scoreStatus = "";
        int playerOneScore = set.getPlayerOneScore();
        int playerTwoScore = set.getPlayerTwoScore();
        int currentGame = set.getCurrentGameNo();

        gameStatus = String.format("%d-%d, ", playerOne.getGamesWon(), playerTwo.getGamesWon());

        if (playerOneScore >= 3 && playerTwoScore >= 3 && currentGame != 12) {
            if (playerOneScore == playerTwoScore) {
                scoreStatus = "Deuce";
            }

            if (playerOneScore == playerTwoScore + 1) {
                scoreStatus = "Advantage " + playerOne.getName();
            }

            if (playerOneScore == playerTwoScore - 1) {
                scoreStatus = "Advantage " + playerTwo.getName();
            }
        } else if (currentGame == 12) {
            scoreStatus = playerOneScore + "-" + playerTwoScore;
        } else {
            scoreStatus = getTennisScoreConversion(playerOneScore) + "-" + getTennisScoreConversion(playerTwoScore);
        }

        matchStatus = gameStatus + scoreStatus;

        if (playerOne.getSetsWon() > 0 || playerTwo.getSetsWon() > 0) {
            matchStatus = "The game has concluded.";
        }

        return matchStatus;
    }

    public void pointWonBy(String playerName) {
        if (set.getCurrentGameNo() < 12) {
            set.pointWonBy(playerName);
            updateGame(4);
        } else {
            set.pointWonBy(playerName);
            updateGame(7);
        }
    }

    private void gameWonBy(Player player) {
        player.setGamesWon(player.getGamesWon() + 1);
        set.nextGame();
    }

    private void updateGame(int minScore) {
        int playerOneScore = set.getPlayerOneScore();
        int playerTwoScore = set.getPlayerTwoScore();

        if (playerOneScore >= minScore || playerTwoScore >= minScore) {
            if (playerOneScore >= playerTwoScore + 2) {
                gameWonBy(playerOne);
            }
            if (playerTwoScore >= playerOneScore + 2) {
                gameWonBy(playerTwo);
            }
        }

        int playerOneGamesWon = playerOne.getGamesWon();
        int playerTwoGamesWon = playerTwo.getGamesWon();
        if (playerOneGamesWon >= 6 || playerTwoGamesWon >= 6) {
            if (playerOneGamesWon >= playerTwoGamesWon + 2) {
                playerOne.setSetsWon(1);
            }
            if (playerTwoGamesWon >= playerOneGamesWon + 2) {
                playerTwo.setSetsWon(1);
            }

            if (playerOneGamesWon == 7) {
                playerOne.setSetsWon(1);
            }

            if (playerTwoGamesWon == 7) {
                playerTwo.setSetsWon(1);
            }
        }
    }

    private String getTennisScoreConversion(int score) {
        if (score == 1) {
            return "15";
        }

        if (score == 2) {
            return "30";
        }

        if (score == 3) {
            return "40";
        }

        return "0";
    }

}
