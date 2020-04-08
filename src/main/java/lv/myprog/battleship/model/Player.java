package lv.myprog.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Field ownField = new Field();
    private Field enemyField = new Field();
    private List<String> history = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getOwnField() {
        return ownField;
    }

    public Field getEnemyField() {
        return enemyField;
    }

    public boolean isReadyToPlay() {
        return ownField.isValid();
    }

    public void addToHistory(String message) {
        history.add(0, message);
    }

    public List<String> getHistory() {
        return history;
    }
}