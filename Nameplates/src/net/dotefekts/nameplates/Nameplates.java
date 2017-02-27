package net.dotefekts.nameplates;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.gui.GenericLabel;

public class Nameplates extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	ListenerMethods listener = null;

	Map<String, GenericLabel> playerWidget = new HashMap<String, GenericLabel>();
	
	public void onEnable(){
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
			Logger.getLogger("Minecraft").warning("[Nameplates] PermissionsEx plugin wasn't found. Disabling plugin.");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
		listener = new ListenerMethods(this);
		getCommand("npreload").setExecutor(this);
		getServer().getPluginManager().registerEvents(listener,this);
		Logger.getLogger("Minecraft").info("[Nameplates] Enabled!");
	}
	
	public void onDisable(){ 
	 
		log.info("[Nameplates] Disabling.");
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("npreload")){
			return true;
		}
		return false;
	}
	
	public FileConfiguration getConfigFile(){
		FileConfiguration files = getConfig();
		return files;
	}
	
}