package net.dotefekts.ikingshoneydukes.items;

import net.dotefekts.ikingshoneydukes.iKingsHoneydukes;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class CustomItem extends GenericCustomItem
{
  public CustomItem(iKingsHoneydukes plugin, String name, String texture)
  {
    super(plugin, name, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/" + texture);
    setStackable(false);
    plugin.itemList.add(this);
  }
}