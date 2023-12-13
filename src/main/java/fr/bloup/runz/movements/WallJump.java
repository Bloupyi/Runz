package fr.bloup.runz.movements;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static fr.bloup.runz.movements.WallMovement.needToPush;
import static fr.bloup.runz.utils.Stamina.playerStamina;

public class WallJump implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                Location footLocation = player.getLocation();
                Vector direction = footLocation.getDirection().setY(0).normalize();

                boolean blockInFront = footLocation.clone().add(direction.multiply(0.45)).getBlock().getType().isSolid();

                if (blockInFront) {
                    double currentStamina = playerStamina.get(player);
                    if (currentStamina > 5) {
                        double newStamina = Math.min(100, currentStamina - 5);
                        playerStamina.put(player, newStamina);

                        needToPush.put(player, true);
                        Vector backward = direction.multiply(-1);
                        Vector velocity = player.getVelocity();
                        velocity.add(backward.multiply(1));
                        velocity.add(new Vector(0, 0.25, 0));
                        player.setVelocity(velocity);
                    }
                } else {
                    Vector toRight = new Vector(-direction.getZ(), 0, direction.getX()).normalize().multiply(0.45);
                    Vector toLeft = toRight.clone().multiply(-1);

                    double currentStamina = playerStamina.get(player);
                    if (currentStamina > 5) {
                        if (footLocation.clone().add(toRight).getBlock().getType().isSolid()) {
                            double newStamina = Math.min(100, currentStamina - 5);
                            playerStamina.put(player, newStamina);

                            Vector velocity = player.getVelocity();
                            velocity.add(toLeft.multiply(1));
                            velocity.add(new Vector(0, 0.5, 0));
                            player.setVelocity(velocity);
                        } else if (footLocation.clone().add(toLeft).getBlock().getType().isSolid()) {
                            double newStamina = Math.min(100, currentStamina - 5);
                            playerStamina.put(player, newStamina);

                            Vector velocity = player.getVelocity();
                            velocity.add(toRight.multiply(1));
                            velocity.add(new Vector(0, 0.5, 0));
                            player.setVelocity(velocity);
                        }
                    }
                }
            }
        }
    }
}
