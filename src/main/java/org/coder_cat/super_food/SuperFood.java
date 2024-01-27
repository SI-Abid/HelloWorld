package org.coder_cat.super_food;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.coder_cat.super_food.commands.RandomTeleport;
import org.coder_cat.super_food.items.HeartCake;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public final class SuperFood extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {

        getLogger().info("Slimefun addon by coder_cat");

        getCommand("rtp").setExecutor(new RandomTeleport());

        NamespacedKey categoryId = new NamespacedKey(this, "super_food");
        CustomItemStack categoryItem = new CustomItemStack(Material.CAKE, "&4Super Food");

        ItemGroup itemGroup = new ItemGroup(categoryId, categoryItem);

        SlimefunItemStack itemStack = new SlimefunItemStack("HEART_CAKE", Material.CAKE, "&aHeart Cake",
                "&7Gives you extra heart");
        ItemStack[] recipe = {
                new ItemStack(Material.EMERALD), new ItemStack(Material.CAKE), new ItemStack(Material.EMERALD),
                new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.TOTEM_OF_UNDYING),
                new ItemStack(Material.DIAMOND_BLOCK),
                new ItemStack(Material.EMERALD), new ItemStack(Material.CAKE), new ItemStack(Material.EMERALD)
        };

        HeartCake cake = new HeartCake(itemGroup, itemStack, RecipeType.MAGIC_WORKBENCH, recipe);
        cake.register(this);

        NamespacedKey researchKey = new NamespacedKey(this, "heart_cake");
        Research research = new Research(researchKey, 123, "Very Expensive way to get Immortality", 10);
        research.addItems(cake);

        research.register();
    }

    @Override
    public void onDisable() {
        getLogger().info("Super Food is disabled");
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
