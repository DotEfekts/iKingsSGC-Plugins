package net.dotefekts.ikingsspells;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TimedEvents
  implements Runnable
{
  iKingsSpells plugin = null;
  public static Map<String, Block> lumosBlock = new HashMap<String, Block>();

  static Set<Integer> idList = new HashSet<Integer>();

  Block block = null;

  public void storeInstance(iKingsSpells o)
  {
    this.plugin = o;

    idList.add(Integer.valueOf(0));
    idList.add(Integer.valueOf(6));
    idList.add(Integer.valueOf(8));
    idList.add(Integer.valueOf(9));
    idList.add(Integer.valueOf(10));
    idList.add(Integer.valueOf(11));
    idList.add(Integer.valueOf(26));
    idList.add(Integer.valueOf(27));
    idList.add(Integer.valueOf(28));
    idList.add(Integer.valueOf(30));
    idList.add(Integer.valueOf(31));
    idList.add(Integer.valueOf(32));
    idList.add(Integer.valueOf(34));
    idList.add(Integer.valueOf(36));
    idList.add(Integer.valueOf(37));
    idList.add(Integer.valueOf(38));
    idList.add(Integer.valueOf(39));
    idList.add(Integer.valueOf(40));
    idList.add(Integer.valueOf(44));
    idList.add(Integer.valueOf(50));
    idList.add(Integer.valueOf(51));
    idList.add(Integer.valueOf(53));
    idList.add(Integer.valueOf(55));
    idList.add(Integer.valueOf(59));
    idList.add(Integer.valueOf(63));
    idList.add(Integer.valueOf(44));
    idList.add(Integer.valueOf(64));
    idList.add(Integer.valueOf(65));
    idList.add(Integer.valueOf(66));
    idList.add(Integer.valueOf(67));
    idList.add(Integer.valueOf(68));
    idList.add(Integer.valueOf(69));
    idList.add(Integer.valueOf(70));
    idList.add(Integer.valueOf(71));
    idList.add(Integer.valueOf(72));
    idList.add(Integer.valueOf(75));
    idList.add(Integer.valueOf(76));
    idList.add(Integer.valueOf(77));
    idList.add(Integer.valueOf(78));
    idList.add(Integer.valueOf(81));
    idList.add(Integer.valueOf(83));
    idList.add(Integer.valueOf(85));
    idList.add(Integer.valueOf(90));
    idList.add(Integer.valueOf(92));
    idList.add(Integer.valueOf(93));
    idList.add(Integer.valueOf(94));
    idList.add(Integer.valueOf(96));
    idList.add(Integer.valueOf(101));
    idList.add(Integer.valueOf(102));
    idList.add(Integer.valueOf(104));
    idList.add(Integer.valueOf(105));
    idList.add(Integer.valueOf(106));
    idList.add(Integer.valueOf(107));
    idList.add(Integer.valueOf(108));
    idList.add(Integer.valueOf(109));
    idList.add(Integer.valueOf(113));
    idList.add(Integer.valueOf(114));
    idList.add(Integer.valueOf(115));
    idList.add(Integer.valueOf(44));
    idList.add(Integer.valueOf(117));
    idList.add(Integer.valueOf(119));
    idList.add(Integer.valueOf(124));
    idList.add(Integer.valueOf(126));
    idList.add(Integer.valueOf(127));
    idList.add(Integer.valueOf(128));
    idList.add(Integer.valueOf(131));
    idList.add(Integer.valueOf(132));
  }

  public void run() {
    for (Player player : Bukkit.getOnlinePlayers())
    {
      if (this.plugin.lumos.containsKey(player.getName())) {
        if (((Long)this.plugin.lumos.get(player.getName())).longValue() > System.currentTimeMillis()) {
          if (lumosBlock.containsKey(player.getName())) {
            this.block = ((Block)lumosBlock.get(player.getName()));
            player.sendBlockChange(this.block.getLocation(), this.block.getTypeId(), this.block.getData());
          }
          this.block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
          if (idList.contains(Integer.valueOf(this.block.getTypeId()))) {
            lumosBlock.remove(player.getName());
          } else {
            player.sendBlockChange(this.block.getLocation(), Material.GLOWSTONE, (byte)0);
            lumosBlock.put(player.getName(), this.block);
          }
        } else {
          this.plugin.lumos.remove(player.getName());
          if (lumosBlock.containsKey(player.getName())) {
            player.sendBlockChange(((Block)lumosBlock.get(player.getName())).getLocation(), ((Block)lumosBlock.get(player.getName())).getTypeId(), ((Block)lumosBlock.get(player.getName())).getData());
            lumosBlock.remove(player.getName());
          }
        }
      }

      if ((this.plugin.frozen.containsKey(player.getName())) && 
        (((Long)this.plugin.frozen.get(player.getName())).longValue() < System.currentTimeMillis())) {
        this.plugin.frozen.remove(player.getName());
      }

      if ((this.plugin.protago.containsKey(player.getName())) && 
        (((Long)this.plugin.protago.get(player.getName())).longValue() < System.currentTimeMillis())) {
        this.plugin.protago.remove(player.getName());
      }

      if (this.plugin.patronus.containsKey(player.getName())) {
        boolean remove = false;
        for (Iterator<?> localIterator1 = ((Set<?>)this.plugin.patronus.get(player.getName())).iterator(); localIterator1.hasNext(); ) { Object obj = localIterator1.next();
          if ((obj instanceof Long)) {
            long lng = ((Long)obj).longValue();
            if (lng < System.currentTimeMillis()) {
              for (Iterator<?> localIterator2 = ((Set<?>)this.plugin.patronus.get(player.getName())).iterator(); localIterator2.hasNext(); ) { Object object = localIterator2.next();
                if ((object instanceof Entity)) {
                  Entity ent = (Entity)object;
                  ent.remove();
                }
              }
              remove = true;
            }
          }
        }
        if (remove) {
          this.plugin.patronus.remove(player.getName());
        }
      }

      if ((this.plugin.flying.containsKey(player.getName())) && 
        (((Long)this.plugin.flying.get(player.getName())).longValue() < System.currentTimeMillis())) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
          player.setAllowFlight(false);
        }
        this.plugin.flying.remove(player.getName());
      }

      if ((this.plugin.stupefy.containsKey(player.getName())) && 
        (((Long)this.plugin.stupefy.get(player.getName())).longValue() < System.currentTimeMillis()))
        this.plugin.stupefy.remove(player.getName());
    }
  }
}