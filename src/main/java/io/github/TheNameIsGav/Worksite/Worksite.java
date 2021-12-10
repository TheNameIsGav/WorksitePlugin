package io.github.TheNameIsGav.Worksite;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Worksite extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    private File playerConfigFile;
    private FileConfiguration playerConfig;

    public static Worksite instance;
    public static Map<UUID, PlayerInformation> onlinePlayers = new HashMap<>();

    //Map of player name to value they are owed on entrance
    public static Map<String, Double> offlinePayments = new HashMap<>();

    @Override
    public void onEnable(){
        getLogger().info("onEnable is called!");
        saveConfig();
        instance = this;
        onlinePlayers = new HashMap<>();

        createCustomConfig();
        enableAllListeners();
        commandInitializer();

        //theoretically puts all the offline payments into the listing
        for(String s : playerConfig.getConfigurationSection("OfflinePayments").getKeys(false)){
            offlinePayments.put(s, playerConfig.getDouble(s + ".val"));
        }
    }

    public FileConfiguration getCustomConfig(){
        return playerConfig;
    }

    @EventHandler
    public void playerJoins(PlayerJoinEvent event){
        Player player = event.getPlayer();

        PlayerInformation pl = ReadPlayerInformation(player.getUniqueId());

        //Theoretically offline payments
        Double val = offlinePayments.get(player.getDisplayName());
        if(val != null){
            pl.balance += val;
            offlinePayments.remove(player.getDisplayName());
        }

        //Put pl into online players
        onlinePlayers.put(player.getUniqueId(), pl);
    }

    @EventHandler
    public void playerLeaves(PlayerQuitEvent event){
        Player player = event.getPlayer();

        //Write player to the file
        WritePlayerInformation(player.getUniqueId());

        //Remove player from the list of online players
        onlinePlayers.remove(player.getUniqueId());
    }

    void WritePlayerInformation(UUID player){
        PlayerInformation pl = onlinePlayers.get(player);

        playerConfig.set("Data." + player + ".balance", pl.balance);
        playerConfig.set("Data." + player + ".jobs", pl.jobs);
        playerConfig.set("Data." + player + ".xp", pl.xp);

        try{
            playerConfig.save(playerConfigFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    PlayerInformation ReadPlayerInformation(UUID player){
        PlayerInformation pl = new PlayerInformation();
        pl.balance = playerConfig.getDouble("Data." + player + ".balance");
        List<String> jobString = playerConfig.getStringList("Data." + player + ".jobs");
        List<Double> xpString = playerConfig.getDoubleList("Data." + player + ".xp");

        pl.jobs = (ArrayList<String>) jobString;
        pl.xp = (ArrayList<Double>) xpString;

        return pl;

    }

    void commandInitializer(){
        JobsCommander jobsCommander = new JobsCommander();
        this.getCommand("job").setExecutor(jobsCommander);
        this.getCommand("jobs").setExecutor(jobsCommander);
        this.getCommand("worksitehelp").setExecutor(jobsCommander);
        this.getCommand("resign").setExecutor(jobsCommander);
        this.getCommand("joblist").setExecutor(jobsCommander);

        EconomyCommander ecoCommander = new EconomyCommander();
        this.getCommand("pay").setExecutor(ecoCommander);
        this.getCommand("balance").setExecutor(ecoCommander);
    }

    void enableAllListeners(){
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Woodcutter(), this);
        getServer().getPluginManager().registerEvents(new Weaponsmith(), this);
        getServer().getPluginManager().registerEvents(new Miner(), this);
        getServer().getPluginManager().registerEvents(new Hunter(), this);
        getServer().getPluginManager().registerEvents(new Fisher(), this);
        getServer().getPluginManager().registerEvents(new Explorer(), this);
        getServer().getPluginManager().registerEvents(new Enchanter(), this);
        getServer().getPluginManager().registerEvents(new Crafter(), this);
        getServer().getPluginManager().registerEvents(new Builder(), this);
        getServer().getPluginManager().registerEvents(new Brewer(), this);
    }

    private void createCustomConfig(){
        playerConfigFile = new File(getDataFolder(), "playerConfig.yml");
        if(!playerConfigFile.exists()){
            getLogger().info("Config file doesn't exist, instantiating");
            playerConfigFile.getParentFile().mkdirs();
            saveResource("playerConfig.yml", false);
        }

        playerConfig = new YamlConfiguration();
        try{
            playerConfig.load(playerConfigFile);
        } catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable(){
        getLogger().info("onDisable is called");

        for(UUID p : onlinePlayers.keySet()){
            WritePlayerInformation(p);
        }

        //Writes all currently active offline payments to file
        for(String name : offlinePayments.keySet()){
            playerConfig.set("OfflinePayments." + name + ".value", offlinePayments.get(name));
        }

        instance = null;
        onlinePlayers = null;
    }
}
