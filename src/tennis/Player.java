package tennis;

class Player {
    private String name;
    private int setsWon;
    private int gamesWon;

    Player(String name) {
        this.name = name;
        setsWon = 0;
        gamesWon = 0;
    }

    String getName() {
        return name;
    }

    int getGamesWon() {
        return gamesWon;
    }

    int getSetsWon() {
        return setsWon;
    }

    void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

}