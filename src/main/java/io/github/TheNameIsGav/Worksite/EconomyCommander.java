package io.github.TheNameIsGav.Worksite;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;


public class EconomyCommander implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if(sender instanceof Player){
            player = (Player) sender;
        } else { return false; }


        label = label.toLowerCase();
        if(label.equals("pay")){
            if(args.length != 2){
                return false;
            }

            String target = "";
            for(UUID p : Worksite.onlinePlayers.keySet()){
                if(Bukkit.getPlayer(p).getDisplayName().equals(args[0])){
                    target = args[0];
                }
            }

            if(target.equals("")){
                return false;
            }

            double val = 0.0;
            try{
                val = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                return false;
            }

            if(val <= 0){
                return false;
            }

            Worksite.onlinePlayers.get(player.getUniqueId()).balance -= val;
            Worksite.onlinePlayers.get(UUID.fromString(target)).balance += val;

            return true;
        }

        if(label.equals("payoffline")){
            if(args.length != 2){
                return false;
            }

            double val = 0.0;
            try{
                val = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                return false;
            }

            if(val <= 0 && (Worksite.onlinePlayers.get(player.getUniqueId()).balance - val >= 0)){
                return false;
            }

            if(Worksite.offlinePayments.get(args[1]) == null){
                Worksite.offlinePayments.put(args[1], val);
            } else {
                Worksite.offlinePayments.put(args[1], Worksite.offlinePayments.get(args[1]) + val);
            }
            Worksite.onlinePlayers.get(player.getUniqueId()).balance -= val;
        }

        if(label.equals("balance")){
            player.sendMessage("$" + Worksite.onlinePlayers.get(player.getUniqueId()).balance);
            return true;
        }


        return false;
    }
}
