package fr.bloup.runz.movements;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPad implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            Material blockMaterial = player.getLocation().subtract(0, 1, 0).getBlock().getType();

            if (blockMaterial == Material.SLIME_BLOCK) {
                float yaw = player.getLocation().getYaw();
                double rad = Math.toRadians(yaw);
                double x = -Math.sin(rad);
                double z = Math.cos(rad);
                Vector forward = new Vector(x, 0, z);
                forward.multiply(0.25);

                Vector velocity = player.getVelocity();
                velocity.setY(1.4);
                velocity.add(forward);
                player.setVelocity(velocity);

            }
        }
    }
}
