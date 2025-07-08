package ru.quizie.mi1.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import ru.quizie.mi1.MI_1;

public class PickaxeUseListener implements Listener {

    @EventHandler
    private void on(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        final ItemStack stack = event.getItem();
        if (stack == null || stack.getType().isAir() || stack.getItemMeta() == null || stack.getItemMeta().getPersistentDataContainer().has(MI_1.PICKAXE_KEY, PersistentDataType.BYTE)) return;

        final Block block = event.getClickedBlock();
        if (block.getType().isAir() || block.getType() != Material.BEDROCK) return;
        final Location location = block.getLocation().toCenterLocation();

        event.setCancelled(true);
        block.setType(Material.AIR);

        final Player player = event.getPlayer();
        player.spawnParticle(Particle.EXPLOSION_NORMAL, location, 1);
        player.playSound(location, Sound.ENTITY_VILLAGER_YES, 1, 1);
    }

}
