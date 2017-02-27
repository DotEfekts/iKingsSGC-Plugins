package net.dotefekts.ikingshoneydukes.items;

import net.dotefekts.ikingshoneydukes.iKingsHoneydukes;
import org.getspout.spoutapi.material.item.GenericCustomFood;

public class Food extends GenericCustomFood
{
  public Food(iKingsHoneydukes plugin, String name, String texture, int hungerRestored)
  {
    super(plugin, name, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/" + texture, hungerRestored);
    plugin.foodList.add(this);
  }
}