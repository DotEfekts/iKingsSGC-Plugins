package net.dotefekts.ikingsspells.spells;

import com.earth2me.essentials.Essentials;
import net.dotefekts.ikingsspells.Caster;
import net.dotefekts.ikingsspells.Wand;
import net.dotefekts.ikingsspells.iKingsSpells;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class Extra
{
  static iKingsSpells plugin = null;
  static Wand wand = null;
  Essentials essen = (Essentials)Bukkit.getServer().getPluginManager().getPlugin("Essentials");

  public abstract boolean cast(Player paramPlayer, String[] paramArrayOfString);

  public static void putSpells(iKingsSpells o) { Caster.extraSpells.put("accio", new Accio());
    Caster.extraSpells.put("fidelius", new Fidelius());
    Caster.extraSpells.put("apparate", new Apparate());
    plugin = o;
    wand = o.wand;
  }
}