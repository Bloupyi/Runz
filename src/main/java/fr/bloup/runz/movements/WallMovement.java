package fr.bloup.runz.movements;

import fr.bloup.runz.Runz;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static fr.bloup.runz.utils.Stamina.playerStamina;

public class WallMovement implements Listener {
    Runz main = Runz.instance();

    public static HashMap<Player, Boolean> onSideWall = new HashMap<>();
    public static HashMap<Player, Boolean> needToPush = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            if (!player.isOnGround()) {
                World world = player.getWorld();

                Location footLocation = player.getLocation();
                Vector direction = footLocation.getDirection().setY(0).normalize();
                Vector toRight = new Vector(-direction.getZ(), 0, direction.getX()).normalize().multiply(0.45);
                Vector toLeft = toRight.clone().multiply(-1);

                boolean blockInFront = footLocation.clone().add(direction.multiply(0.45)).getBlock().getType().isSolid();

                if (blockInFront) {
                    Boolean push = needToPush.get(player);

                    double currentStamina = playerStamina.get(player);
                    if (currentStamina > 4) {
                        double newStamina = Math.min(100, currentStamina - 2.5);
                        playerStamina.put(player, newStamina);

                        if (!push) {
                            Vector velocity = new Vector(0, 0, 0);
                            velocity.setY(velocity.getY() + 0.25);
                            player.setVelocity(velocity);

                            world.playSound(player.getLocation(), Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
                        } else {
                            Bukkit.getScheduler().runTaskLater(main, () -> {
                                needToPush.put(player, false);
                            }, 2);
                        }
                    }

                } else if (footLocation.clone().add(toRight).getBlock().getType().isSolid() || footLocation.clone().add(toLeft).getBlock().getType().isSolid()) {
                    onSideWall.put(player, true);

                    double currentStamina = playerStamina.get(player);
                    if (currentStamina > 0.3) {
                        double newStamina = Math.min(100, currentStamina - 1);
                        playerStamina.put(player, newStamina);

                        float yaw = player.getLocation().getYaw();
                        double rad = Math.toRadians(yaw);
                        double x = -Math.sin(rad);
                        double z = Math.cos(rad);
                        Vector forward = new Vector(x, 0, z);
                        forward.multiply(0.06);

                        Vector velocity = player.getVelocity();
                        velocity.setY(velocity.getY() + 0.03);
                        velocity.add(forward);
                        player.setVelocity(velocity);

                        world.playSound(player.getLocation(), Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
                    }

                } else {
                    Bukkit.getScheduler().runTaskLater(main, () -> {
                        needToPush.put(player, false);
                        onSideWall.put(player, false);
                    }, 2);
                }
            } else {
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    needToPush.put(player, false);
                    onSideWall.put(player, false);
                }, 2);
            }
        }
    }
}
