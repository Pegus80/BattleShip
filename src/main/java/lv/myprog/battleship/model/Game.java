package lv.myprog.battleship.model;


public class Game {
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn = true;
    private Player winner;
    private Player loser;
    private boolean gameOver;

    public synchronized void join(Player player) {
        if (player1 == null) {
            player1 = player;
        } else {
            player2 = player;
        }
    }

    public synchronized boolean isReady() {
        return player1 != null && player2 != null;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public synchronized Player getActivePlayer() {
        if (isPlayer1Turn) {
            return player1;
        } else {
            return player2;
        }
    }

    public synchronized Player getInactivePlayer() {
        if (isPlayer1Turn) {
            return player2;
        } else {
            return player1;
        }
    }

    public void clearGame (){
       winner=null;
       loser=null;
       player1.getOwnField().clear();
       player2.getOwnField().clear();
       player1.clearHistory();
       player2.clearHistory();
       player1.clearFields();
       player2.clearFields();
       isPlayer1Turn=!isPlayer1Turn; //will start another player

    }


    public synchronized boolean isMyTurn(Player  player) {
        return getActivePlayer() == player;
    }

    public synchronized void fire(String addr) {
        var opponent = getInactivePlayer();
        var player = getActivePlayer();

        String result = "MISS";
        if (opponent.getOwnField().getState(addr) == CellState.SHIP) {
            opponent.getOwnField().setState(addr, CellState.HIT);
            player.getEnemyField().setState(addr, CellState.HIT);
            result = "HIT";
            if (!opponent.getOwnField().hasShips()) {
                winner = player;
                loser = getOpponent(player);
            }
        } else if (opponent.getOwnField().getState(addr) == CellState.EMPTY) {
            opponent.getOwnField().setState(addr, CellState.MISS);
            player.getEnemyField().setState(addr, CellState.MISS);
            isPlayer1Turn = !isPlayer1Turn;
        } else {
            isPlayer1Turn = !isPlayer1Turn;
        }
        player.addToHistory(String.format("You fired to %s: %s", addr, result));
        opponent.addToHistory(String.format("%s fired to %s: %s", player.getName(), addr, result));
    }


    public boolean isFinished() {
        return winner != null;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }


    public synchronized Player getOpponent(Player player) {
        if (player1 == null || player2 == null) {
            return null;
        } else {
            if (player.equals(player1)) {
                return player2;
            } else {
                return player1;

            }


        }

    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    public boolean isGameOver() {
        return gameOver;
    }

}