package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class AvadaKadavra extends Curses
{
  public boolean cast(Player player, Entity entityHit)
  {
    if (!(entityHit instanceof Player)) {
      if (entityHit == null) {
        return false;
      }
      entityHit.getLocation().getWorld().strikeLightningEffect(entityHit.getLocation().subtract(0.0D, 1.0D, 0.0D));
      entityHit.remove();
      return true;
    }if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      entityHit.getLocation().getWorld().strikeLightningEffect(entityHit.getLocation().subtract(0.0D, 1.0D, 0.0D));
      playerHit.damage(10000);
      return true;
    }
    return false;
  }
}