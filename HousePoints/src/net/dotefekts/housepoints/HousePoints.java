package net.dotefekts.housepoints;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class HousePoints extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	ListenerMethods listener = new ListenerMethods();
	Map<String, Set<GenericLabel>> playerLabels = new HashMap<String, Set<GenericLabel>>();
	Map<String, GenericGradient> playerGradients = new HashMap<String, GenericGradient>();
	
	public void onEnable(){
		FileConfiguration file = this.getConfig();
		
		if(!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
			Logger.getLogger("Minecraft").warning("[HousePoints] PermissionsEx plugin wasn't found. Disabling plugin.");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
		if(!file.getKeys(true).contains("ResetConfig")||file.getBoolean("ResetConfig")==true){
			File configdel = new File(getDataFolder(), "config.yml");
			configdel.delete();
			this.reloadConfig();
			file=this.getConfig();
			file.set("ResetConfig", false);
			file.set("Enabled", true);
			file.set("LoginPoints", 10);
			file.set("Gryffindor.Points", 0);
			file.set("Gryffindor.GroupName", "Gryffindor");
			file.set("Gryffindor.Password", "Gryffindor");
			file.set("Hufflepuff.Points", 0);
			file.set("Hufflepuff.GroupName", "Hufflepuff");
			file.set("Hufflepuff.Password", "Hufflepuff");
			file.set("Ravenclaw.Points", 0);
			file.set("Ravenclaw.GroupName", "Ravenclaw");
			file.set("Ravenclaw.Password", "Ravenclaw");
			file.set("Slytherin.Points", 0);
			file.set("Slytherin.GroupName", "Slytherin");
			file.set("Slytherin.Password", "Slytherin");
			this.saveConfig();
			Logger.getLogger("Minecraft").info("[HousePoints] Configuration file reset.");
		}
		if(file.contains("Enabled")){
			if(!file.getBoolean("Enabled")){
				Logger.getLogger("Minecraft").warning("[HousePoints] Configuration set to disable plugin.");
				Logger.getLogger("Minecraft").warning("[HousePoints] Disabling.");
				Bukkit.getServer().getPluginManager().disablePlugin(this);
			}
		} else {
			file.set("Enabled", true);
		}
		listener.storeInstance(this);
		getServer().getPluginManager().registerEvents(listener,this);
		SpoutManager.getKeyBindingManager().registerBinding("HousePointsShowHide", Keyboard.KEY_J, "Hides and shows the mainscreen GUI", listener, this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){public void run(){
			Iterator<Map.Entry<String, Long>> iter = listener.loginCooldown.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Long> entry = iter.next();
				if(entry.getValue()<System.currentTimeMillis()){
					if(Bukkit.getPlayerExact(entry.getKey())!=null){
						Player player = Bukkit.getPlayerExact(entry.getKey());
						int points = getConfig().getInt("LoginPoints");
						PermissionManager permissions = PermissionsEx.getPermissionManager();
						for(String str : permissions.getUser(player).getGroupsNames()){
				 			if(str.equalsIgnoreCase(getConfig().getString("Gryffindor.GroupName"))){
				 				int currentPoints = getConfig().getInt("Gryffindor.Points");
							 	currentPoints += points;
							 	getConfig().set("Gryffindor.Points", currentPoints);
							 	saveConfig();
							 	updatePoints();
							 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
				 			} else if(str.equalsIgnoreCase(getConfig().getString("Hufflepuff.GroupName"))){
				 				int currentPoints = getConfig().getInt("Hufflepuff.Points");
							 	currentPoints += points;
							 	getConfig().set("Hufflepuff.Points", currentPoints);
							 	saveConfig();
							 	updatePoints();
							 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
				 			} else if(str.equalsIgnoreCase(getConfig().getString("Ravenclaw.GroupName"))){
				 				int currentPoints = getConfig().getInt("Ravenclaw.Points");
							 	currentPoints += points;
							 	getConfig().set("Ravenclaw.Points", currentPoints);
							 	saveConfig();
							 	updatePoints();
							 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
				 			} else if(str.equalsIgnoreCase(getConfig().getString("Slytherin.GroupName"))){
				 				int currentPoints = getConfig().getInt("Slytherin.Points");
							 	currentPoints += points;
							 	getConfig().set("Slytherin.Points", currentPoints);
							 	saveConfig();
							 	updatePoints();
							 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
				 			}
						}
						listener.loginCooldown.put(player.getName(), System.currentTimeMillis()+(60000*60));
					} else {
						iter.remove();
					}
				}
			}
		}}, 0, 1200L);
		Logger.getLogger("Minecraft").info("[HousePoints] Enabled!");
	}
	
	public void onDisable(){ 
	 
		log.info("[HousePoints] Disabling.");
		
	}
	
	public FileConfiguration getConfigFile(){
		FileConfiguration files = getConfig();
		return files;
	}
	
	public void createOverlay(SpoutPlayer player) {
		
		GenericGradient gradient = new GenericGradient();
		gradient.setTopColor(new Color(0, 0, 0, 0.5F));
		gradient.setBottomColor(new Color(0, 0, 0, 0.5F));
		gradient.setHeight(25).setWidth(180);
		gradient.setX(-180).setY(-35);
		gradient.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		gradient.setPriority(RenderPriority.Highest);

		GenericLabel gryL = new GenericLabel("Gryffindor§f: " + getConfig().getInt("Gryffindor.Points"));
		gryL.setTextColor(new Color(178, 0, 0));
		gryL.setHeight(20).setWidth(100);
		gryL.setX(-90).setY(-32);
		gryL.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		
		GenericLabel slyL = new GenericLabel("Slytherin§f: " + getConfig().getInt("Slytherin.Points"));
		slyL.setTextColor(new Color(0, 142, 0));
		slyL.setHeight(20).setWidth(100);
		slyL.setX(-90).setY(-20);
		slyL.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		
		GenericLabel hufL = new GenericLabel("Hufflepuff§f: " + getConfig().getInt("Hufflepuff.Points"));
		hufL.setTextColor(new Color(178, 179, 0));
		hufL.setHeight(20).setWidth(100);
		hufL.setX(-175).setY(-32);
		hufL.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		
		GenericLabel ravL = new GenericLabel("Ravenclaw§f: " + getConfig().getInt("Ravenclaw.Points"));
		ravL.setTextColor(new Color(0, 51, 204));
		ravL.setHeight(20).setWidth(100);
		ravL.setX(-175).setY(-20);
		ravL.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		
		player.getMainScreen().attachWidget(this, gradient);
		player.getMainScreen().attachWidget(this, gryL);
		player.getMainScreen().attachWidget(this, slyL);
		player.getMainScreen().attachWidget(this, hufL);
		player.getMainScreen().attachWidget(this, ravL);
		
		Set<GenericLabel> set = new HashSet<GenericLabel>();
		set.add(gryL);
		set.add(slyL);
		set.add(hufL);
		set.add(ravL);
		playerLabels.put(player.getName(), set);
		playerGradients.put(player.getName(), gradient);
   
	}
	
	public void updatePoints(){
		Iterator<Map.Entry<String,Set<GenericLabel>>> iter = playerLabels.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,Set<GenericLabel>> entry = iter.next();
			for(GenericLabel label : entry.getValue()){
				if(label.getText().matches("^Gryffindor§f: -?[0-9]*$")){
					label.setText("Gryffindor§f: " + getConfig().getInt("Gryffindor.Points"));
					label.setDirty(true);
				} else if(label.getText().matches("^Slytherin§f: -?[0-9]*$")){
					label.setText("Slytherin§f: " + getConfig().getInt("Slytherin.Points"));
					label.setDirty(true);
				} else if(label.getText().matches("^Hufflepuff§f: -?[0-9]*$")){
					label.setText("Hufflepuff§f: " + getConfig().getInt("Hufflepuff.Points"));
					label.setDirty(true);
				} else if(label.getText().matches("^Ravenclaw§f: -?[0-9]*$")){
					label.setText("Ravenclaw§f: " + getConfig().getInt("Ravenclaw.Points"));
					label.setDirty(true);
				}
				
			}
		}
	}
	
}