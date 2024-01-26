package org.coder_cat.helloworld;

import org.bukkit.plugin.java.JavaPlugin;
import org.coder_cat.helloworld.commands.RandomTeleport;
import org.coder_cat.helloworld.listeners.PlayerConsumeListener;
import org.coder_cat.helloworld.listeners.PlayerJoinListener;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public final class HelloWorld extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HelloWorld has been enabled");
        Config config = new Config(this);

        // Commands
        this.getCommand("rtp").setExecutor(new RandomTeleport());

        // Event Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConsumeListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("HelloWorld has been disabled");
    }

    @Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}
	
	@Override
	public String getBugTrackerURL() {
		return "https://github.com/SI-Abid/HelloWorld/issues";
	}
}
