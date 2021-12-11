package io.github.TheNameIsGav.Worksite;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakerEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());

        if(pl.jobs.contains("woodcutter")){ //Woodcutter Block Break Event
            Woodcutter.HandleWoodCutterBlockBreak(pl, event.getBlock());
        }



    }

}
