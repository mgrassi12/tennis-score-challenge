package tennis;

import org.junit.Assert;
import org.junit.Test;

public class MatchTest {
    private Match winPoints(Match match, int points, String playerName) {
        int i = 0;
        while (i < points) {
            match.pointWonBy(playerName);
            i++;
        }
        return match;
    }

    private Match winGames(Match match, int points, String playerName) {
        int i = 0;
        while (i < points) {
            match.pointWonBy(playerName);
            match.pointWonBy(playerName);
            match.pointWonBy(playerName);
            match.pointWonBy(playerName);
            i++;
        }
        return match;
    }

    @Test
    public void shouldCreateMatchWithSet() {
        Match match = new Match("p1", "p2");
        Assert.assertNotNull(match.getSet());
    }

    @Test
    public void shouldCreateMatchWithSpecifiedPlayers() {
        Match match = new Match("John", "Sam");
        Assert.assertEquals("John", match.getPlayerOne().getName());
        Assert.assertEquals("Sam", match.getPlayerTwo().getName());
    }

    @Test
    public void shouldShowFifteenLove() {
        Match match = new Match("player 1", "player 2");
        match.pointWonBy("player 1");
        Assert.assertEquals("0-0, 15-0", match.score());
    }

    @Test
    public void shouldShowThirtyLove() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 2, "player 1");
        Assert.assertEquals("0-0, 30-0", match.score());
    }

    @Test
    public void shouldShowFortyLove() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 3, "player 1");
        Assert.assertEquals("0-0, 40-0", match.score());
    }

    @Test
    public void shouldShowGameWinAfterReachingFourScore() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 4, "player 1");
        Assert.assertEquals("1-0, 0-0", match.score());
    }

    @Test
    public void shouldShowDeuceWhenBothPlayersHaveThreeScore() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 3, "player 1");
        winPoints(match, 3, "player 2");
        Assert.assertEquals("0-0, Deuce", match.score());
    }

    @Test
    public void shouldShowAdvToPlayerOne() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 3, "player 1");
        winPoints(match, 3, "player 2");
        winPoints(match, 1, "player 1");
        Assert.assertEquals("0-0, Advantage player 1", match.score());
    }

    @Test
    public void shouldShowAdvToPlayerTwo() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 3, "player 1");
        winPoints(match, 4, "player 2");
        Assert.assertEquals("0-0, Advantage player 2", match.score());
    }

    @Test
    public void shouldShowGameWinAfterFourScoreAndTwoScoreAheadOfOpponent() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 3, "player 1");
        winPoints(match, 5, "player 2");
        Assert.assertEquals("0-1, 0-0", match.score());
    }

    @Test
    public void shouldShowSetHasMultipleGames() {
        Match match = new Match("player 1", "player 2");
        winPoints(match, 4, "player 1");
        winPoints(match, 4, "player 2");
        Assert.assertEquals("1-1, 0-0", match.score());
        Assert.assertEquals(2, match.getSet().getCurrentGameNo());
    }

    @Test
    public void shouldShowSetWinAfterSixGameWinsAndMoreThanTwoGameWinsApart() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 6, "player 1");
        Assert.assertEquals("The game has concluded. player 1 is the winner.", match.score());
        Assert.assertEquals(6, match.getSet().getCurrentGameNo());
    }

    @Test
    public void shouldNotEndIfPlayersAreCloserThanTwoGameWins() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 5, "player 1");
        winGames(match, 5, "player 2");
        winGames(match, 1, "player 1");
        Assert.assertEquals("6-5, 0-0", match.score());
        Assert.assertEquals(11, match.getSet().getCurrentGameNo());
    }

    @Test
    public void shouldEndIfPlayersAreMoreThanTwoGameWinsApart() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 5, "player 1");
        winGames(match, 5, "player 2");
        winGames(match, 2, "player 1");
        Assert.assertEquals("The game has concluded. player 1 is the winner.", match.score());
        Assert.assertEquals(12, match.getSet().getCurrentGameNo());
    }

    @Test
    public void shouldUseTiebreakerRulesForThirteenthGame() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 5, "player 1");
        winGames(match, 6, "player 2");
        winGames(match, 1, "player 1");
        winPoints(match, 6, "player 1");
        winPoints(match, 4, "player 2");
        Assert.assertEquals("6-6, 6-4", match.score());
        Assert.assertEquals(12, match.getSet().getCurrentGameNo());
    }

    @Test
    public void shouldWinTiebreakerAtSevenScoreIfMoreThanTwoScoreAhead() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 5, "player 1");
        winGames(match, 6, "player 2");
        winGames(match, 1, "player 1");
        winPoints(match, 5, "player 1");
        winPoints(match, 7, "player 2");
        Assert.assertEquals("The game has concluded. player 2 is the winner.", match.score());
        Assert.assertEquals(13, match.getSet().getCurrentGameNo());
    }


    @Test
    public void shouldWinTiebreakerAfterSevenScoreWhenMoreThanTwoScoreAhead() {
        Match match = new Match("player 1", "player 2");
        winGames(match, 5, "player 1");
        winGames(match, 6, "player 2");
        winGames(match, 1, "player 1");
        winPoints(match, 6, "player 1");
        winPoints(match, 7, "player 2");
        winPoints(match, 2, "player 1");
        winPoints(match, 3, "player 2");
        Assert.assertEquals("The game has concluded. player 2 is the winner.", match.score());
        Assert.assertEquals(13, match.getSet().getCurrentGameNo());
    }
}