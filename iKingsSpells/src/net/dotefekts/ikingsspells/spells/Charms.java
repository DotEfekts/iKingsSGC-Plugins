package net.dotefekts.ikingsspells.spells;

import java.util.Random;
import net.dotefekts.ikingsspells.Caster;

public abstract class Charms extends Caster
{
  Random rand = new Random(System.currentTimeMillis());

  public static void putSpells() {
    Caster.spellsList.put("episkey", new Episkey());
    Caster.spellsList.put("allohomora", new Allohomora());
    Caster.spellsList.put("aguamenti", new Aguamenti());
    Caster.spellsList.put("avifors", new Avifors());
    Caster.spellsList.put("expecto patronum", new ExpectoPatronum());
    Caster.spellsList.put("expelliarmus", new Expelliarmus());
    Caster.spellsList.put("homorphus", new Homorphus());
    Caster.spellsList.put("immobulus", new Immobulus());
    Caster.spellsList.put("impedimenta", new Impedimenta());
    Caster.spellsList.put("obliviate", new Obliviate());
    Caster.spellsList.put("petrificus totalus", new PetrificusTotalus());
    Caster.spellsList.put("protego", new Protego());

    Caster.spellsList.put("scruge", new Scruge());
    Caster.spellsList.put("wingardium leviosa", new WingardiumLeviosa());
  }
}