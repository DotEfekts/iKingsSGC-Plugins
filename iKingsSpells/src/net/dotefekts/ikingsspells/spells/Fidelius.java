package net.dotefekts.ikingsspells.spells;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class Fidelius extends Extra
{
  public boolean cast(Player player, String[] message)
  {
    if (Bukkit.getPlayer(message[1]) != null) {
      if (message.length > 2) {
        String messageA = "(PM) <" + player.getName() + ">";
        for (int x = 2; x < message.length; x++) {
          messageA = messageA + " " + message[x];
        }
        Bukkit.getPlayer(message[1]).sendMessage(messageA);
        return true;
      }
      player.sendMessage(ChatColor.RED + "No message supplied.");
      return false;
    }

    player.sendMessage(ChatColor.RED + "Player not found.");
    return false;
  }
}