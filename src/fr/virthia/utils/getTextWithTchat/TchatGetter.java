package fr.virthia.utils.getTextWithTchat;

import fr.virthia.utils.bukkit.plugin.Utils;
import fr.virthia.utils.getTextWithTchat.task.EndTask;
import fr.virthia.utils.getTextWithTchat.task.RapelTask;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.function.Consumer;

public class TchatGetter {
    private final int time;
    private final String messageAccepted, messageStart, messageEnd, messageRapel, shortMessageStart, shortMessageEnd, shortMessageRapel;
    private final boolean useTitle;
    private BukkitTask currentTask;
    private final Player player;
    private Consumer<String> reponse;
    private final static HashMap<Player, TchatGetter> playerTchatGetter = new HashMap<>();

    public TchatGetter(int time, Player player, String messageAccepted, String messageStart, String messageEnd, String messageRapel, String shortMessageStart, String shortMessageEnd, String shortMessageRapel, boolean useTitle) {
        if(time>5)
            this.time = time;
        else
            this.time = 5;
        this.player=player;
        this.messageAccepted = messageAccepted;
        this.messageStart = messageStart;
        this.messageEnd = messageEnd;
        this.messageRapel = messageRapel;
        this.shortMessageStart = shortMessageStart;
        this.shortMessageEnd = shortMessageEnd;
        this.shortMessageRapel = shortMessageRapel;
        this.useTitle = useTitle;
    }

    public boolean startTchatGetter(Consumer<String> onReponse){
        RapelTask rapelTask = new RapelTask(
                this, player, useTitle,
                messageRapel, shortMessageRapel,

                new EndTask(
                        player, useTitle,
                        messageEnd, shortMessageEnd));
        player.sendMessage(messageStart);
        if (useTitle)
            ;
        playerTchatGetter.put(player, this);
        rapelTask.runTaskLater(Utils.INSTANCE, (time - 3) * 20L);
        return true;
    }

    public void setCurrentTask(BukkitTask currentTask) {
        this.currentTask = currentTask;
    }

    public void playerReponse(String reponseStr){
        currentTask.cancel();
        reponse.accept(reponseStr);
    }

    public static boolean playerMessage(Player player, String message){
        if (playerTchatGetter.containsKey(player)){
            TchatGetter tcGet = playerTchatGetter.get(player);
            tcGet.playerReponse(message);
            player.sendMessage(tcGet.messageAccepted);
            playerTchatGetter.remove(player);
            return true;
        }
        return false;
    }
}
