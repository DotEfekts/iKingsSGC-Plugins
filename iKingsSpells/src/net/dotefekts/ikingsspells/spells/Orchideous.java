package net.dotefekts.ikingsspells.spells;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

class Orchideous extends Spells
{
  @SuppressWarnings("deprecation")
public boolean cast(Player player, Entity entityHit)
  {
    player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.YELLOW_FLOWER, this.gen.nextInt(4) + 1) });
    player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.RED_ROSE, this.gen.nextInt(4) + 1) });
    player.updateInventory();
    return true;
  }
}