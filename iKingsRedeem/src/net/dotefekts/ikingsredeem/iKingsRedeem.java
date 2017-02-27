package net.dotefekts.ikingsredeem;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class iKingsRedeem extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	ListenerMethods listener = new ListenerMethods();
	
	public void onEnable(){
		FileConfiguration file = this.getConfig();
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
			Logger.getLogger("Minecraft").warning("[iKingsRedeem] PermissionsEx plugin wasn't found. Disabling plugin.");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
		if(!file.getKeys(true).contains("ResetConfig")||file.getBoolean("ResetConfig")==true){
			File configdel = new File(getDataFolder(), "config.yml");
			configdel.delete();
			this.reloadConfig();
			file=this.getConfig();
			file.set("ResetConfig", false);
			file.set("Enabled", true);
			file.set("Coupons.test.Command", "say Coupon test.");
			file.set("Coupons.test.Count", 1);
			this.saveConfig();
			Logger.getLogger("Minecraft").info("[iKingsRedeem] Configuration file reset.");
		}
		if(file.contains("Enabled")){
			if(!file.getBoolean("Enabled")){
				Logger.getLogger("Minecraft").warning("[iKingsRedeem] Configuration set to disable plugin.");
				Logger.getLogger("Minecraft").warning("[iKingsRedeem] Disabling.");
				Bukkit.getServer().getPluginManager().disablePlugin(this);
			}
		} else {
			file.set("Enabled", true);
		}
		listener.storeInstance(this);
		getServer().getPluginManager().registerEvents(listener,this);
		Logger.getLogger("Minecraft").info("[iKingsRedeem] Enabled!");
	}
	
	public void onDisable(){ 
	 
		log.info("[iKingsRedeem] Disabling.");
		
	}
	
	public FileConfiguration getConfigFile(){
		FileConfiguration files = getConfig();
		return files;
	}
	
}