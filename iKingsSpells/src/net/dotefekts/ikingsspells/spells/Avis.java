package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

class Avis extends Spells
{
  @SuppressWarnings("deprecation")
public boolean cast(Player player, Entity entityHit)
  {
    Vector vec = player.getLocation().getDirection().multiply(2);
    Entity chicken = player.getLocation().getWorld().spawnCreature(player.getLocation(), EntityType.CHICKEN);
    chicken.setVelocity(vec);
    return true;
  }
}