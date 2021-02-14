package fr.virthia.utils.getTextWithTchat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TchatGetterListeners implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event){
        event.setCancelled(TchatGetter.playerMessage(event.getPlayer(), event.getMessage()));
    }
}
