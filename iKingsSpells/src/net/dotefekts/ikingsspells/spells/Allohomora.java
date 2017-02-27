package net.dotefekts.ikingsspells.spells;

import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.material.Door;

@SuppressWarnings("deprecation")
class Allohomora extends Charms
{
public boolean cast(Player player, Entity entityHit)
  {
    for (int x = -5; x < 6; x++) {
      for (int y = -5; y < 6; y++) {
        for (int z = -5; z < 6; z++) {
          if ((player.getLocation().getBlock().getRelative(x, y, z).getLocation().distance(player.getLocation()) <= 5.0D) && (
            (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 64) || (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 54))) {
            if (plugin.lwc != null) {
              if (plugin.lwc.findProtection(player.getLocation().getBlock().getRelative(x, y, z)) != null) {
                if (plugin.lwc.canAccessProtection(player, player.getLocation().getBlock().getRelative(x, y, z))) {
                  if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 54) {
                    Chest chest = (Chest)player.getLocation().getBlock().getRelative(x, y, z).getState();
                    player.openInventory(chest.getBlockInventory());
                    return true;
                  }if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 64) {
                    Door door = (Door)player.getLocation().getBlock().getRelative(x, y, z).getState().getData();
                    if (!door.isOpen()) {
                      door.setOpen(true);
                      player.getLocation().getBlock().getRelative(x, y, z).setData(door.getData());
                      return true;
                    }
                  }
                }
              } else {
                if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 54) {
                  Chest chest = (Chest)player.getLocation().getBlock().getRelative(x, y, z).getState();
                  player.openInventory(chest.getBlockInventory());
                } else if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 64) {
                  Door door = (Door)player.getLocation().getBlock().getRelative(x, y, z).getState().getData();
                  if (!door.isOpen()) {
                    door.setOpen(true);
                    player.getLocation().getBlock().getRelative(x, y, z).setData(door.getData());
                  }
                }
                return true;
              }
            } else {
              if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 54) {
                Chest chest = (Chest)player.getLocation().getBlock().getRelative(x, y, z).getState();
                player.openInventory(chest.getBlockInventory());
              } else if (player.getLocation().getBlock().getRelative(x, y, z).getTypeId() == 64) {
                Door door = (Door)player.getLocation().getBlock().getRelative(x, y, z).getState().getData();
                if (!door.isOpen()) {
                  door.setOpen(true);
                  player.getLocation().getBlock().getRelative(x, y, z).setData(door.getData());
                }
              }
              return true;
            }
          }
        }
      }
    }

    return false;
  }
}