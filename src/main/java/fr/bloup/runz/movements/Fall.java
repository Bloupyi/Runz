package fr.bloup.runz.movements;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

import static fr.bloup.runz.movements.WallMovement.onSideWall;

public class Fall implements Listener {

    private final HashMap<Player, Long> sneakStartTimes = new HashMap<>();

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (event.isSneaking()) {
            sneakStartTimes.put(player, System.currentTimeMillis());
        } else {
            sneakStartTimes.remove(player);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player player = (Player) event.getEntity();

            event.setCancelled(true);

            Long sneakStartTime = sneakStartTimes.get(player);
            Boolean onWall = onSideWall.get(player);
            if (!onWall && (sneakStartTime == null || System.currentTimeMillis() - sneakStartTime > 150)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
            } else if (!onWall){
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1));
            }
        }
    }
}
