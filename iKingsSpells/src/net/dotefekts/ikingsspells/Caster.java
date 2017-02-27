package net.dotefekts.ikingsspells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import net.dotefekts.ikingsspells.spells.Charms;
import net.dotefekts.ikingsspells.spells.Curses;
import net.dotefekts.ikingsspells.spells.Extra;
import net.dotefekts.ikingsspells.spells.Spells;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public abstract class Caster
{
  public TimerTask task = null;
  protected Timer timer = new Timer();
  Logger log = Logger.getLogger("Minecraft");
  public static iKingsSpells plugin = null;
  public static FileConfiguration config = null;
  public static Map<String, Caster> spellsList = new HashMap<String, Caster>();
  public static HashSet<Byte> transparentBlocks = new HashSet<Byte>();
  public static Map<String, Extra> extraSpells = new HashMap<String, Extra>();
  public static Map<String, Map<String, Long>> cooldowns = new HashMap<String, Map<String, Long>>();
  static PermissionManager permissions = PermissionsEx.getPermissionManager();

  public static void storeInstance(iKingsSpells o) {
    plugin = o;
    config = o.getConfig();
    Charms.putSpells();
    Spells.putSpells();
    Curses.putSpells();
    Extra.putSpells(plugin);

    transparentBlocks.add(Byte.valueOf((byte)0));
    transparentBlocks.add(Byte.valueOf((byte)6));
    transparentBlocks.add(Byte.valueOf((byte)8));
    transparentBlocks.add(Byte.valueOf((byte)9));
    transparentBlocks.add(Byte.valueOf((byte)10));
    transparentBlocks.add(Byte.valueOf((byte)11));
    transparentBlocks.add(Byte.valueOf((byte)27));
    transparentBlocks.add(Byte.valueOf((byte)28));
    transparentBlocks.add(Byte.valueOf((byte)31));
    transparentBlocks.add(Byte.valueOf((byte)32));
    transparentBlocks.add(Byte.valueOf((byte)34));
    transparentBlocks.add(Byte.valueOf((byte)36));
    transparentBlocks.add(Byte.valueOf((byte)37));
    transparentBlocks.add(Byte.valueOf((byte)38));
    transparentBlocks.add(Byte.valueOf((byte)39));
    transparentBlocks.add(Byte.valueOf((byte)40));
    transparentBlocks.add(Byte.valueOf((byte)50));
    transparentBlocks.add(Byte.valueOf((byte)51));
    transparentBlocks.add(Byte.valueOf((byte)55));
    transparentBlocks.add(Byte.valueOf((byte)59));
    transparentBlocks.add(Byte.valueOf((byte)63));
    transparentBlocks.add(Byte.valueOf((byte)65));
    transparentBlocks.add(Byte.valueOf((byte)66));
    transparentBlocks.add(Byte.valueOf((byte)68));
    transparentBlocks.add(Byte.valueOf((byte)69));
    transparentBlocks.add(Byte.valueOf((byte)70));
    transparentBlocks.add(Byte.valueOf((byte)72));
    transparentBlocks.add(Byte.valueOf((byte)75));
    transparentBlocks.add(Byte.valueOf((byte)76));
    transparentBlocks.add(Byte.valueOf((byte)77));
    transparentBlocks.add(Byte.valueOf((byte)78));
    transparentBlocks.add(Byte.valueOf((byte)83));
    transparentBlocks.add(Byte.valueOf((byte)93));
    transparentBlocks.add(Byte.valueOf((byte)94));
    transparentBlocks.add(Byte.valueOf((byte)104));
    transparentBlocks.add(Byte.valueOf((byte)105));
    transparentBlocks.add(Byte.valueOf((byte)106));
    transparentBlocks.add(Byte.valueOf((byte)115));
    transparentBlocks.add(Byte.valueOf((byte)117));
    transparentBlocks.add(Byte.valueOf((byte)119));
    transparentBlocks.add(Byte.valueOf((byte)127));
    transparentBlocks.add(Byte.valueOf((byte)-125));
    transparentBlocks.add(Byte.valueOf((byte)-124));
  }

  public abstract boolean cast(Player paramPlayer, Entity paramEntity);

  public static boolean checkSpell(String message) {
    return spellsList.containsKey(message.toLowerCase());
  }

  public static boolean castSpell(Player player, String[] messageA) {
    if (!permissions.has(player, "ikingsspells." + messageA[0])) {
      player.sendMessage(ChatColor.RED + "You don't have permission.");
      return false;
    }
    if (player.getFoodLevel() > config.getInt("Spells." + messageA[0].toLowerCase() + ".Cost")) {
      if (!cooldowns.containsKey(player.getName())) {
        player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + messageA[0].toLowerCase() + ".Cost"));
        Map<String, Long> map = new HashMap<String, Long>();
        map.put(messageA[0].toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + messageA[0].toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Extra)extraSpells.get(messageA[0].toLowerCase())).cast(player, messageA);
      }if (!((Map<String, Long>)cooldowns.get(player.getName())).containsKey(messageA[0].toLowerCase())) {
        player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + messageA[0].toLowerCase() + ".Cost"));
        Map<String, Long> map = (Map<String, Long>)cooldowns.get(player.getName());
        map.put(messageA[0].toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + messageA[0].toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Extra)extraSpells.get(messageA[0].toLowerCase())).cast(player, messageA);
      }if (((Long)((Map<String, Long>)cooldowns.get(player.getName())).get(messageA[0].toLowerCase())).longValue() < System.currentTimeMillis()) {
        player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + messageA[0].toLowerCase() + ".Cost"));
        Map<String, Long> map = (Map<String, Long>)cooldowns.get(player.getName());
        map.put(messageA[0].toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + messageA[0].toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Extra)extraSpells.get(messageA[0].toLowerCase())).cast(player, messageA);
      }
    } else {
      player.sendMessage(ChatColor.RED + "You're too tired to do that");
      return false;
    }
    player.sendMessage(ChatColor.RED + "Spell cooling down.");
    return false;
  }

  public static boolean castSpell(Player player, String message) {
    if (!permissions.has(player, "ikingsspells." + message.replace(" ", ""))) {
      player.sendMessage(ChatColor.RED + "You don't have permission.");
      return false;
    }
    if (!message.equalsIgnoreCase("prior incantato")) {
      plugin.customConfig.set(player.getName() + ".LastSpell", message.toLowerCase());
      plugin.saveCustomConfig();
    }
    if (!message.equalsIgnoreCase("prior incantato")) {
      plugin.customConfig.set(player.getName() + ".ToPerform", message.toLowerCase());
      plugin.saveCustomConfig();
    }
    if ((player.getFoodLevel() > config.getInt("Spells." + message.toLowerCase() + ".Cost")) || (player.getGameMode().equals(GameMode.CREATIVE))) {
      if (!cooldowns.containsKey(player.getName())) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
          player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + message.toLowerCase() + ".Cost"));
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put(message.toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + message.toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Caster)spellsList.get(message.toLowerCase())).cast(player, getEntityLooked(player));
      }if (!((Map<String, Long>)cooldowns.get(player.getName())).containsKey(message.toLowerCase())) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
          player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + message.toLowerCase() + ".Cost"));
        }
        Map<String, Long> map = (Map<String, Long>)cooldowns.get(player.getName());
        map.put(message.toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + message.toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Caster)spellsList.get(message.toLowerCase())).cast(player, getEntityLooked(player));
      }if (((Long)((Map<String, Long>)cooldowns.get(player.getName())).get(message.toLowerCase())).longValue() < System.currentTimeMillis()) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
          player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + message.toLowerCase() + ".Cost"));
        }
        Map<String, Long> map = (Map<String, Long>)cooldowns.get(player.getName());
        map.put(message.toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + message.toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Caster)spellsList.get(message.toLowerCase())).cast(player, getEntityLooked(player));
      }if (permissions.has(player, "ikingsspells.bypasscooldown")) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
          player.setFoodLevel(player.getFoodLevel() - config.getInt("Spells." + message.toLowerCase() + ".Cost"));
        }
        Map<String, Long> map = (Map<String, Long>)cooldowns.get(player.getName());
        map.put(message.toLowerCase(), Long.valueOf(System.currentTimeMillis() + config.getInt("Spells." + message.toLowerCase() + ".Cooldown") * 1000));
        cooldowns.put(player.getName(), map);
        return ((Caster)spellsList.get(message.toLowerCase())).cast(player, getEntityLooked(player));
      }
    } else {
      player.sendMessage(ChatColor.RED + "You're too tired to do that");
      return false;
    }
    player.sendMessage(ChatColor.RED + "Spell cooling down.");
    return false;
  }

  static Entity getEntityLooked(Player player){
	  List<Entity> nearbyE = player.getNearbyEntities(config.getInt("SpellRange"),config.getInt("SpellRange"),config.getInt("SpellRange"));
	  ArrayList<LivingEntity> livingE = new ArrayList<LivingEntity>();

      for (Entity e : nearbyE) {
          if (e instanceof LivingEntity) {
              livingE.add((LivingEntity) e);
          }
      }
      BlockIterator bItr = new BlockIterator(player, config.getInt("SpellRange"));
      Block block;
      Location loc;
      int bx, by, bz;
      double ex, ey, ez;
      // loop through player's line of sight
      while (bItr.hasNext()) {
              block = bItr.next();
              bx = block.getX();
              by = block.getY();
              bz = block.getZ();
                      // check for entities near this block in the line of sight
                      for (LivingEntity e : livingE) {
                              loc = e.getLocation();
                              ex = loc.getX();
                              ey = loc.getY();
                              ez = loc.getZ();
                              if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
                              	if(!(e instanceof Player)){
                  					return e;
                  				} else if(e instanceof Player && (Player)e!=player){
                  					Player playerHit = (Player)e;
                  					if(plugin.protago.containsKey(playerHit.getName())){
                  						return player;
                  					} else {
                  						return e;
                  					}
                  				}
                              }
                      }
	
		}
		return null;
	}
}