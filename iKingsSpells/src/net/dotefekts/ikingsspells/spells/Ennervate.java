package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Ennervate extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    if (plugin.stupefy.containsKey(player.getName())) {
      plugin.stupefy.remove(player.getName());
      return true;
    }
    return false;
  }
}