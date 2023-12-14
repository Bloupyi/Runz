package fr.bloup.runz;

import fr.bloup.runz.managers.CommandManager;
import fr.bloup.runz.managers.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Runz extends JavaPlugin implements Listener {

    private static Runz INSTANCE;

    @Override
    public void onEnable() {

        INSTANCE = this;

        Bukkit.getLogger().info("Enable Runz 1.0");

        CommandManager commands = new CommandManager();
        commands.setCommand();

        ListenerManager listeners = new ListenerManager();
        listeners.registerEvents();

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Disable Runz 1.0");

    }

    public static Runz instance() {
        return INSTANCE;
    }

}
