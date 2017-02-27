package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Impedimenta extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      playerHit.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
      return true;
    }
    return false;
  }
}