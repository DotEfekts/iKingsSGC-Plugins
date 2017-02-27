package net.dotefekts.ikingsspells.spells;

import java.util.TimerTask;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Conjunctivitis extends Curses
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      final Player playerHit = (Player)entityHit;
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
      playerHit.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
      final long timeout = System.currentTimeMillis() + 10000L;
      this.task = new TimerTask() {
        public void run() {
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, 1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(0.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          playerHit.playEffect(playerHit.getLocation().add(-1.0D, 1.0D, -1.0D), Effect.SMOKE, 4);
          if (System.currentTimeMillis() > timeout)
            cancel();
        }
      };
      this.timer.scheduleAtFixedRate(this.task, 0L, 100L);
      return true;
    }
    return false;
  }
}