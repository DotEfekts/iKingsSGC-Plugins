package net.dotefekts.ikingsspells;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class Wand extends GenericCustomItem
{
	private static int durability = 0;
	
	public Wand(iKingsSpells plugin)
	{
		super(plugin, "Wand", "https://dl.dropboxusercontent.com/u/46463035/HcyHdyhexRwidodg364/ikingsspells/wand.png");    
		Wand.durability = this.getCustomId();
		setStackable(false);
	}
  
	public static boolean isWand(ItemStack stack) {
		if(stack.getType() == Material.FLINT) {
			if(stack.getDurability() == durability){
				return true;
			} else return false;
		} else return false;
	}
}