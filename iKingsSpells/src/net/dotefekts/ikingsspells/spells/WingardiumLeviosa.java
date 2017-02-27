package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class WingardiumLeviosa extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    player.setAllowFlight(true);
    plugin.flying.put(player.getName(), Long.valueOf(System.currentTimeMillis() + 10000L));
    return true;
  }
}