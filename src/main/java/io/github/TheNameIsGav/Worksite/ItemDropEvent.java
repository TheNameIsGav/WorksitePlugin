package io.github.TheNameIsGav.Worksite;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class ItemDropEvent implements Listener {

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event){
        Player player = event.getPlayer();
        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());


    }
}
