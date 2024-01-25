package org.coder_cat.helloworld;

import org.bukkit.plugin.java.JavaPlugin;
import org.coder_cat.helloworld.commands.RandomTeleport;
import org.coder_cat.helloworld.listeners.PlayerConsumeListener;
import org.coder_cat.helloworld.listeners.PlayerJoinListener;

public final class HelloWorld extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HelloWorld has been enabled");

        // Commands
        this.getCommand("kit").setExecutor(new RandomTeleport());

        // Event Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConsumeListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("HelloWorld has been disabled");
    }
}
