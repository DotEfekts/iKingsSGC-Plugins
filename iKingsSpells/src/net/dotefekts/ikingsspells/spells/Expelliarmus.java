package net.dotefekts.ikingsspells.spells;

import net.dotefekts.ikingsspells.Wand;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Expelliarmus extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      if (Wand.isWand(playerHit.getItemInHand())) {
        playerHit.getLocation().getWorld().dropItem(playerHit.getLocation(), playerHit.getItemInHand());
        playerHit.getInventory().remove(playerHit.getItemInHand());
        return true;
      }
    }
    return false;
  }
}