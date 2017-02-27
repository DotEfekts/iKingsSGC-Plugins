package net.dotefekts.ikingshoneydukes;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class TimedEvents
  implements Runnable
{
  iKingsHoneydukes plugin = null;
  FileConfiguration file = null;
  Random rand = new Random(System.currentTimeMillis());
  Item item = null;
  SpoutItemStack keyS = null;

  public void storeInstance(iKingsHoneydukes quidditch) {
    this.plugin = quidditch;
    this.file = this.plugin.getConfig();
  }

  public void run() {
    for (World world : Bukkit.getWorlds())
      for (Entity ent : world.getEntities()) {
        if ((this.rand.nextInt(5) != 2) || 
          (!ent.getType().equals(EntityType.DROPPED_ITEM))) continue;
        this.item = ((Item)ent);
        this.keyS = new SpoutItemStack(this.plugin.items.frog, 1);
        if (this.item.getItemStack().getType() == Material.FLINT && this.item.getItemStack().getDurability() == this.keyS.getDurability())
          ent.setVelocity(randomVec(ent.getLocation()));
      }
  }

  public Vector randomVec(Location loc)
  {
    Vector target = new Vector(loc.getBlockX() + (this.rand.nextInt(4) - 1), loc.getBlockY() + (this.rand.nextInt(2) + 1), loc.getBlockZ() + (this.rand.nextInt(4) - 1));

    Vector vec = target.clone().subtract(new Vector(loc.getX(), loc.getY(), loc.getZ()));

    vec.multiply(0.5D / vec.length());

    return vec;
  }
}