package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Protego extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    plugin.protago.put(player.getName(), Long.valueOf(System.currentTimeMillis() + 5000L));
    return true;
  }
}