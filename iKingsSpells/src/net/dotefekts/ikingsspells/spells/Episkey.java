package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Episkey extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if (player.getHealth() == player.getMaxHealth()) {
      if ((entityHit instanceof Player)) {
        Player playerHit = (Player)entityHit;
        playerHit.setHealth(playerHit.getMaxHealth());
        return true;
      }
    } else {
      player.setHealth(player.getMaxHealth());
      return true;
    }
    return false;
  }
}