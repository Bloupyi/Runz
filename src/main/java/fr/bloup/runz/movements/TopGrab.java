package fr.bloup.runz.movements;

import fr.bloup.runz.Runz;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import static fr.bloup.runz.utils.Stamina.playerStamina;

public class TopGrab implements Listener {
    Runz main = Runz.instance();

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {

            Location eyeLocation = player.getEyeLocation();

            Block blockAbove = eyeLocation.getBlock().getRelative(BlockFace.UP);

            Vector direction = eyeLocation.getDirection().normalize().multiply(0.5);

            Block blockInFrontAndAbove = eyeLocation.add(direction).getBlock().getRelative(BlockFace.UP);

            if (player.isSneaking()) {
                if (blockAbove.getType() != Material.AIR || blockInFrontAndAbove.getType() != Material.AIR) {
                    double currentStamina = playerStamina.get(player);
                    if (currentStamina > 15) {
                        double newStamina = Math.min(100, currentStamina - 15);
                        playerStamina.put(player, newStamina);

                        float yaw = player.getLocation().getYaw();
                        double rad = Math.toRadians(yaw);
                        double x = -Math.sin(rad);
                        double z = Math.cos(rad);
                        Vector forward = new Vector(x, 0, z);
                        forward.multiply(0.6);

                        Vector velocity = player.getVelocity();
                        velocity.setY(velocity.getY() + -0.15);
                        velocity.add(forward);
                        player.setVelocity(velocity);
                        Bukkit.getScheduler().runTaskLater(main, () -> {
                            Vector velocity2 = new Vector(0,0,0);
                            velocity2.setY(velocity2.getY() + 0.5);
                            velocity2.add(forward);
                            player.setVelocity(velocity2);
                        }, 3);
                    }
                }
            }
        }
    }
}
