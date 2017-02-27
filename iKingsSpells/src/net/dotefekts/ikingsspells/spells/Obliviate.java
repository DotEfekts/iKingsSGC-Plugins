package net.dotefekts.ikingsspells.spells;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Obliviate extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      playerHit.teleport(playerHit.getLocation().getWorld().getSpawnLocation());
      playerHit.sendMessage(ChatColor.RED + "You were sent back the the spawn with Obliviate!");
      return true;
    }
    return false;
  }
}