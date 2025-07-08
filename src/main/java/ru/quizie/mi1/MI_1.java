package ru.quizie.mi1;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import ru.quizie.mi1.commands.GivePickaxeCommand;
import ru.quizie.mi1.listeners.PickaxeUseListener;

public final class MI_1 extends JavaPlugin {

    @Override
    public void onEnable() {
        super.saveDefaultConfig();
        Config.load(super.getConfig());

        super.getServer().getPluginManager().registerEvents(new PickaxeUseListener(), this);

        super.getCommand("givepickaxe").setExecutor(new GivePickaxeCommand());
    }

    public static NamespacedKey PICKAXE_KEY = new NamespacedKey("MI-1", "pickaxe");
}
