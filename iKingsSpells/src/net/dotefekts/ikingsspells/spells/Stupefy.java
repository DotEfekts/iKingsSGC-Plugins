package net.dotefekts.ikingsspells.spells;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Stupefy extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    if ((entityHit instanceof Player)) {
      Player playerHit = (Player)entityHit;
      playerHit.sendMessage(ChatColor.RED + "You've been hit by Stupefy! Quick, cast Ennervate!");
      plugin.stupefy.put(playerHit.getName(), Long.valueOf(System.currentTimeMillis() + 10000L));
      playerHit.damage(2);
      return true;
    }
    return false;
  }
}