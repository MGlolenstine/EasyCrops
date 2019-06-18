package xyz.mglolenstine.easycrops;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
import org.bukkit.material.NetherWarts;

import java.util.Random;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    void onRightClickCrops(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CROPS
                || e.getClickedBlock().getType() == Material.POTATO
                || e.getClickedBlock().getType() == Material.CARROT
                || e.getClickedBlock().getType() == Material.BEETROOT_BLOCK) {
            Crops crop = (Crops) e.getClickedBlock().getState().getData();
            if (crop.getState().equals(CropState.RIPE)) {
                e.getClickedBlock().setType(e.getClickedBlock().getType());
                crop.setState(CropState.SEEDED);
                int mult = 1;
                ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
                if (is.hasItemMeta()) {
                    ItemMeta im = is.getItemMeta();
                    if (im.getEnchants().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                        mult = im.getEnchants().get(Enchantment.LOOT_BONUS_BLOCKS);
                    }
                }
                if (e.getClickedBlock().getType() == Material.CROPS) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.WHEAT, mult));
                } else if (e.getClickedBlock().getType() == Material.POTATO) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.POTATO_ITEM, mult));
                } else if (e.getClickedBlock().getType() == Material.CARROT) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.CARROT_ITEM, mult));
                } else if (e.getClickedBlock().getType() == Material.BEETROOT_BLOCK) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.BEETROOT, mult));
                } else if (e.getClickedBlock().getType() == Material.NETHER_WARTS) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.NETHER_WARTS, mult));
                }
            }
        } else if (e.getClickedBlock().getType() == Material.NETHER_WARTS) {
            NetherWarts data = (NetherWarts) e.getClickedBlock().getState().getData();
            if (data.getState() == NetherWartsState.RIPE) {
                e.getClickedBlock().setType(e.getClickedBlock().getType());
                int mult = 1;
                ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
                if (is.hasItemMeta()) {
                    ItemMeta im = is.getItemMeta();
                    if (im.getEnchants().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                        mult = im.getEnchants().get(Enchantment.LOOT_BONUS_BLOCKS);
                    }
                }
                e.getPlayer().getInventory().addItem(new ItemStack(Material.NETHER_STALK, mult));
            }
        } else {
            if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.getPlayer().sendMessage(e.getClickedBlock().getType().name());
            }
        }
    }

    int random(int lower, int upper) {
        Random r = new Random();
        return r.nextInt(upper - lower) + lower;
    }
}
