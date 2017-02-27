package net.dotefekts.ikingsspells.spells;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

class ExpectoPatronum extends Charms
{
  @SuppressWarnings("deprecation")
public boolean cast(Player player, Entity entityHit)
  {
    String entityS = "null";
    if (plugin.customConfig.contains(player.getName() + ".Patronus")) {
      entityS = plugin.customConfig.getString(player.getName() + ".Patronus");
    }
    if (!plugin.patronus.containsKey(player.getName())) {
      if (!entityS.equals("null")) {
        Entity ent = player.getLocation().getWorld().spawnCreature(player.getLocation(), EntityType.valueOf(entityS));
        Set<Object> patronus = new HashSet<Object>(0);
        patronus.add(ent);
        patronus.add(Long.valueOf(System.currentTimeMillis() + 45000L));
        plugin.patronus.put(player.getName(), patronus);
        return true;
      }
      EntityType[] entA = { EntityType.CHICKEN, EntityType.COW, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PIG, EntityType.SHEEP, EntityType.SNOWMAN };
      EntityType entT = entA[this.rand.nextInt(8)];
      Entity ent = player.getLocation().getWorld().spawnCreature(player.getLocation(), entT);
      plugin.customConfig.set(player.getName() + ".Patronus", ent.getType().name());
      plugin.saveCustomConfig();
      Set<Object> patronus = new HashSet<Object>(0);
      patronus.add(ent);
      patronus.add(Long.valueOf(System.currentTimeMillis() + 45000L));
      plugin.patronus.put(player.getName(), patronus);
      return true;
    }

    player.sendMessage(ChatColor.RED + "You already have a patronus.");
    return false;
  }
}