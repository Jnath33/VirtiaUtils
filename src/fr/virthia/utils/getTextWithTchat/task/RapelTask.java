package fr.virthia.utils.getTextWithTchat.task;

import fr.virthia.utils.bukkit.plugin.Utils;
import fr.virthia.utils.getTextWithTchat.TchatGetter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RapelTask extends BukkitRunnable {
    private final TchatGetter tchatGetter;
    private final Player player;
    private final boolean useTeam;
    private final String messageRapel, shortMessageRapel;
    private final EndTask endTask;

    public RapelTask(TchatGetter tchatGetter, Player player, boolean useTeam, String messageRapel, String shortMessageRapel, EndTask endTask) {
        this.tchatGetter = tchatGetter;
        this.player = player;
        this.useTeam = useTeam;
        this.messageRapel = messageRapel;
        this.shortMessageRapel = shortMessageRapel;
        this.endTask = endTask;
    }

    @Override
    public void run() {
        player.sendMessage(messageRapel);
        if (useTeam)
            ;
        tchatGetter.setCurrentTask(endTask.runTaskLater(Utils.INSTANCE, 60));
    }
}
