package me.coder_cat.super_food.items;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;

public class HeartCake extends SlimefunItem {

    public HeartCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        BlockUseHandler handler = this::onBlockUseRightClick;
        addItemHandler(handler);
    }

    private void onBlockUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItem();
    
        // Check if the player is holding the HeartCake item
        if (itemInHand != null && itemInHand.isSimilar(getItem())) {
            player.setItemInHand(null);
            increaseMaxHealth(player);
            spawnRedFirework(player.getLocation());
            event.cancel(); // Prevent the cake from being placed down
        }
    }

    private void increaseMaxHealth(Player player) {
        // Get the current max health
        double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        // Increase max health by 2
        double newMaxHealth = currentMaxHealth + 2;

        // Set the new max health for the player
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);

        // If you want to instantly update the player's current health to the new max health
        player.setHealth(newMaxHealth);

        // Send a message to the player
        player.sendMessage("Your max health has been increased to " + newMaxHealth);
    }

    private void spawnRedFirework(Location location) {
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        
        // Set firework properties
        FireworkEffect effect = FireworkEffect.builder()
                .withColor(Color.RED)
                .with(FireworkEffect.Type.BALL)
                .build();
        
        meta.addEffect(effect);
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }
}
