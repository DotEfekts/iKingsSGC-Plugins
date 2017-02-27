package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class PetrificusTotalus extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      plugin.frozen.put(playerHit.getName(), Long.valueOf(System.currentTimeMillis() + 10000L));
      return true;
    }
    return false;
  }
}