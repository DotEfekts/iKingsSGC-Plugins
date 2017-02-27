package net.dotefekts.ikingshoneydukes;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ListenerMethods
  implements Listener
{
  Logger log = Logger.getLogger("Minecraft");
  iKingsHoneydukes plugin = null;
  FileConfiguration file = null;
  Item item = null;

  public void storeInstance(iKingsHoneydukes o) {
    this.plugin = o;
    this.file = o.getConfig();
  }
  @EventHandler
  public void playerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
      if (this.plugin.items.isItem(player.getItemInHand()))
        this.plugin.items.giveContents(player);
      else if (this.plugin.items.isFood(player.getItemInHand()))
        this.plugin.items.nourish(player);
  }
}