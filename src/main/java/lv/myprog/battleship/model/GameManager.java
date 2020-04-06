package lv.myprog.battleship.model;

public class GameManager {
    private Game incomplete;

    public synchronized Game getIncompleteGameAndJoin(Player player) {
        if (incomplete == null) {
            incomplete = new Game();
        }
        incomplete.join(player);
        var tmp = incomplete;
        if (incomplete.isReady()) {
            incomplete = null;
        }
        return tmp;
    }
}