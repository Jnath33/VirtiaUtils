package fr.virthia.utils.scoreboard;

import java.util.HashMap;

public class ScoreboardPage {
    private HashMap<Integer, String> scoreboardLines = new HashMap<>();
    private String name = "§eVirthia";

    public HashMap<Integer, String> getScoreboardLines() {
        return scoreboardLines;
    }

    public void setScoreboardLines(HashMap<Integer, String> scoreboardLines) {
        this.scoreboardLines = scoreboardLines;
    }

    public void setLine(int line, String text){
        scoreboardLines.put(line, text);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
