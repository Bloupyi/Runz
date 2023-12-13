package fr.bloup.runz.movements;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class GlassBreaker implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            if (player.isSprinting()) {
                World world = player.getWorld();

                float yaw = player.getLocation().getYaw();
                double rad = Math.toRadians(yaw);
                double x = -Math.sin(rad);
                double z = Math.cos(rad);
                Vector forward = new Vector(x, 0, z);

                Block eyeBlock = player.getEyeLocation().add(forward).getBlock();
                Location eyeBlockLoc = player.getEyeLocation().add(forward);
                if (eyeBlock.getType() == Material.GLASS) {
                    eyeBlock.breakNaturally();
                    world.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
                    world.spawnParticle(Particle.BLOCK_CRACK, eyeBlockLoc, 100, 0.5, 0.1, 0.5, 0.1, Material.GLASS.createBlockData());
                }

                Block feetBlock = player.getLocation().add(forward).getBlock();
                Location feetBlockLoc = player.getLocation().add(forward);
                if (feetBlock.getType() == Material.GLASS) {
                    feetBlock.breakNaturally();
                    world.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
                    world.spawnParticle(Particle.BLOCK_CRACK, feetBlockLoc.add(0, 0.5, 0), 100, 0.5, 0.1, 0.5, 0.1, Material.GLASS.createBlockData());
                }
            }
        }
    }
}
