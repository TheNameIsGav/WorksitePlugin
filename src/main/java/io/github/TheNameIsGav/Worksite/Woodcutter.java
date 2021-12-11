package io.github.TheNameIsGav.Worksite;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class Woodcutter {

    static int baseXP = 2;

    public static void addXP(PlayerInformation pl){
        int job = pl.jobs.indexOf("woodcutter");
        pl.xp.set(job, pl.xp.get(job) + (baseXP * pl.mult));
    }

    public static void HandleWoodcutterBlockBreak(PlayerInformation pl, Block block, Player player){
        Material b = block.getState().getType();

        if(b.equals(Material.ACACIA_LOG)
                || b.equals(Material.BIRCH_LOG)
                || b.equals(Material.DARK_OAK_LOG)
                || b.equals(Material.OAK_LOG)
                || b.equals(Material.JUNGLE_LOG)
                || b.equals(Material.SPRUCE_LOG)
                || b.equals(Material.WARPED_STEM)
                || b.equals(Material.CRIMSON_STEM)
        ) {
            player.sendMessage(pl.xp.toString());
            addXP(pl);//add XP loop
            player.sendMessage(pl.xp.toString());
        }

    }

    public static void HandleWoodcutterItemDrop(PlayerInformation pl, Block block, Material blockMat, Player player){
        if(blockMat.equals(Material.ACACIA_LOG)
                || blockMat.equals(Material.BIRCH_LOG)
                || blockMat.equals(Material.DARK_OAK_LOG)
                || blockMat.equals(Material.OAK_LOG)
                || blockMat.equals(Material.JUNGLE_LOG)
                || blockMat.equals(Material.SPRUCE_LOG)
                || blockMat.equals(Material.WARPED_STEM)
                || blockMat.equals(Material.CRIMSON_STEM)
        ) {
            double currXp = pl.xp.get(pl.jobs.indexOf("woodcutter"));
            Random rand = new Random();
            if(currXp >= 0 && currXp < 5000){ //Level 10 reward
                if(rand.nextDouble(1) < 1){
                    player.getWorld().dropItem(block.getLocation(), new ItemStack(blockMat));
                }
            }
        }
    }


}
