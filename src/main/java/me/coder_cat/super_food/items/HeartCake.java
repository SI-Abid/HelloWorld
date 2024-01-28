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
        // Calling event.cancel() in here would prevent the cake
        // from being placed down.
        increaseMaxHealth(event.getPlayer());
    }

    public void increaseMaxHealth(Player player) {
        // Get the current max health
        double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        // Increase max health by 1 (adjust as needed)
        double newMaxHealth = currentMaxHealth + 2;

        // Set the new max health for the player
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);

        // If you want to instantly update the player's current health to the new max
        // health
        player.setHealth(newMaxHealth);

        // Send a message to the player
        player.sendMessage("Your max health has been increased to " + newMaxHealth);
    }
}
