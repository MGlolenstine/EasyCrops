package xyz.mglolenstine.easycrops;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;

import java.util.Random;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    void onRightClickCrops(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CROPS) {
            Crops crop = (Crops) e.getClickedBlock().getState();
            if (crop.getState().equals(CropState.RIPE)) {
                crop.setState(CropState.SEEDED);
                int mult = 1;
                ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
                if (is.hasItemMeta()) {
                    ItemMeta im = is.getItemMeta();
                    if (im.getEnchants().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                        mult = im.getEnchants().get(Enchantment.LOOT_BONUS_BLOCKS);
                    }
                }
                if (crop.getItemType() == Material.SEEDS) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.WHEAT, mult));
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.SEEDS, random(0, mult)));
                } else {
                    e.getPlayer().getInventory().addItem(new ItemStack(crop.getItemType(), mult));
                }
            }
        }
    }

    int random(int lower, int upper) {
        Random r = new Random();
        return r.nextInt(upper - lower) + lower;
    }
}
