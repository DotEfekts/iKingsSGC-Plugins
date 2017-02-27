package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Relashio extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    if (!plugin.frozen.containsKey(player.getName())) {
      if ((entityHit instanceof Player)) {
        Player playerHit = (Player)entityHit;
        if (plugin.frozen.containsKey(playerHit.getName())) {
          plugin.frozen.remove(playerHit.getName());
          return true;
        }
        return false;
      }
    }
    else {
      plugin.frozen.remove(player.getName());
      return true;
    }
    return false;
  }
}