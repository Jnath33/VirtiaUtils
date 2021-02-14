package fr.virthia.utils.getTextWithTchat.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EndTask extends BukkitRunnable {
    private final Player player;
    private final boolean useTeam;
    private final String messageEnd, shortMessageEnd;

    public EndTask(Player player, boolean useTeam, String messageEnd, String shortMessageEnd) {
        this.player = player;
        this.useTeam = useTeam;
        this.messageEnd = messageEnd;
        this.shortMessageEnd = shortMessageEnd;
    }

    @Override
    public void run() {

    }
}
