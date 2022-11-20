package me.sillygoose.sillyhug.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class HugCommand implements CommandExecutor
{

    private final HashMap<UUID, Long> cooldown;

    // key = uuid of the player
    // long = the epoch time of when they ran the command
    public HugCommand()
    {
        this.cooldown = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player) // if sender is a player
        {
            Player player = (Player) sender;
            if (!this.cooldown.containsKey(player.getUniqueId())) // if its the dirst time theyve ran the command
            {
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis()); // put the uuid and the time that theyve just ran the command into the hashmap

                if (args.length == 0)
                {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSelf love is the best love. <3"));
                    hug(player);
                }
                else
                {
                    String playerName = args[0];
                    Player target = Bukkit.getServer().getPlayerExact(playerName);

                    if (target == null)
                    {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnable to hug this person."));
                    }
                    else
                    {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou hugged &4" + target.getDisplayName()));
                        hug(player);

                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "You've been hugged by &4" + player.getDisplayName()));
                        hug(target);
                    }
                }
            }
            else
            {
                // difference in ms
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());

                // 10 seconds (10000 ms)
                if (timeElapsed >= 10000)
                {
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                    if (args.length == 0)
                    {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSelf love is the best love. <3"));
                        hug(player);
                    }
                    else
                    {
                        String playerName = args[0];
                        Player target = Bukkit.getServer().getPlayerExact(playerName);

                        if (target == null)
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnable to hug this person."));
                        }
                        else
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou hugged &4" + target.getDisplayName()));
                            hug(player);

                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "You've been hugged by &4" + player.getDisplayName()));
                            hug(target);
                        }
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour arms are tired. You'll feel better in &4" + ((10000 - (System.currentTimeMillis() - cooldown.get(player.getUniqueId()))) / 1000) + " seconds."));
                    return true;
                }
            }
        }
        else
        {
            sender.sendMessage("You must be a player to run this command.");
            return true;
        }

        return true;
    }

    public static void hug(Player p)
    {
        p.spawnParticle(Particle.HEART, p.getLocation().add(0, 1, 0), 10, .5, .5, .5);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
    }
}
