package net.dotefekts.ikingshoneydukes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.dotefekts.ikingshoneydukes.items.CustomItem;
import net.dotefekts.ikingshoneydukes.items.Food;
import net.minecraft.server.v1_5_R3.EntityItem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class Items
{
	iKingsHoneydukes plugin = null;

	public Food frog = null;

	public CustomItem frogBox = null;
	public CustomItem frogBoxEmpty = null;

	public CustomItem beanBox = null;
	public CustomItem beanBoxEmpty = null;

	public CustomItem taffyBox = null;
	public CustomItem taffyBoxEmpty = null;

	public Food acidPop = null;
	
	public Food liquoriceWand = null;
	public Food sugarQuill = null;
	public Food rockCake = null;
	public Food treacleFudge = null;

	public CustomItem fruitballContainer = null;
	public CustomItem sherbertLemonContainer = null;
	public CustomItem uNoPooContainer = null;
	public CustomItem cinnamonBallContainer = null;
	public CustomItem peppermintStrawContainer = null;
	public CustomItem containerEmpty = null;

	public Food uNoPoo = null;
	public Food cinnamonBall = null;
	public Food peppermintStraw = null;
	public Food sherbertLemon = null;

	public Food pinkCoconutIce = null;

	public Food blueberryFruitball = null;
	public Food bubblegumFruitball = null;
	public Food cherryFruitball = null;
	public Food lemonFruitball = null;
	public Food limeFruitball = null;
	public Food orangeFruitball = null;
	public Food plumFruitball = null;

	Set<Food> fruitballSet = new HashSet<Food>();

	public Food bananaBean = null;
	public Food blackpepperBean = null;
	public Food blueberryBean = null;
	public Food bogeysBean = null;
	public Food candyflossBean = null;
	public Food cherryBean = null;
	public Food cinnamonBean = null;
	public Food dirtBean = null;
	public Food earthwormBean = null;
	public Food earwaxBean = null;
	public Food grassBean = null;
	public Food greenappleBean = null;
	public Food marshmellowBean = null;
	public Food rotteneggBean = null;
	public Food sausageBean = null;
	public Food sherbertlemonBean = null;
	public Food soapBean = null;
	public Food tutifruitiBean = null;
	public Food vomitBean = null;
	public Food watermelonBean = null;

	Set<Food> beanSet = new HashSet<Food>();

	public Food blueberryTaffy = null;
	public Food clumbleberryTaffy = null;
	public Food grapeTaffy = null;
	public Food greenAppleTaffy = null;

	Set<Food> taffySet = new HashSet<Food>();

	public CustomItem pinkIceContainer = null;
	public CustomItem pinkIceContainerEmpty = null;

	public Food eyeball = null;

	public CustomItem polyjuiceFlask = null;
	public CustomItem emptyPolyjuiceFlask = null;
	
	public CustomItem pumpkinJuice = null;
	public CustomItem batBloodSoup = null;
	public CustomItem emptyFlask = null;

	public CustomItem chocBallContainer = null;
	public CustomItem chocBallContainerEmpty = null;
	public Food chocBall = null;
	
	public Items(iKingsHoneydukes plugin)
	{
		this.plugin = plugin;
	}

	public void initilizeItems()
	{
		this.frog = new Food(this.plugin, "Honeydukes Chocolate Frog", "frog/frog.png", 2);

		this.polyjuiceFlask = new CustomItem(this.plugin, "Novelty Polyjuice Potion", "polyjuice/pollyjuice.png");
		this.emptyPolyjuiceFlask = new CustomItem(this.plugin, "Empty Novelty Polyjuice Flask", "polyjuice/potionflaskemp.png");
		
		this.pumpkinJuice = new CustomItem(this.plugin, "Pumpkin Juice", "pumpkinjuice/pumpkinjuice.png");
		this.batBloodSoup = new CustomItem(this.plugin, "Bat Blood Soup", "batbloodsoup/batbloodsoup.png");
		this.emptyFlask = new CustomItem(this.plugin, "Empty Flask", "pumpkinjuice/bevbottlemp.png");

		this.frogBox = new CustomItem(this.plugin, "Honeydukes Chocolate Frogs", "frog/frogbox.png");
		this.frogBoxEmpty = new CustomItem(this.plugin, "Empty Frog Box", "frog/frogbox.png");

		this.beanBox = new CustomItem(this.plugin, "Bertie Botts Every Flavor Beans", "beans/beansbox.png");
		this.beanBoxEmpty = new CustomItem(this.plugin, "Empty Bean Box", "beans/beanboxemp.png");

		this.taffyBox = new CustomItem(this.plugin, "HoneyDukes Salt Water Taffy", "saltwatertaffy/taffybox.png");
		this.taffyBoxEmpty = new CustomItem(this.plugin, "Empty Taffy Box", "saltwatertaffy/empty/saltwatertaffyebox.png");

		this.eyeball = new Food(this.plugin, "Eyeball Bananza", "eyeball.png", 2);
		this.liquoriceWand = new Food(this.plugin, "Liquorice Wand", "liquoricewand.png", 8);
		this.rockCake = new Food(this.plugin, "Rock Cake", "rockcake.png", 2);
		this.sugarQuill = new Food(this.plugin, "Sugar Quill", "sugarquill.png", 4);
		this.treacleFudge = new Food(this.plugin, "Treacle Fudge", "treaclefudge.png", 1);

		this.acidPop = new Food(this.plugin, "Acid Pop", "acidpop.png", 4);

		this.fruitballContainer = new CustomItem(this.plugin, "Fruitball Container", "fruitballs/fruitballscontainer.png");
		this.sherbertLemonContainer = new CustomItem(this.plugin, "Sherbert Lemon Container", "sherbertlemon/sherbertlemoncontainer.png");
		this.uNoPooContainer = new CustomItem(this.plugin, "Zonko's U-No-Poo Container", "unopoo/unopoocontainer.png");
		this.cinnamonBallContainer = new CustomItem(this.plugin, "Cinnamon Balls Container", "cinnamonballs/cinnamonballcontainer.png");
		this.peppermintStrawContainer = new CustomItem(this.plugin, "Peppermint Straw Container", "peppermintstraws/peppermintstrawcontainer.png");
		this.containerEmpty = new CustomItem(this.plugin, "Empty Container", "containeremp.png");

		this.uNoPoo = new Food(this.plugin, "Zonko's U-No-Poo", "unopoo/unopoo.png", 1);
		this.sherbertLemon = new Food(this.plugin, "Sherbert Lemon", "sherbertlemon/sherbertlemon.png", 1);
		this.cinnamonBall = new Food(this.plugin, "Cinnamon Ball", "cinnamonballs/cinnamonball.png", 1);
		this.peppermintStraw = new Food(this.plugin, "Peppermint Straw", "peppermintstraws/peppermintstraw.png", 2);

		this.pinkCoconutIce = new Food(this.plugin, "Pink Coconut Ice", "pinkcoconutice/pinkcoconutice.png", 1);
		this.pinkIceContainer = new CustomItem(this.plugin, "Pink Coconut Ice Container", "pinkcoconutice/pinkcoconuticecontainer.png");
		this.pinkIceContainerEmpty = new CustomItem(this.plugin, "Empty Tall Container", "pinkcoconutice/containerempp.png");
		
		this.chocBall = new Food(this.plugin, "Choco Ball", "chocballs/chocball.png", 2);
		this.chocBallContainer = new CustomItem(this.plugin, "Choco Ball Container", "chocballs/chocballcontainer.png");
		this.chocBallContainerEmpty = new CustomItem(this.plugin, "Empty Choco Ball Container", "chocballs/chocballcontaineremp.png");

		this.fruitballSet.add(this.blueberryFruitball = new Food(this.plugin, "Blueberry Fruitball", "fruitballs/blueberryf.png", 1));
		this.fruitballSet.add(this.bubblegumFruitball = new Food(this.plugin, "Bubblegum Fruitball", "fruitballs/bubblegumf.png", 1));
		this.fruitballSet.add(this.cherryFruitball = new Food(this.plugin, "Cherry Fruitball", "fruitballs/cherryf.png", 1));
		this.fruitballSet.add(this.lemonFruitball = new Food(this.plugin, "Lemon Fruitball", "fruitballs/lemonf.png", 1));
		this.fruitballSet.add(this.limeFruitball = new Food(this.plugin, "Lime Fruitball", "fruitballs/limef.png", 1));
		this.fruitballSet.add(this.orangeFruitball = new Food(this.plugin, "Orange Fruitball", "fruitballs/orangef.png", 1));
		this.fruitballSet.add(this.plumFruitball = new Food(this.plugin, "Plum Fruitball", "fruitballs/plumf.png", 1));

		this.taffySet.add(this.blueberryTaffy = new Food(this.plugin, "Blueberry Taffy", "saltwatertaffy/blueberryt.png", 2));
		this.taffySet.add(this.clumbleberryTaffy = new Food(this.plugin, "Clumbleberry Taffy", "saltwatertaffy/clumbleberryt.png", 2));
		this.taffySet.add(this.grapeTaffy = new Food(this.plugin, "Grape Taffy", "saltwatertaffy/grapet.png", 2));
		this.taffySet.add(this.greenAppleTaffy = new Food(this.plugin, "Green Apple Taffy", "saltwatertaffy/greenapplet.png", 2));

		this.beanSet.add(this.bananaBean = new Food(this.plugin, "Banana Bean", "beans/bananab.png", 1));
		this.beanSet.add(this.blackpepperBean = new Food(this.plugin, "Black Pepper Bean", "beans/blackpepperb.png", 1));
		this.beanSet.add(this.blueberryBean = new Food(this.plugin, "Blueberry Bean", "beans/blueberryb.png", 1));
		this.beanSet.add(this.bogeysBean = new Food(this.plugin, "Boogies Bean", "beans/boogiesb.png", 1));
		this.beanSet.add(this.candyflossBean = new Food(this.plugin, "Candyfloss Bean", "beans/candyflossb.png", 1));
		this.beanSet.add(this.cherryBean = new Food(this.plugin, "Cherry Bean", "beans/cherryb.png", 1));
		this.beanSet.add(this.cinnamonBean = new Food(this.plugin, "Cinnamon Bean", "beans/cinnamonb.png", 1));
		this.beanSet.add(this.dirtBean = new Food(this.plugin, "Dirt Bean", "beans/dirtb.png", 1));
		this.beanSet.add(this.earthwormBean = new Food(this.plugin, "Earthworm Bean", "beans/earthwormb.png", 1));
		this.beanSet.add(this.earwaxBean = new Food(this.plugin, "Earwax Bean", "beans/earwaxb.png", 1));
		this.beanSet.add(this.grassBean = new Food(this.plugin, "Grass Bean", "beans/grassb.png", 1));
		this.beanSet.add(this.greenappleBean = new Food(this.plugin, "Green Apple Bean", "beans/greenappleb.png", 1));
		this.beanSet.add(this.marshmellowBean = new Food(this.plugin, "Marshmellow Bean", "beans/marshmellowb.png", 1));
		this.beanSet.add(this.rotteneggBean = new Food(this.plugin, "Rotten Egg Bean", "beans/rotteneggb.png", 1));
		this.beanSet.add(this.sausageBean = new Food(this.plugin, "Sausage Bean", "beans/sausageb.png", 1));
		this.beanSet.add(this.sherbertlemonBean = new Food(this.plugin, "Sherbert Lemon Bean", "beans/sherbertlemonb.png", 1));
		this.beanSet.add(this.soapBean = new Food(this.plugin, "Soap Bean", "beans/soapb.png", 1));
		this.beanSet.add(this.tutifruitiBean = new Food(this.plugin, "Tuti Fruiti Bean", "beans/tutifruitib.png", 1));
		this.beanSet.add(this.vomitBean = new Food(this.plugin, "Vomit Bean", "beans/vomitb.png", 1));
		this.beanSet.add(this.watermelonBean = new Food(this.plugin, "Watermelon Bean", "beans/watermelonb.png", 1));
	}

	public boolean isItem(ItemStack itemInHand)
	{
		for (CustomItem item : this.plugin.itemList) {
			if (itemInHand.getType() == Material.FLINT && itemInHand.getDurability() == item.getCustomId()) {
				return true;
			}
		}
		return false;
	}

	public void giveContents(Player player) {
		ItemStack stack = player.getItemInHand();
		Object itemstack;
		if (stack.getType() == Material.FLINT && stack.getDurability() == fruitballContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.containerEmpty));

			for (Food fruitball : this.fruitballSet)
				player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(fruitball));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == sherbertLemonContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.containerEmpty));
			
			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.sherbertLemon, 7));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == cinnamonBallContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.containerEmpty));
			
			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.cinnamonBall, 7));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == chocBallContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.chocBallContainerEmpty));
			
			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.chocBall, 10));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == uNoPooContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.containerEmpty));

			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.uNoPoo, 9));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == peppermintStrawContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.containerEmpty));

			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.peppermintStraw, 7));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == frogBox.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.frogBoxEmpty));

			Location location = player.getLocation();
			final EntityItem e = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ()) {
			    @Override
			    public boolean a(EntityItem entityitem) {
			        // DO NOT merge items, we want to keep this exact instance.
			        return false;
			    }
			};
			((CraftWorld) location.getWorld()).getHandle().addEntity(e);
			Vector vec = player.getLocation().getDirection().multiply(2);
			e.getBukkitEntity().setVelocity(vec);
			Item i = (Item) e.getBukkitEntity();
			i.setItemStack(new SpoutItemStack(this.frog));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == beanBox.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.beanBoxEmpty));

			for (itemstack = this.beanSet.iterator(); ((Iterator<?>)itemstack).hasNext(); ) { Food bean = (Food)((Iterator<?>)itemstack).next();
			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(bean)); }
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == taffyBox.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.taffyBoxEmpty));

			for (itemstack = this.taffySet.iterator(); ((Iterator<?>)itemstack).hasNext(); ) { Food taffy = (Food)((Iterator<?>)itemstack).next();
				player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(taffy)); }
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == pinkIceContainer.getCustomId()) {
			player.setItemInHand(new SpoutItemStack(this.pinkIceContainerEmpty));
			player.getWorld().dropItemNaturally(player.getLocation(), new SpoutItemStack(this.pinkCoconutIce, 5));
		} else if (stack.getType() == Material.FLINT && stack.getDurability() == polyjuiceFlask.getCustomId()) {
			if (player.getFoodLevel() != 20) {
			player.setItemInHand(new SpoutItemStack(this.emptyPolyjuiceFlask));
			 player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
				if (player.getFoodLevel() > 12)
					player.setFoodLevel(20);
				else
					player.setFoodLevel(player.getFoodLevel() + 8);
			}
		} else if ((stack.getType() == Material.FLINT && stack.getDurability() == pumpkinJuice.getCustomId()) && 
			(player.getFoodLevel() != 20)) {
			player.setItemInHand(new SpoutItemStack(this.emptyFlask));
			if (player.getFoodLevel() > 14)
				player.setFoodLevel(20);
			else
				player.setFoodLevel(player.getFoodLevel() + 6);
		} else if ((stack.getType() == Material.FLINT && stack.getDurability() == batBloodSoup.getCustomId()) && (player.getFoodLevel() != 20)) {
			player.setItemInHand(new SpoutItemStack(this.emptyFlask));
			if (player.getFoodLevel() > 16)
				player.setFoodLevel(20);
			else
				player.setFoodLevel(player.getFoodLevel() + 4);
		}
	}

	public boolean isFood(ItemStack itemInHand)
	{
		for (Food food : this.plugin.foodList) {
			if (itemInHand.getType() == Material.FLINT && itemInHand.getDurability() == food.getCustomId()) {
				return true;
			}
		}
		return false;
	}

	public void nourish(Player player) {
		ItemStack itemInHand = player.getItemInHand();
		for (Food food : this.plugin.foodList)
			if (itemInHand.getType() == Material.FLINT && itemInHand.getDurability() == food.getCustomId()) {
				if(food.equals(liquoriceWand)){
					player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
				}
				if (player.getFoodLevel() > 20 - food.getHungerRestored())
					player.setFoodLevel(20);
				else {
					player.setFoodLevel(player.getFoodLevel() + food.getHungerRestored());
				}
				if (player.getItemInHand().getAmount() == 1)
					player.setItemInHand(new ItemStack(0));
				else {
					player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
				}
				return;
			}
	}
}