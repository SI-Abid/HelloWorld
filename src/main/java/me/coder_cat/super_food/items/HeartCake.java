package me.coder_cat.super_food.items;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

public class HeartCake extends SlimefunItem {

    public HeartCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler handler = this::onItemUseRightClick;
        addItemHandler(handler);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();

        event.cancel(); // Prevent the cake from being placed down
        player.getInventory().setItemInMainHand(null);
        increaseMaxHealth(player);
        spawnRedFirework(player.getLocation());

    }

    private void increaseMaxHealth(Player player) {
        double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double newMaxHealth = currentMaxHealth + 2;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
        player.setHealth(newMaxHealth);
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
