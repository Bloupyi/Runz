package fr.bloup.runz.managers;

import fr.bloup.runz.Runz;
import fr.bloup.runz.movements.*;
import fr.bloup.runz.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    Runz main = Runz.instance();

    private List<Listener> listeners = new ArrayList<>();

    public void registerEvents(){

        this.listeners.add(new JumpPad());
        this.listeners.add(new Fall());
        this.listeners.add(new WallMovement());
        this.listeners.add(new WallJump());
        this.listeners.add(new LedgeGrab());
        this.listeners.add(new TopGrab());
        this.listeners.add(new GlassBreaker());

        this.listeners.add(new Player());

        new Stamina().runTaskTimer(main, 0L, 1L);
        this.listeners.forEach((listener -> {
            Bukkit.getPluginManager().registerEvents(listener, main);
        }));
    }
}