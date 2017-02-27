package net.dotefekts.ikingsspells;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericItemWidget;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class iKingsSpells extends JavaPlugin
{
  public Logger log = Logger.getLogger("Minecraft");
  ListenerMethods listener = new ListenerMethods();
  public Map<String, Long> lumos = new HashMap<String, Long>();
  public Map<String, Long> frozen = new HashMap<String, Long>();
  public Map<String, Long> protago = new HashMap<String, Long>();
  public Map<String, Long> flying = new HashMap<String, Long>();
  public Map<String, Long> stupefy = new HashMap<String, Long>();
  public Map<String, Set<Object>> patronus = new HashMap<String, Set<Object>>();
  Set<String> list = new HashSet<String>();
  public LWC lwc = null;
  public Wand wand = null;
  public Map<SpoutPlayer, GenericPopup> playerPopup = new HashMap<SpoutPlayer, GenericPopup>();
  public FileConfiguration customConfig = null;
  private File customConfigurationFile = null;

  public void onEnable()
  {
    list.add("avis");
    list.add("prior incantato");
    list.add("ennervate");
    list.add("finite incantatum");
    list.add("incendio");
    list.add("lumos");
    list.add("nox");
    list.add("orchideous");
    list.add("relashio");
    list.add("repairo");
    list.add("stupefy");
    list.add("accio");
    list.add("fidelius");
    list.add("crucio");
    list.add("avada kadavra");
    list.add("conjunctivitis");
    list.add("episkey");
    list.add("allohomora");
    list.add("aguamenti");
    list.add("avifors");
    list.add("expecto patronum");
    list.add("expelliarmus");
    list.add("homorphus");
    list.add("immobulus");
    list.add("impedimenta");
    list.add("obliviate");
    list.add("petrificus totalus");
    list.add("protego");
    list.add("scruge");
    list.add("wingardium leviosa");

    SpoutManager.getFileManager().addToPreLoginCache(this, "https://dl.dropboxusercontent.com/u/46463035/HcyHdyhexRwidodg364/ikingsspells/wand.png");
    wand = new Wand(this);
    getCustomConfig();
    FileConfiguration file = getConfig();
    if (!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
      log.warning("[iKingsSpells] PermissionsEx plugin wasn't found. Disabling plugin.");
      Bukkit.getServer().getPluginManager().disablePlugin(this);
    }
    Plugin lwcPlugin = getServer().getPluginManager().getPlugin("LWC");
    if (lwcPlugin != null)
      lwc = ((LWCPlugin)lwcPlugin).getLWC();
    else {
      log.warning("[iKingsSpells] LWC plugin wasn't found. Chests won't have protection.");
    }
    if ((!file.getKeys(true).contains("ResetConfig")) || (file.getBoolean("ResetConfig"))) {
      File configdel = new File(getDataFolder(), "config.yml");
      configdel.delete();
      reloadConfig();
      file = getConfig();
      file.set("ResetConfig", Boolean.valueOf(false));
      file.set("Enabled", Boolean.valueOf(true));
      file.set("HearDistance", Integer.valueOf(50));
      file.set("SpellRange", Integer.valueOf(50));
      for (String str : list) {
        file.set("Spells." + str + ".Cost", Integer.valueOf(1));
        file.set("Spells." + str + ".Cooldown", Integer.valueOf(10));
      }
      saveConfig();
      log.info("[iKingsSpells] Configuration file reset.");
    }
    if (file.contains("Enabled")) {
      if (!file.getBoolean("Enabled")) {
        log.warning("[iKingsSpells] Configuration set to disable plugin.");
        log.warning("[iKingsSpells] Disabling.");
        Bukkit.getServer().getPluginManager().disablePlugin(this);
      }
    }
    else file.set("Enabled", Boolean.valueOf(true));

    listener.storeInstance(this);
    Caster.storeInstance(this);
    TimedEvents runnable = new TimedEvents();
    runnable.storeInstance(this);
    getServer().getScheduler().scheduleSyncRepeatingTask(this, runnable, 20L, 20L);
    getServer().getPluginManager().registerEvents(this.listener, this);
    log.info("[iKingsSpells] Enabled!");
  }

  public void onDisable() {
    log.info("[iKingsSpells] Disabling.");
    for(Object ob : patronus.values()) {
    	if(ob instanceof Entity)
    		((Entity)ob).remove();
    }
    saveConfig();
    saveCustomConfig();
  }

  public void reloadCustomConfig()
  {
    if (customConfig == null) {
      customConfigurationFile = new File(getDataFolder(), "players.yml");
    }
    customConfig = YamlConfiguration.loadConfiguration(customConfigurationFile);

    if (!customConfigurationFile.exists()) {
      System.out.println("[iKingsSpells] Player file generated.");
      customConfig.set("ratchet11110.LastSpell", "None");
      customConfig.set("ratchet11110.ToPerform", "episkey");
      saveCustomConfig();
    }
  }

  public FileConfiguration getCustomConfig() {
    if (customConfig == null) {
      reloadCustomConfig();
    }
    return customConfig;
  }

  public void saveCustomConfig() {
    if ((customConfig == null) || (customConfigurationFile == null))
      return;
    try
    {
      customConfig.save(this.customConfigurationFile);
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Could not save config to " + this.customConfig, ex);
    }
  }
  
  public void createOverlay(SpoutPlayer player)
  {
    PermissionManager permissions = PermissionsEx.getPermissionManager();

    GenericPopup popup = new GenericPopup();

    GenericButton button1 = new GenericButton("Episkey");
    button1.setAnchor(WidgetAnchor.TOP_LEFT);
    button1.setWidth(100).setHeight(20);
    button1.shiftXPos(5).shiftYPos(5);
    if (!permissions.has(player, "ikingsspells.episkey")) button1.setEnabled(false);

    GenericButton button2 = new GenericButton("Allohomora");
    button2.setAnchor(WidgetAnchor.TOP_LEFT);
    button2.setWidth(100).setHeight(20);
    button2.shiftXPos(5).shiftYPos(30);
    if (!permissions.has(player, "ikingsspells.allohomora")) button2.setEnabled(false);

    GenericButton button3 = new GenericButton("Avifors");
    button3.setAnchor(WidgetAnchor.TOP_LEFT);
    button3.setWidth(100).setHeight(20);
    button3.shiftXPos(5).shiftYPos(55);
    if (!permissions.has(player, "ikingsspells.avifors")) button3.setEnabled(false);

    GenericButton button4 = new GenericButton("Expecto Patronum");
    button4.setAnchor(WidgetAnchor.TOP_LEFT);
    button4.setWidth(100).setHeight(20);
    button4.shiftXPos(5).shiftYPos(80);
    if (!permissions.has(player, "ikingsspells.expectopatronum")) button4.setEnabled(false);

    GenericButton button5 = new GenericButton("Expelliarmus");
    button5.setAnchor(WidgetAnchor.TOP_LEFT);
    button5.setWidth(100).setHeight(20);
    button5.shiftXPos(5).shiftYPos(105);
    if (!permissions.has(player, "ikingsspells.expelliarmus")) button5.setEnabled(false);

    GenericButton button6 = new GenericButton("Homorphus");
    button6.setAnchor(WidgetAnchor.TOP_LEFT);
    button6.setWidth(100).setHeight(20);
    button6.shiftXPos(5).shiftYPos(130);
    if (!permissions.has(player, "ikingsspells.homorphus")) button6.setEnabled(false);

    GenericButton button7 = new GenericButton("Immobulus");
    button7.setAnchor(WidgetAnchor.TOP_LEFT);
    button7.setWidth(100).setHeight(20);
    button7.shiftXPos(5).shiftYPos(155);
    if (!permissions.has(player, "ikingsspells.immobulus")) button7.setEnabled(false);

    GenericButton button8 = new GenericButton("Impedimenta");
    button8.setAnchor(WidgetAnchor.TOP_LEFT);
    button8.setWidth(100).setHeight(20);
    button8.shiftXPos(5).shiftYPos(180);
    if (!permissions.has(player, "ikingsspells.impedimenta")) button8.setEnabled(false);

    GenericButton button9 = new GenericButton("Obliviate");
    button9.setAnchor(WidgetAnchor.TOP_LEFT);
    button9.setWidth(100).setHeight(20);
    button9.shiftXPos(5).shiftYPos(205);
    if (!permissions.has(player, "ikingsspells.obliviate")) button9.setEnabled(false);

    GenericButton button10 = new GenericButton("Petrificus Totalus");
    button10.setAnchor(WidgetAnchor.TOP_LEFT);
    button10.setWidth(100).setHeight(20);
    button10.shiftXPos(110).shiftYPos(5);
    if (!permissions.has(player, "ikingsspells.petrificustotalus")) button10.setEnabled(false);

    GenericButton button11 = new GenericButton("Protego");
    button11.setAnchor(WidgetAnchor.TOP_LEFT);
    button11.setWidth(100).setHeight(20);
    button11.shiftXPos(110).shiftYPos(30);
    if (!permissions.has(player, "ikingsspells.protego")) button11.setEnabled(false);

    GenericButton button12 = new GenericButton("Scruge");
    button12.setAnchor(WidgetAnchor.TOP_LEFT);
    button12.setWidth(100).setHeight(20);
    button12.shiftXPos(110).shiftYPos(55);
    if (!permissions.has(player, "ikingsspells.scruge")) button12.setEnabled(false);

    GenericButton button13 = new GenericButton("Wingardium Leviosa");
    button13.setAnchor(WidgetAnchor.TOP_LEFT);
    button13.setWidth(100).setHeight(20);
    button13.shiftXPos(110).shiftYPos(80);
    if (!permissions.has(player, "ikingsspells.wingardiumleviosa")) button13.setEnabled(false);

    GenericButton button14 = new GenericButton("Avis");
    button14.setAnchor(WidgetAnchor.TOP_LEFT);
    button14.setWidth(100).setHeight(20);
    button14.shiftXPos(110).shiftYPos(105);
    if (!permissions.has(player, "ikingsspells.avis")) button14.setEnabled(false);

    GenericButton button15 = new GenericButton("Aguamenti");
    button15.setAnchor(WidgetAnchor.TOP_LEFT);
    button15.setWidth(100).setHeight(20);
    button15.shiftXPos(110).shiftYPos(130);
    if (!permissions.has(player, "ikingsspells.aguamenti")) button15.setEnabled(false);

    GenericButton button16 = new GenericButton("Ennervate");
    button16.setAnchor(WidgetAnchor.TOP_LEFT);
    button16.setWidth(100).setHeight(20);
    button16.shiftXPos(110).shiftYPos(155);
    if (!permissions.has(player, "ikingsspells.ennervate")) button16.setEnabled(false);

    GenericButton button17 = new GenericButton("Finite Incantatum");
    button17.setAnchor(WidgetAnchor.TOP_LEFT);
    button17.setWidth(100).setHeight(20);
    button17.shiftXPos(110).shiftYPos(180);
    if (!permissions.has(player, "ikingsspells.finiteincantatum")) button17.setEnabled(false);

    GenericButton button18 = new GenericButton("Incendio");
    button18.setAnchor(WidgetAnchor.TOP_LEFT);
    button18.setWidth(100).setHeight(20);
    button18.shiftXPos(110).shiftYPos(205);
    if (!permissions.has(player, "ikingsspells.incendio")) button18.setEnabled(false);

    GenericButton button19 = new GenericButton("Lumos");
    button19.setAnchor(WidgetAnchor.TOP_LEFT);
    button19.setWidth(100).setHeight(20);
    button19.shiftXPos(215).shiftYPos(5);
    if (!permissions.has(player, "ikingsspells.lumos")) button19.setEnabled(false);

    GenericButton button20 = new GenericButton("Nox");
    button20.setAnchor(WidgetAnchor.TOP_LEFT);
    button20.setWidth(100).setHeight(20);
    button20.shiftXPos(215).shiftYPos(30);
    if (!permissions.has(player, "ikingsspells.nox")) button20.setEnabled(false);

    GenericButton button21 = new GenericButton("Orchideous");
    button21.setAnchor(WidgetAnchor.TOP_LEFT);
    button21.setWidth(100).setHeight(20);
    button21.shiftXPos(215).shiftYPos(55);
    if (!permissions.has(player, "ikingsspells.orchideous")) button21.setEnabled(false);

    GenericButton button22 = new GenericButton("Relashio");
    button22.setAnchor(WidgetAnchor.TOP_LEFT);
    button22.setWidth(100).setHeight(20);
    button22.shiftXPos(215).shiftYPos(80);
    if (!permissions.has(player, "ikingsspells.relashio")) button22.setEnabled(false);

    GenericButton button23 = new GenericButton("Repairo");
    button23.setAnchor(WidgetAnchor.TOP_LEFT);
    button23.setWidth(100).setHeight(20);
    button23.shiftXPos(215).shiftYPos(105);
    if (!permissions.has(player, "ikingsspells.repairo")) button23.setEnabled(false);

    GenericButton button24 = new GenericButton("Stupefy");
    button24.setAnchor(WidgetAnchor.TOP_LEFT);
    button24.setWidth(100).setHeight(20);
    button24.shiftXPos(215).shiftYPos(130);
    if (!permissions.has(player, "ikingsspells.stupefy")) button24.setEnabled(false);

    GenericButton button25 = new GenericButton("Crucio");
    button25.setAnchor(WidgetAnchor.TOP_LEFT);
    button25.setWidth(100).setHeight(20);
    button25.shiftXPos(215).shiftYPos(155);
    if (!permissions.has(player, "ikingsspells.crucio")) button25.setEnabled(false);

    GenericButton button26 = new GenericButton("Avada Kadavra");
    button26.setAnchor(WidgetAnchor.TOP_LEFT);
    button26.setWidth(100).setHeight(20);
    button26.shiftXPos(215).shiftYPos(180);
    if (!permissions.has(player, "ikingsspells.avadakadavra")) button26.setEnabled(false);

    GenericButton button27 = new GenericButton("Conjunctivitis");
    button27.setAnchor(WidgetAnchor.TOP_LEFT);
    button27.setWidth(100).setHeight(20);
    button27.shiftXPos(215).shiftYPos(205);
    if (!permissions.has(player, "ikingsspells.conjunctivitis")) button27.setEnabled(false);

    GenericButton infob1 = new GenericButton("Last Spell");
    infob1.setAnchor(WidgetAnchor.TOP_LEFT);
    infob1.setWidth(100).setHeight(20);
    infob1.shiftXPos(320).shiftYPos(5);
    infob1.setEnabled(false);

    GenericButton infob2 = new GenericButton("Selected Spell");
    infob2.setAnchor(WidgetAnchor.TOP_LEFT);
    infob2.setWidth(100).setHeight(20);
    infob2.shiftXPos(320).shiftYPos(55);
    infob2.setEnabled(false);

    GenericButton infob3 = new GenericButton(WordUtils.capitalize(this.customConfig.getString(player.getName() + ".LastSpell").toLowerCase()));
    infob3.setAnchor(WidgetAnchor.TOP_LEFT);
    infob3.setWidth(100).setHeight(20);
    infob3.shiftXPos(320).shiftYPos(30);
    if (infob3.getText().equalsIgnoreCase("None")) {
      infob3.setEnabled(false);
    }
    if (!permissions.has(player, "ikingsspells." + this.customConfig.getString(new StringBuilder(String.valueOf(player.getName())).append(".LastSpell").toString()).toLowerCase().replace(" ", ""))) infob3.setEnabled(false);

    GenericButton infob4 = new GenericButton(WordUtils.capitalize(this.customConfig.getString(player.getName() + ".ToPerform").toLowerCase()));
    infob4.setAnchor(WidgetAnchor.TOP_LEFT);
    infob4.setWidth(100).setHeight(20);
    infob4.shiftXPos(320).shiftYPos(80);
    if (!permissions.has(player, "ikingsspells." + this.customConfig.getString(new StringBuilder(String.valueOf(player.getName())).append(".ToPerform").toString()).toLowerCase().replace(" ", ""))) infob4.setEnabled(false);

    GenericItemWidget book = new GenericItemWidget();
    book.setAnchor(WidgetAnchor.TOP_LEFT);
    book.setTypeId(340);
    book.setWidth(100).setHeight(100);
    book.shiftXPos(320).shiftYPos(102);

    GenericButton infob5 = new GenericButton("Spell Book");
    infob5.setAnchor(WidgetAnchor.TOP_LEFT);
    infob5.setWidth(100).setHeight(20);
    infob5.shiftXPos(320).shiftYPos(205);
    infob5.setEnabled(false);

    this.playerPopup.put(player, popup);

    popup.attachWidget(this, button1);
    popup.attachWidget(this, button2);
    popup.attachWidget(this, button3);
    popup.attachWidget(this, button4);
    popup.attachWidget(this, button5);
    popup.attachWidget(this, button6);
    popup.attachWidget(this, button7);
    popup.attachWidget(this, button8);
    popup.attachWidget(this, button9);
    popup.attachWidget(this, button10);
    popup.attachWidget(this, button11);
    popup.attachWidget(this, button12);
    popup.attachWidget(this, button13);
    popup.attachWidget(this, button14);
    popup.attachWidget(this, button15);
    popup.attachWidget(this, button16);
    popup.attachWidget(this, button17);
    popup.attachWidget(this, button18);
    popup.attachWidget(this, button19);
    popup.attachWidget(this, button20);
    popup.attachWidget(this, button21);
    popup.attachWidget(this, button22);
    popup.attachWidget(this, button23);
    popup.attachWidget(this, button24);
    popup.attachWidget(this, button25);
    popup.attachWidget(this, button26);
    popup.attachWidget(this, button27);
    popup.attachWidget(this, infob1);
    popup.attachWidget(this, infob2);
    popup.attachWidget(this, infob3);
    popup.attachWidget(this, infob4);
    popup.attachWidget(this, book);
    popup.attachWidget(this, infob5);
    player.getMainScreen().attachPopupScreen(popup);
  }
}