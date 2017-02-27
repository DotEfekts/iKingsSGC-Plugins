package net.dotefekts.ikingschatbubbles;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class iKingsChatBubbles extends JavaPlugin {

	public ListenerMethods listener = null;
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		listener = new ListenerMethods(this);
		getServer().getPluginManager().registerEvents(listener,this);
		log.info("[iKingsChatBubbles] Enabled!");
	}
	
}
