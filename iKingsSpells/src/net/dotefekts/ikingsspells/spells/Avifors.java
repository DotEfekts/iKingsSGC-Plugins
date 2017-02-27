package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

class Avifors extends Charms
{
  @SuppressWarnings("deprecation")
public boolean cast(Player player, Entity entityHit)
  {
    if (!(entityHit instanceof Player)) {
      if (entityHit == null) {
        return false;
      }
      entityHit.getLocation().getWorld().spawnCreature(entityHit.getLocation(), EntityType.CHICKEN);
      entityHit.remove();
      return true;
    }
    return false;
  }
}