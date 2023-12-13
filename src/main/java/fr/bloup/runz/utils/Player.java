package fr.bloup.runz.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Player implements Listener {
    @EventHandler public void onFoodLevelChange(FoodLevelChangeEvent event) {event.setCancelled(true);}

    @EventHandler public void onPlayerJoin(PlayerJoinEvent event) {event.getPlayer().setMaxHealth(6.0);}

    @EventHandler
    public void onHealthRegen(EntityRegainHealthEvent event) {
        if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setDamage(0);
    }

    /*
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            return;
        }
        event.setCancelled(true);
    }
    */
}
