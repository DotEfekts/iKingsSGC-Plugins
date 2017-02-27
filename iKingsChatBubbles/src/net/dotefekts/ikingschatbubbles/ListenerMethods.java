package net.dotefekts.ikingschatbubbles;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class ListenerMethods implements Listener {
	
	iKingsChatBubbles plugin = null;
	HashMap<String, ArrayList<String>> playerNameplates = new HashMap<String, ArrayList<String>>();
	
	public ListenerMethods(iKingsChatBubbles plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event){
		Player player = event.getPlayer();
		if(!PermissionsEx.getPermissionManager().has(player, "ikingschatbubbles.bypass")){
			final SpoutPlayer sPlayer = ((SpoutPlayer)player);
			String message = event.getMessage().trim();
			if(message.length()>25)
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
				}
				
			}, 200L);
		}
	}
	
}
