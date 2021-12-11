package io.github.TheNameIsGav.Worksite;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobsCommander implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player;
        if(sender instanceof Player){
            player = (Player) sender;
        } else { return false; }

        label = label.toLowerCase();

        if(label.equals("job")){
            return assignJob(player, args);
        }

        if(label.equals("resign")){
            return resignJob(player, args);
        }

        if(label.equals("jobs")){
            return listJobs(player);
        }

        if(label.equals("joblist")){
            player.sendMessage("""
                    Brewer
                    Builder
                    Crafter
                    Enchanter
                    Explorer
                    Farmer
                    Fisher
                    Hunter
                    Miner
                    Weaponsmith
                    Woodcutter""");
        }

        if(label.equals("worksitehelp")){
            player.sendMessage("Commands: '/job <job title>' to register for a job, '/resign <job title>' to leave a job , /worksitehelp , '/joblist' to list all jobs, '/jobs' to list your jobs");
        }

        return true;
    }

    boolean listJobs(Player player){
        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());
        player.sendMessage("Current jobs are " + pl.jobs.toString());
        return true;
    }

    boolean resignJob(Player player, String[] args){
        if(args.length != 1){
            player.sendMessage("Invalid arguments for command job. Should be /resign <job title>");
            return false;
        }

        String job = args[0].toLowerCase();
        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());
        if(pl.jobs.contains(job)){
            pl.xp.remove(pl.jobs.indexOf(job));
            pl.jobs.remove(job);
            return true;
        }

        player.sendMessage("It appears as though you do not have that job. Please use /jobs to list your current jobs");
        return false;
    }

    boolean assignJob(Player player, String[] args){
        if(args.length != 1){
            player.sendMessage("Invalid arguments for command job. Should be /job <job title>");
            return false;
        }

        String job = args[0].toLowerCase();

        PlayerInformation pl = Worksite.onlinePlayers.get(player.getUniqueId());

        if(pl.jobs.size() >= 2){
            player.sendMessage("Sorry, could not add job. You have too many jobs");
            return false;
        }

        if(job.equals("blacksmith")){
            pl.jobs.add("blacksmith");
            pl.xp.add(0.0);
            player.sendMessage("Blacksmith job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("brewer")){
            pl.jobs.add("brewer");
            pl.xp.add(0.0);
            player.sendMessage("Brewer job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("builder")){
            pl.jobs.add("builder");
            pl.xp.add(0.0);
            player.sendMessage("Builder job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("cook")){
            pl.jobs.add("cook");
            pl.xp.add(0.0);
            player.sendMessage("Builder job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("enchanter")){
            pl.jobs.add("enchanter");
            pl.xp.add(0.0);
            player.sendMessage("Enchanter job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("explorer")){
            pl.jobs.add("explorer");
            pl.xp.add(0.0);
            player.sendMessage("Explorer job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("farmer")){
            pl.jobs.add("farmer");
            pl.xp.add(0.0);
            player.sendMessage("Farmer job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("fisher")){
            pl.jobs.add("fisher");
            pl.xp.add(0.0);
            player.sendMessage("Fisher job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("hunter")){
            pl.jobs.add("hunter");
            pl.xp.add(0.0);
            player.sendMessage("Hunter job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("machinist")){
            pl.jobs.add("machinist");
            pl.xp.add(0.0);
            player.sendMessage("Machinist job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("miner")){
            pl.jobs.add("miner");
            pl.xp.add(0.0);
            player.sendMessage("Miner job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("stonemason")) {
            pl.jobs.add("stonemason");
            pl.xp.add(0.0);
            player.sendMessage("stonemason job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        if(job.equals("woodcutter")){
            pl.jobs.add("woodcutter");
            pl.xp.add(0.0);
            player.sendMessage("Woodcutter job acquired.");
            Worksite.onlinePlayers.put(player.getUniqueId(), pl);
            return true;
        }

        return false;
    }
}
