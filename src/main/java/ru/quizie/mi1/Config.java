package ru.quizie.mi1;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.quizie.mi1.utils.Colorizer;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private static FileConfiguration config;

    public static void load(FileConfiguration file) {
        config = file;

        parsePickaxe();
    }

    private static void parsePickaxe() {
        final ConfigurationSection section = config.getConfigurationSection("pickaxe");

        final ItemStack stack = new ItemStack(Material.valueOf(section.getString("material").toUpperCase()));
        final ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(Colorizer.use(section.getString("display-name")));

        final List<String> list = new ArrayList<>();
        section.getStringList("lore").forEach(str -> list.add(Colorizer.use(str)));
        meta.setLore(list);

        meta.getPersistentDataContainer().set(MI_1.PICKAXE_KEY, PersistentDataType.BYTE, (byte) 1);

        pickaxe = stack;
    }

    public static ItemStack pickaxe;
}
