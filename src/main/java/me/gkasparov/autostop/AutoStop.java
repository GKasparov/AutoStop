package me.gkasparov.autostop;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class AutoStop extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveConfig();

        Bukkit.getServer().getPluginManager().registerEvents(this, this);


    }

    @Override
    public void onDisable() {
        System.out.println("Disabling AutoStop");
    }



    @EventHandler
    public void onJoin(PlayerQuitEvent event) throws InterruptedException {
        while (Bukkit.getOnlinePlayers().size() == 0) {
            TimeUnit.MINUTES.sleep(getConfig().getInt("timeout"));
            Bukkit.shutdown();

        }
    }
}
