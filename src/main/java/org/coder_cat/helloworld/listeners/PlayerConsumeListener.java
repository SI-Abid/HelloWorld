package org.coder_cat.helloworld.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.attribute.Attribute;

public class PlayerConsumeListener implements Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        // Check if the consumed item is the one you are interested in (e.g., a custom
        // item)
        if (event.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            // Get the current max health
            double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            // Increase max health by 1 (adjust as needed)
            double newMaxHealth = currentMaxHealth + 2;

            // Set the new max health for the player
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);

            // Send a message to the player
            player.sendMessage("Your max health has been increased to " + newMaxHealth);

        }
    }
}
