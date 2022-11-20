package me.sillygoose.sillyhug;

import me.sillygoose.sillyhug.commands.HugCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SillyHug extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("hug").setExecutor(new HugCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
