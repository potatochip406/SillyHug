package me.sillygoose.sillyhug.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSelf love is the best love. <3"));
                player.spawnParticle(Particle.HEART, player.getLocation().add(0, 1, 0), 10, .5, .5, .5);
            } else {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);

                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnable to hug this person."));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou hugged &4" + target.getDisplayName()));
                    player.spawnParticle(Particle.HEART, player.getLocation().add(0, 1, 0), 10, .5, .5, .5);

                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "You've been hugged by &4" + player.getDisplayName()));
                    target.spawnParticle(Particle.HEART, target.getLocation().add(0, 1, 0), 10, .5, .5, .5);
                }
             }

        } else {
            sender.sendMessage("You must be a player to run this command.");
            return false;
        }

        return true;
    }
}
