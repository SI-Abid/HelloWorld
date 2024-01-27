package me.coder_cat.super_food.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTeleport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location newLoc = getRandomLocation(player.getLocation());
            return player.teleport(newLoc);
        }
        sender.sendMessage("This is a player only command");
        return true;
    }

    Location getRandomLocation(Location prev) {
        int randomizer = (int) (Math.random() * 10000) % 10000 - 5000;
        double posX = prev.getX() + randomizer;
        double posZ = prev.getZ() + randomizer;

        int maxY = prev.getWorld().getMaxHeight();
        int minY = 1;
        double posY = Math.max(minY, Math.min(maxY, prev.getY()));

        return prev.set(posX, posY, posZ);
    }
}
