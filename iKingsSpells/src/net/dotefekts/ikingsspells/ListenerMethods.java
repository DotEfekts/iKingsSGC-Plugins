package net.dotefekts.ikingsspells;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

@SuppressWarnings("deprecation")
public class ListenerMethods
  implements Listener
{
  Logger log = Logger.getLogger("Minecraft");
  iKingsSpells plugin = null;
  FileConfiguration config = null;

  public void storeInstance(iKingsSpells o) {
    this.plugin = o;
    this.config = o.getConfig();
  }

  @EventHandler
  public void playerMove(PlayerMoveEvent event) {
    if ((this.plugin.frozen.containsKey(event.getPlayer().getName())) || (this.plugin.stupefy.containsKey(event.getPlayer().getName())))
      event.setCancelled(true);
  }

  @EventHandler
  public void blockBreak(BlockBreakEvent event) {
	  if(Wand.isWand(event.getPlayer().getItemInHand())) {
		  event.setCancelled(true);
	  }
  }
  
  @EventHandler
  public void playerInteract(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    SpoutItemStack stack = new SpoutItemStack(this.plugin.wand, 1);
    if ((player.getItemInHand().getType().equals(Material.STICK)) && (player.getItemInHand().getAmount() == 1)) {
      if (((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && 
        (!plugin.customConfig.contains(player.getName() + ".LastSpell"))) {
        removeFromInventory(player.getInventory(), player.getItemInHand());
        player.getInventory().addItem(new ItemStack[] { stack });
        player.updateInventory();
        plugin.customConfig.set(player.getName() + ".LastSpell", "none");
        plugin.customConfig.set(player.getName() + ".ToPerform", "episkey");
        plugin.saveCustomConfig();
        player.sendMessage(ChatColor.GREEN + "You feel power flowing through you.");
        player.sendMessage(ChatColor.GREEN + "You feel every goosebump on your skin.");
        player.sendMessage(ChatColor.GREEN + "You feel, that you're now a wizard.");
      }
    }
    else if (Wand.isWand(event.getPlayer().getItemInHand())) {
      if ((event.getAction().equals(Action.LEFT_CLICK_AIR)) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
        this.plugin.createOverlay((SpoutPlayer)event.getPlayer());
      } else if ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
        String spell = this.plugin.customConfig.getString(player.getName() + ".ToPerform");
        player.chat(spell);
      }
    }
  }

  @EventHandler
  public void onButtonClick(ButtonClickEvent event)
  {
    SpoutPlayer player = event.getPlayer();

    if (!plugin.playerPopup.containsKey(player)) {
      return;
    }

    if (plugin.playerPopup.get(player) != event.getButton().getScreen()) {
      return;
    }
    plugin.customConfig.set(player.getName() + ".ToPerform", event.getButton().getText());
    plugin.saveCustomConfig();
    event.getPlayer().getMainScreen().closePopup();
  }

  @EventHandler
  public void entityDeath(EntityDeathEvent event) {
    Iterator<Entry<String, Set<Object>>> iter = plugin.patronus.entrySet().iterator();
    Iterator<Object> localIterator1;
    for (; iter.hasNext(); 
      localIterator1.hasNext())
    {
      Map.Entry<String, Set<Object>> entry = (Map.Entry<String, Set<Object>>)iter.next();
      localIterator1 = ((Set<Object>)entry.getValue()).iterator();
      Object obj = localIterator1.next();
      if ((obj instanceof LivingEntity)) {
        Entity entity = (Entity)obj;
        if (event.getEntity().equals(entity)) {
          event.getDrops().clear();
          iter.remove();
        }
      }
    }
  }

  @EventHandler
  public void entityTarget(EntityTargetEvent event)
  {
    Iterator<Entry<String, Set<Object>>> iter = plugin.patronus.entrySet().iterator();
    Iterator<Object> localIterator1;
    for (; iter.hasNext(); 
      localIterator1.hasNext())
    {
      Map.Entry<String, Set<Object>> entry = (Map.Entry<String, Set<Object>>)iter.next();
      localIterator1 = ((Set<Object>)entry.getValue()).iterator();
      Object obj = localIterator1.next();
      if ((obj instanceof LivingEntity)) {
        Entity entity = (Entity)obj;
        if ((event.getEntity().equals(entity)) && 
          ((event.getTarget() instanceof Player)) && 
          (Bukkit.getServer().getPlayer((String)entry.getKey()) != null) && 
          (((Player)event.getTarget()).equals(Bukkit.getServer().getPlayer((String)entry.getKey()))))
          event.setCancelled(true);
      }
    }
  }

  @EventHandler
  public void playerPickup(PlayerPickupItemEvent event)
  {
	  ItemStack stack = event.getItem().getItemStack();
    if (Wand.isWand(stack)) {
    	for (ItemStack item : event.getPlayer().getInventory().getContents()) {
    	    if (item != null)
    	        if(Wand.isWand(item)) {
    	        	event.setCancelled(true);
    	        	return;
    	        }
    	}
    	if (!plugin.customConfig.contains(event.getPlayer().getName() + ".LastSpell")){
    		event.setCancelled(true);
        	return;
    	} else {
    		if(stack.getAmount() > 1) {
    			ItemStack newStack = stack.clone();
    			newStack.setAmount(newStack.getAmount() - 1);
    			stack.setAmount(1);
    			event.getItem().remove();
    			event.getItem().getLocation().getWorld().dropItemNaturally(event.getItem().getLocation(), newStack);
    			event.getPlayer().getInventory().addItem(stack);
    			event.setCancelled(true);
    		}
    	}
    }
  }

@EventHandler
  public void playerChat(PlayerChatEvent event)
  {
    Player player = event.getPlayer();
    String message = event.getMessage().trim();
    String[] messageA = event.getMessage().trim().split("\\s+");
    if ((messageA[0].equalsIgnoreCase("accio")) || (messageA[0].equalsIgnoreCase("fidelius")) || (messageA[0].equalsIgnoreCase("apparate"))) {
      if (plugin.customConfig.contains(event.getPlayer().getName() + ".LastSpell")) {
        if (Caster.castSpell(player, messageA))
          player.sendMessage(ChatColor.GREEN + "Spell cast!");
        else {
          player.sendMessage(ChatColor.RED + "Spell failed!");
        }
      }
      event.setCancelled(true);
    } else if (Caster.checkSpell(message)) {
      if (Wand.isWand(event.getPlayer().getItemInHand())) {
        Set<Player> toRemove = new HashSet<Player>();
        if (config.contains("HearDistance")) {
          if (config.isInt("HearDistance")) {
            for (Player players : event.getRecipients()) {
              if (player.getLocation().distance(players.getLocation()) > config.getInt("HearDistance"))
                toRemove.add(players);
            }
          }
          else {
            for (Player players : event.getRecipients()) {
              if (player.getLocation().distance(players.getLocation()) > 50.0D)
                toRemove.add(players);
            }
          }
        }
        else {
          for (Player players : event.getRecipients()) {
            if (player.getLocation().distance(players.getLocation()) > 50.0D) {
              toRemove.add(players);
            }
          }
        }
        for (Player players : toRemove) {
          event.getRecipients().remove(players);
        }
        event.getRecipients().remove(player);
        if (Caster.castSpell(player, message)) {
          player.sendMessage(ChatColor.GREEN + "Spell cast!");
          for (Player players : event.getRecipients())
            players.sendMessage(ChatColor.BLUE + player.getName() + " cast " + WordUtils.capitalize(message.toLowerCase()) + "!");
        }
        else {
          player.sendMessage(ChatColor.RED + "Spell failed!");
        }
      }
      event.setCancelled(true);
    }
  }

  public void removeFromInventory(Inventory inventory, ItemStack item)
  {
    int amt = item.getAmount();
    ItemStack[] items = inventory.getContents();
    for (int i = 0; i < items.length; i++) {
      if ((items[i] != null) && (items[i].getType() == item.getType()) && (items[i].getDurability() == item.getDurability())) {
        if (items[i].getAmount() > amt) {
          items[i].setAmount(items[i].getAmount() - amt);
          break;
        }if (items[i].getAmount() == amt) {
          items[i] = null;
          break;
        }
        amt -= items[i].getAmount();
        items[i] = null;
      }
    }

    inventory.setContents(items);
  }
}