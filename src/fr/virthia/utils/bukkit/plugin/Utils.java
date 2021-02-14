package fr.virthia.utils.bukkit.plugin;

import fr.virthia.utils.getTextWithTchat.TchatGetterListeners;
import fr.virthia.utils.scoreboard.ScoreboardListeners;
import fr.virthia.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Utils extends JavaPlugin {

    public static Utils INSTANCE;

    private ScoreboardManager scoreboardManager;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;


    public void onEnable() {
        INSTANCE = this;
        System.out.println("Plugin utils & API de virthia activer");

        scoreboardManager = new ScoreboardManager();

        executorMonoThread = Executors.newScheduledThreadPool(1);
        scheduledExecutorService = Executors.newScheduledThreadPool(16);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ScoreboardListeners(), this);
        pm.registerEvents(new TchatGetterListeners(), this);
    }

    public void onDisable() {
        System.out.println("Plugin utils & API de virthia désactiver");
        getScoreboardManager().onDisable();
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
