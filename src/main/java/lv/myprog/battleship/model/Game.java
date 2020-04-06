package lv.myprog.battleship.model;

public class Game {
    private Player player1;
    private Player player2;

    public void join(Player player) {
        if (player1 == null) {
            player1 = player;
        } else {
            player2 = player;
        }
    }

    public boolean isReady() {
        return player1 != null && player2 != null;
    }


    public Player getOpponent(Player yourPlayer) {
        if (player1 == null || player2 == null) {
            return null;
        } else {
            if (yourPlayer.equals(player1)) {
                return player2;
            } else {
                return player1;

            }


        }

    }
}