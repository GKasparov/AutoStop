package me.gkasparov.autostop;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoStop extends JavaPlugin implements Listener {

    private long lastLeaveTime = -1;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        lastLeaveTime = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveConfig();

        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        long timeoutMillis = getConfig().getInt("timout") * 60000L; // Converts config minutes to required ms

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (lastLeaveTime > 0 && System.currentTimeMillis() > lastLeaveTime + timeoutMillis) {
                getServer().shutdown();
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling AutoStop");
    }
}

