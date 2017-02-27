package net.dotefekts.ikingshoneydukes;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.dotefekts.ikingshoneydukes.items.CustomItem;
import net.dotefekts.ikingshoneydukes.items.Food;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;

public class iKingsHoneydukes extends JavaPlugin
{
  Logger log = Logger.getLogger("Minecraft");
  ListenerMethods listener = new ListenerMethods();
  TimedEvents runnable = new TimedEvents();
  Items items = new Items(this);
  public Set<Food> foodList = new HashSet<Food>();
  public Set<CustomItem> itemList = new HashSet<CustomItem>();

  public void onEnable() {
    if (!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
      Logger.getLogger("Minecraft").warning("[iKingsHoneydukes] PermissionsEx plugin wasn't found. Disabling plugin.");
      Bukkit.getServer().getPluginManager().disablePlugin(this);
    }

    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/acidpop.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/bananab.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/blackpepperb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/blueberryb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/boogiesb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/beansbox.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/beanboxemp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/candyflossb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/cherryb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/cinnamonb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/dirtb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/earthwormb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/earwaxb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/grassb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/greenappleb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/marshmellowb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/rotteneggb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/sausageb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/sherbertlemonb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/soapb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/tutifruitib.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/vomitb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/beans/watermelonb.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/containeremp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/eyeball.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/frog/frogbox.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/frog/frog.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/blueberryf.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/bubblegumf.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/cherryf.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/fruitballscontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/lemonf.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/limef.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/orangef.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/fruitballs/plumf.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/pinkcoconutice/pinkcoconuticecontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/cinnamonballs/cinnamonball.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/cinnamonballs/cinnamonballcontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/pinkcoconutice/containerempp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/pinkcoconutice/pinkcoconutice.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/polyjuice/pollyjuice.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/polyjuice/potionflaskemp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/pumpkinjuice/bevbottlemp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/batbloodsoup/batbloodsoup.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/pumpkinjuice/pumpkinjuice.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/blueberryt.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/taffybox.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/clumbleberryt.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/empty/saltwatertaffyebox.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/grapet.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/saltwatertaffy/greenapplet.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/sherbertlemon/sherbertlemoncontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/sherbertlemon/sherbertlemon.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/unopoo/unopoocontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/unopoo/unopoo.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/chocballs/chocball.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/chocballs/chocballcontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/chocballs/chocballcontaineremp.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/peppermintstraws/peppermintstraw.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/peppermintstraws/peppermintstrawcontainer.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/rockcake.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/liquoricewand.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/sugarquill.png");
    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropbox.com/u/46463035/HcyHdyhexRwidodg364/ikingshoneydukes/treaclefudge.png");
    
    this.items.initilizeItems();
    this.listener.storeInstance(this);
    this.runnable.storeInstance(this);
    getServer().getPluginManager().registerEvents(this.listener, this);
    getServer().getScheduler().scheduleSyncRepeatingTask(this, this.runnable, 0L, 5L);
    Logger.getLogger("Minecraft").info("[iKingsHoneydukes] Enabled!");
  }

  public void onDisable() {
    this.log.info("[iKingsHoneydukes] Disabling.");
  }

  public FileConfiguration getConfigFile() {
    FileConfiguration files = getConfig();
    return files;
  }
}