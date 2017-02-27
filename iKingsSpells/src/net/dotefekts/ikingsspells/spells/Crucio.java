package net.dotefekts.ikingsspells.spells;

import java.util.TimerTask;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Crucio extends Curses
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      final Player playerHit = (Player)entityHit;
      playerHit.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 100));
      playerHit.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
      final long timeout = System.currentTimeMillis() + 10000L;
      this.task = new TimerTask() {
        public void run() {
          if (playerHit.getHealth() < 2) {
            playerHit.setHealth(2);
          }
          if (System.currentTimeMillis() > timeout)
            cancel();
        }
      };
      this.timer.scheduleAtFixedRate(this.task, 0L, 500L);
      return true;
    }
    return false;
  }
}