package fr.virthia.utils.scoreboard;

import fr.virthia.utils.bukkit.plugin.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard {
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;
    private final ArrayList<ScoreboardPage> scoreboardPages = new ArrayList<>();
    private Long pageSwitchTickTime = 100L;
    private int currentPage = 0;

    PersonalScoreboard(Player player){
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "ScoreBoard");

        reloadData();
        objectiveSign.addReceiver(player);
        scoreboardPages.add(new ScoreboardPage());
        ScoreboardPage scoreboardPage = scoreboardPages.get(0);

        scoreboardPage.setLine(1,"§1");
        scoreboardPage.setLine(2,"§2");
        scoreboardPage.setLine(3,"§3");

        Bukkit.getScheduler().runTaskLater(Utils.INSTANCE, this::nextPage,pageSwitchTickTime);
    }

    public void reloadData(){}

    public void setLines(String ip){
        ScoreboardPage scoreboardPage = scoreboardPages.get(currentPage);
        objectiveSign.setDisplayName(scoreboardPage.getName());
        int maxLine = 0;
        HashMap<Integer, String> scoreboardLines = scoreboardPages.get(currentPage).getScoreboardLines();
        for(int line : scoreboardLines.keySet()){
            maxLine=Math.max(maxLine, line);
            objectiveSign.setLine(line, scoreboardLines.get(line));
        }
        objectiveSign.setLine(maxLine+1, ip);

        objectiveSign.updateLines();
    }

    public boolean setLine(int line, String text){
        if(scoreboardPages.get(0).getScoreboardLines().size()>=14)return false;
        scoreboardPages.get(0).setLine(line, text);
        return true;
    }

    public void setScoreboardName(String scoreboardName) {
        scoreboardPages.get(0).setName(scoreboardName);
    }

    public boolean setScoreboardLines(HashMap<Integer, String> scoreboardLines) {
        if(scoreboardLines.size()>14) return false;
        scoreboardPages.get(0).setScoreboardLines(scoreboardLines);
        return true;
    }

    public boolean setLine(int page, int line, String text){
        if(scoreboardPages.get(page).getScoreboardLines().size()>=14)return false;
        scoreboardPages.get(page).setLine(line, text);
        return true;
    }

    public void setScoreboardName(int page, String scoreboardName) {
        scoreboardPages.get(page).setName(scoreboardName);
    }

    public boolean setScoreboardLines(int page, HashMap<Integer, String> scoreboardLines) {
        if(scoreboardLines.size()>14) return false;
        scoreboardPages.get(page).setScoreboardLines(scoreboardLines);
        return true;
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }

    public void nextPage(){
        currentPage++;
        if(currentPage>=scoreboardPages.size()){
            currentPage=0;
        }
        Bukkit.getScheduler().runTaskLater(Utils.INSTANCE, this::nextPage,pageSwitchTickTime);
    }

    public void setPageSwitchTickTime(Long pageSwitchTickTime) {
        this.pageSwitchTickTime = pageSwitchTickTime;
    }
}