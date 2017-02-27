package net.dotefekts.ikingsspells.spells;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

class Repairo extends Spells
{
  @SuppressWarnings("deprecation")
public boolean cast(Player player, Entity entityHit)
  {
    if (!repairInv(player.getInventory())) {
      if ((entityHit instanceof Player)) {
        Player playerHit = (Player)entityHit;
        if (repairInv(playerHit.getInventory())) {
          playerHit.updateInventory();
          return true;
        }
      }
    } else {
      player.updateInventory();
      return true;
    }
    return false;
  }

  public boolean repairInv(Inventory inventory) {
    ItemStack[] items = inventory.getContents();
    boolean fixed = false;
    for (int i = 0; i < items.length; i++) {
      if ((items[i] != null) && 
        (repairMats.contains(items[i].getType())) && 
        (items[i].getDurability() != 0)) {
        items[i].setDurability((short)0);
        fixed = true;
      }

    }

    inventory.setContents(items);
    return fixed;
  }
}