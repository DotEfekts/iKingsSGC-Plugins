package net.dotefekts.ikingsspells.spells;

import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

class Apparate extends Extra
{
  public boolean cast(Player player, String[] warp)
  {
    if (warp.length == 2) {
      Collection<String> warps = this.essen.getWarps().getList();
      if (!warps.contains(warp[1])) {
        player.sendMessage(ChatColor.RED + "That's not a valid destination.");
        return false;
      }
      try {
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.SMOKE, 4);
        player.getWorld().playEffect(player.getLocation(), Effect.EXTINGUISH, 0);
        player.teleport(this.essen.getWarps().getWarp(warp[1]));
        this.essen.getWarps().getWarp(warp[1]).getWorld().createExplosion(this.essen.getWarps().getWarp(warp[1]), 0.0F, false);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return true;
    } else if(warp.length < 2) {
    	player.sendMessage(ChatColor.RED + "You have too few words in the spell.");
    } else
    player.sendMessage(ChatColor.RED + "You have too many words in the spell.");
    return false;
  }
}