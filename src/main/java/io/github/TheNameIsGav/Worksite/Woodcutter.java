package io.github.TheNameIsGav.Worksite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Woodcutter implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());

        Bukkit.broadcastMessage(String.valueOf(pl.balance));
        Bukkit.broadcastMessage(pl.jobs.toString());

        Bukkit.broadcastMessage("You broke a block cunt (in player handler)");
    }
}
