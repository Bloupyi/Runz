package fr.bloup.runz.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Stamina extends BukkitRunnable {
    public static HashMap<Player, Double> playerStamina = new HashMap<>();

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            double checkCurrentStamina = playerStamina.getOrDefault(player, 75.0);
            if (checkCurrentStamina < 0) {
                playerStamina.put(player, 0.0);
            }
            double currentStamina = playerStamina.getOrDefault(player, 75.0);
            if (currentStamina < 100) {
                double newStamina = Math.min(100, currentStamina + 0.3);
                playerStamina.put(player, newStamina);
                player.setExp((float) (newStamina / 100));
            }

        }
    }
}
