package xyz.mglolenstine.easycrops;

import org.bukkit.plugin.java.JavaPlugin;

public final class Easycrops extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
