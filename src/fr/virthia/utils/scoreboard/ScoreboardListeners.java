package fr.virthia.utils.scoreboard;

import fr.virthia.utils.bukkit.plugin.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Utils.INSTANCE.getScoreboardManager().onLogin(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Utils.INSTANCE.getScoreboardManager().onLogout(event.getPlayer());
    }
}
