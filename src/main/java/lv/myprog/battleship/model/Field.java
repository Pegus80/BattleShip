package lv.myprog.battleship.model;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private final Map<String, CellState> cells = new HashMap<>();

    public CellState getState(String addr) {
        return cells.getOrDefault(addr, CellState.EMPTY);
    }

    public void setState(String addr, CellState state) {
        cells.put(addr, state);
    }

    public void clear() {
        cells.clear();
    }

    public boolean isValid() {
        return cells.values().stream()
                .filter(s -> s == CellState.SHIP)
                .count() > 0 ;
    }

    public boolean hasShips() {
        return cells.containsValue(CellState.SHIP);
    }
}