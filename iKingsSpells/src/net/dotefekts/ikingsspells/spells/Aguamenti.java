package net.dotefekts.ikingsspells.spells;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

class Aguamenti extends Charms
{
  public boolean cast(Player player, Entity entityHit)
  {
    List<Block> lastBlocks = player.getLastTwoTargetBlocks(transparentBlocks, config.getInt("SpellRange"));
    if (((Block)lastBlocks.get(1)).getTypeId() == 118) {
      ((Block)lastBlocks.get(1)).setData((byte)3);
      return true;
    }
    BlockFace face = ((Block)lastBlocks.get(1)).getFace((Block)lastBlocks.get(0));
    if (((Block)lastBlocks.get(1)).getTypeId() != 0) {
      if (((Block)lastBlocks.get(1)).getRelative(face).getType().equals(Material.FIRE)) {
        ((Block)lastBlocks.get(1)).getRelative(face).setType(Material.AIR);
        return true;
      }if ((((Block)lastBlocks.get(1)).getRelative(face).getTypeId() == 0) || (((Block)lastBlocks.get(1)).getRelative(face).getTypeId() == 8) || (((Block)lastBlocks.get(1)).getRelative(face).getTypeId() == 9)) {
        ((Block)lastBlocks.get(1)).getRelative(face).setType(Material.WATER);
        return true;
      }
      return false;
    }

    return false;
  }
}