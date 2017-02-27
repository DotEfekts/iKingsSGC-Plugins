package net.dotefekts.ikingsspells.spells;

import java.util.Iterator;
import java.util.Set;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

class FiniteIncantatum extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    if (plugin.lumos.containsKey(player.getName())) {
      plugin.lumos.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
    }
    if (plugin.frozen.containsKey(player.getName())) {
      plugin.frozen.remove(player.getName());
    }
    if (plugin.protago.containsKey(player.getName())) {
      plugin.protago.remove(player.getName());
    }
    if (plugin.patronus.containsKey(player.getName())) {
      Entity ent = null;
      Object object = null;
      for (Iterator<Object> localIterator = ((Set<Object>)plugin.patronus.get(player.getName())).iterator(); localIterator.hasNext(); ) { 
    	object = localIterator.next();
        if ((object instanceof Entity)) {
          ent = (Entity)object;
        }
      }
      ((Set<Object>)plugin.patronus.get(player.getName())).clear();
      ((Set<Object>)plugin.patronus.get(player.getName())).add(Long.valueOf(System.currentTimeMillis()));
      ((Set<Object>)plugin.patronus.get(player.getName())).add(ent);
    }
    PotionEffectType[] arrayOfPotionEffectType;
    int localObject1 = (arrayOfPotionEffectType = PotionEffectType.values()).length; 
    for (int object = 0; object < localObject1; object++) { PotionEffectType type = arrayOfPotionEffectType[object];
      if ((type != null) && 
        (player.hasPotionEffect(type))) {
        player.removePotionEffect(type);
      }
    }

    return true;
  }
}