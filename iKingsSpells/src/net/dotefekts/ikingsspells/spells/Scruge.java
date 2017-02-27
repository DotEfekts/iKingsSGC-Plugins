package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

class Scruge extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Slime)) {
      entityHit.remove();
      return true;
    }
    return false;
  }
}