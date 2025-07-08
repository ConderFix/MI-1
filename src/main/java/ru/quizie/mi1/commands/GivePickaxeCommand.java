package ru.quizie.mi1.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import ru.quizie.mi1.Config;

public class GivePickaxeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission("givepickaxe.use"))) return true;

        if (args.length <= 1) {
            sender.sendMessage("/givepickaxe <name> - Issue a pickaxe");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        safeGive(target, Config.pickaxe);
        sender.sendMessage("ok");

        return true;
    }

    private void safeGive(Player player, ItemStack item) {
        player.getInventory().addItem(item).forEach((i, is) -> player.getWorld().dropItem(player.getLocation(), is));
    }
}
