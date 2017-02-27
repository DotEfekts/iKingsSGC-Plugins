package net.dotefekts.ikingsspells.spells;

import net.dotefekts.ikingsspells.Caster;

public abstract class Curses extends Caster
{
  public static void putSpells()
  {
    Caster.spellsList.put("crucio", new Crucio());
    Caster.spellsList.put("avada kadavra", new AvadaKadavra());
    Caster.spellsList.put("conjunctivitis", new Conjunctivitis());
  }
}