package net.dotefekts.ikingsspells.spells;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class PriorIncantato extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    String spell = WordUtils.capitalize(plugin.customConfig.getString(player.getName() + ".LastSpell").toLowerCase());
    player.sendMessage(ChatColor.GREEN + "Last spell: " + ChatColor.GOLD + spell);
    return true;
  }
}