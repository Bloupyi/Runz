package fr.bloup.runz.movements;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static fr.bloup.runz.utils.Stamina.playerStamina;

public class LedgeGrab implements Listener {

    //Not finished
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block clickedBlock = event.getClickedBlock();

                if (clickedBlock != null) {
                    Block blockInFront = player.getTargetBlockExact(2);
                    Location footLocation = player.getLocation();

                    Block blockBelow = footLocation.getBlock().getRelative(BlockFace.DOWN);

                    if (blockInFront != null && blockInFront.equals(clickedBlock) && !blockInFront.equals(blockBelow)) {
                        Block blockAbove = clickedBlock.getRelative(BlockFace.UP);
                        Block blockTwoAbove = blockAbove.getRelative(BlockFace.UP);

                        if (blockAbove.getType() == Material.AIR && blockTwoAbove.getType() == Material.AIR) {
                            double currentStamina = playerStamina.get(player);
                            if (currentStamina > 10) {
                                double newStamina = Math.min(100, currentStamina - 10);
                                playerStamina.put(player, newStamina);

                                float yaw = player.getLocation().getYaw();

                                Location location = clickedBlock.getLocation();

                                location.setY(clickedBlock.getY() + 1);
                                location.setX(location.getX() + 0.5);
                                location.setZ(location.getZ() + 0.5);
                                location.setYaw(yaw);

                                player.teleport(location);

                                double rad = Math.toRadians(yaw);
                                double x = -Math.sin(rad);
                                double z = Math.cos(rad);
                                Vector velocity = new Vector(x, 0, z);
                                velocity.multiply(0.5);
                                velocity.setY(0.25);

                                player.setVelocity(velocity);
                            }
                        }
                    }
                }
            }
        }
    }

}
