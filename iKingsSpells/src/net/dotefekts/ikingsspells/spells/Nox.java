package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Nox extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    plugin.lumos.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
    return true;
  }
}