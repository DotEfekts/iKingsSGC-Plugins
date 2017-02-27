package net.dotefekts.nameplates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import mmo.Core.InfoAPI.MMOInfoEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class ListenerMethods implements Listener {
	
	Logger log = Logger.getLogger("Minecraft");
	Nameplates plugin = null;
	HashMap<String, ArrayList<String>> playerNameplates = new HashMap<String, ArrayList<String>>();
	
	public ListenerMethods(Nameplates plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerLogin(PlayerJoinEvent event){
		sendNameplates();
	}
	
	@EventHandler
	public void onMMOInfoEvent(MMOInfoEvent event){
		if (event.isToken("nameplate")) {
			SpoutPlayer player = event.getPlayer();
			if(plugin.playerWidget.containsKey(player.getName())){
				plugin.playerWidget.remove(player.getName());
			}
			String title = PermissionsEx.getPermissionManager().getUser(player).getPrefix()+player.getName()+PermissionsEx.getPermissionManager().getUser(player).getSuffix();
			title = title.replaceAll("&", "§");
			ArrayList<String> nameplate = null;
			if(!playerNameplates.containsKey(player.getName())){
				nameplate = new ArrayList<String>();
				nameplate.add(0, title);
				playerNameplates.put(player.getName(), nameplate);
			} else {
				nameplate = playerNameplates.get(player.getName());
				nameplate.set(0, title);
				playerNameplates.put(player.getName(), nameplate);
			}
			Widget label = new GenericLabel(title).setResize(true).setFixed(true);
			event.setWidget(plugin, label);
			plugin.playerWidget.put(player.getName(), ((GenericLabel)label));
		}
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){
		String[] args = event.getMessage().split(" "); 
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("/pex") || args[0].equalsIgnoreCase("/sorthat") || args[0].equalsIgnoreCase("/npreload")){
				sendNameplates();
			}
		}
	}
	
	
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event){
		Player player = event.getPlayer();
		if(!PermissionsEx.getPermissionManager().has(player, "ikingschatbubbles.bypass")){
			final SpoutPlayer sPlayer = ((SpoutPlayer)player);
			String message = event.getMessage().trim();
			if(message.length() > 25)
				message = message.substring(0, 22) + "...";
			String title = "";
			final String name = sPlayer.getName();
			ArrayList<String> nameplate = null;
			if(!playerNameplates.containsKey(name)){
				nameplate = new ArrayList<String>();
				nameplate.add(0, sPlayer.getTitle());
				nameplate.add(1, message);
				playerNameplates.put(name, nameplate);
			} else {
				nameplate = playerNameplates.get(name);
				nameplate.add(1, message);
				playerNameplates.put(name, nameplate);
			}
			int i = 0;
			for(String line : nameplate){
				if(i == 3) break;
				title += line + "\n";
				i++;
			}
			title = title.substring(0, title.length()-1);
			sPlayer.setTitle(title);
			for(Player players : Bukkit.getOnlinePlayers()){
				sPlayer.setTitleFor(((SpoutPlayer)players), title);
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				
				public void run() {
					String title = "";
					ArrayList<String> nameplate = playerNameplates.get(name);
					nameplate.remove(nameplate.size()-1);
					int i = 0;
					for(String line : nameplate){
						if(i == 3) break;
						title += line + "\n";
						i++;
					}
					title = title.substring(0, title.length()-1);
					sPlayer.setTitle(title);
					for(Player players : Bukkit.getOnlinePlayers()){
						sPlayer.setTitleFor(((SpoutPlayer)players), title);
					}
				}
				
			}, 200L);
		}
	}
	
	private void sendNameplates(){
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			
			public void run() {
				for(SpoutPlayer player : Spout.getServer().getOnlinePlayers()){
					 String title = PermissionsEx.getPermissionManager().getUser(player).getPrefix()+player.getName()+PermissionsEx.getPermissionManager().getUser(player).getSuffix();
					 title = title.replaceAll("&", "§");
					 ArrayList<String> nameplate = null;
					if(!playerNameplates.containsKey(player.getName())){
						nameplate = new ArrayList<String>();
						nameplate.add(0, title);
						playerNameplates.put(player.getName(), nameplate);
					} else {
						nameplate = playerNameplates.get(player.getName());
						nameplate.set(0, title);
						playerNameplates.put(player.getName(), nameplate);
					}
					if(plugin.playerWidget.containsKey(player.getName())){
						plugin.playerWidget.get(player.getName()).setText(title);
					}
					player.setTitle(title);
					for(Player players : Bukkit.getOnlinePlayers()){
						player.setTitleFor(((SpoutPlayer)players), title);
					}
				}
			}
		}, 10L);
	}
}