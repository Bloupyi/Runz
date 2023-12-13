package fr.bloup.runz;

import fr.bloup.runz.commands.CommandTabCompleter;
import fr.bloup.runz.commands.RunzCommand;
import fr.bloup.runz.movement.Fall;
import fr.bloup.runz.movement.JumpPad;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Runz extends JavaPlugin implements Listener {

    private static Runz instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("===========================");
        Bukkit.getLogger().info("Enable Runz 1.0");
        Bukkit.getLogger().info("===========================");

        this.getCommand("runz").setExecutor(new RunzCommand());
        this.getCommand("runz").setTabCompleter(new CommandTabCompleter());

        instance = this;

        getServer().getPluginManager().registerEvents(this, this);

        getServer().getPluginManager().registerEvents(new JumpPad(), this);
        getServer().getPluginManager().registerEvents(new Fall(), this);

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("===========================");
        Bukkit.getLogger().info("Disable Runz 1.0");
        Bukkit.getLogger().info("===========================");
    }

    public static Runz getInstance() {
        return instance;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
