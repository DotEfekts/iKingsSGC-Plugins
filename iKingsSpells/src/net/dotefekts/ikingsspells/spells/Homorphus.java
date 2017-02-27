package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Homorphus extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    player.setVelocity(player.getVelocity().multiply(-15));
    return true;
  }
}