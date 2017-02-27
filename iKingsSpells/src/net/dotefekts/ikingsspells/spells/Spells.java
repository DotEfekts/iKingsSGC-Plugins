package net.dotefekts.ikingsspells.spells;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import net.dotefekts.ikingsspells.Caster;
import org.bukkit.Material;

public abstract class Spells extends Caster
{
  static Set<Material> repairMats = new HashSet<Material>();
  Random gen = new Random(System.currentTimeMillis());

  public static void putSpells() {
    Caster.spellsList.put("avis", new Avis());
    Caster.spellsList.put("prior incantato", new PriorIncantato());
    Caster.spellsList.put("ennervate", new Ennervate());
    Caster.spellsList.put("finite incantatum", new FiniteIncantatum());
    Caster.spellsList.put("incendio", new Incendio());
    Caster.spellsList.put("lumos", new Lumos());
    Caster.spellsList.put("nox", new Nox());
    Caster.spellsList.put("orchideous", new Orchideous());
    Caster.spellsList.put("relashio", new Relashio());
    Caster.spellsList.put("repairo", new Repairo());

    Caster.spellsList.put("stupefy", new Stupefy());

    repairMats.add(Material.IRON_AXE);
    repairMats.add(Material.IRON_BOOTS);
    repairMats.add(Material.IRON_CHESTPLATE);
    repairMats.add(Material.IRON_HELMET);
    repairMats.add(Material.IRON_HOE);
    repairMats.add(Material.IRON_LEGGINGS);
    repairMats.add(Material.IRON_PICKAXE);
    repairMats.add(Material.IRON_SPADE);
    repairMats.add(Material.IRON_SWORD);
    repairMats.add(Material.BOW);
    repairMats.add(Material.WOOD_AXE);
    repairMats.add(Material.WOOD_HOE);
    repairMats.add(Material.WOOD_PICKAXE);
    repairMats.add(Material.WOOD_SPADE);
    repairMats.add(Material.WOOD_SWORD);
    repairMats.add(Material.LEATHER_BOOTS);
    repairMats.add(Material.LEATHER_CHESTPLATE);
    repairMats.add(Material.LEATHER_HELMET);
    repairMats.add(Material.LEATHER_LEGGINGS);
    repairMats.add(Material.STONE_AXE);
    repairMats.add(Material.STONE_HOE);
    repairMats.add(Material.STONE_PICKAXE);
    repairMats.add(Material.STONE_SPADE);
    repairMats.add(Material.STONE_SWORD);
    repairMats.add(Material.GOLD_AXE);
    repairMats.add(Material.GOLD_BOOTS);
    repairMats.add(Material.GOLD_CHESTPLATE);
    repairMats.add(Material.GOLD_HELMET);
    repairMats.add(Material.GOLD_HOE);
    repairMats.add(Material.GOLD_LEGGINGS);
    repairMats.add(Material.GOLD_PICKAXE);
    repairMats.add(Material.GOLD_SPADE);
    repairMats.add(Material.GOLD_SWORD);
    repairMats.add(Material.DIAMOND_AXE);
    repairMats.add(Material.DIAMOND_BOOTS);
    repairMats.add(Material.DIAMOND_CHESTPLATE);
    repairMats.add(Material.DIAMOND_HELMET);
    repairMats.add(Material.DIAMOND_HOE);
    repairMats.add(Material.DIAMOND_LEGGINGS);
    repairMats.add(Material.DIAMOND_PICKAXE);
    repairMats.add(Material.DIAMOND_SPADE);
    repairMats.add(Material.DIAMOND_SWORD);
  }
}