package net.dotefekts.ikingsspells.spells;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

class Accio extends Extra
{
  public boolean cast(Player player, String[] message)
  {
    boolean success = false;
    for (int x = 1; x < message.length; x++) {
      if (message[x].equalsIgnoreCase("wand")) {
        SpoutItemStack stack = new SpoutItemStack(wand, 1);
        if (!player.getInventory().contains(stack)) {
          player.getInventory().addItem(new ItemStack[] { stack });
          success = true;
        }
      } else if (Bukkit.getPlayer(message[x]) != null) {
        if (Bukkit.getPlayer(message[x]).getLocation().distance(player.getLocation()) <= 20.0D) {
          Bukkit.getPlayer(message[x]).setVelocity(player.getLocation().toVector().subtract(Bukkit.getPlayer(message[x]).getLocation().toVector()).normalize().multiply(3));
          success = true;
        }
      } else if (EntityType.fromName(message[x]) != null) {
        for (Entity ent : player.getWorld().getEntities()) {
          if ((ent.getLocation().distance(player.getLocation()) <= 20.0D) && ((ent instanceof LivingEntity)) && 
            (ent.getType().equals(EntityType.fromName(message[x])))) {
            ent.setVelocity(player.getLocation().toVector().subtract(ent.getLocation().toVector()).normalize().multiply(3));
            success = true;
          }
        }

      }

    }

    return success;
  }
}