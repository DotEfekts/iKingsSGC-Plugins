package net.dotefekts.ikingsspells.spells;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Incendio extends Spells
{
  public boolean cast(Player player, Entity entityHit)
  {
    List<Block> lastBlocks = player.getLastTwoTargetBlocks(transparentBlocks, config.getInt("SpellRange"));
    BlockFace face = ((Block)lastBlocks.get(1)).getFace((Block)lastBlocks.get(0));
    if (((Block)lastBlocks.get(1)).getTypeId() != 0) {
      if ((((Block)lastBlocks.get(1)).getRelative(face).getType().equals(Material.STATIONARY_WATER)) || (((Block)lastBlocks.get(1)).getRelative(face).getType().equals(Material.WATER))) {
        ((Block)lastBlocks.get(1)).getRelative(face).setType(Material.AIR);
        return true;
      }if (((Block)lastBlocks.get(1)).getRelative(face).getTypeId() == 0) {
        ((Block)lastBlocks.get(1)).getRelative(face).setType(Material.FIRE);
        return true;
      }
      return false;
    }

    return false;
  }
}