package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day21 extends Solution<Integer> {
  private static final int MAX_RING = 2;

  private static final List<Weapon> WEAPONS = List.of(
    new Weapon("Dagger", 8, 4, 0),
    new Weapon("Shortsword", 10, 5, 0),
    new Weapon("Warhammer", 25, 6, 0),
    new Weapon("Longsword", 40, 7, 0),
    new Weapon("Greataxe", 74, 8, 0)
  );

  private static final List<Armor> ARMORS = List.of(
    new Armor("Leather", 13, 0, 1),
    new Armor("Chainmail", 31, 0, 2),
    new Armor("Splintmail", 53, 0, 3),
    new Armor("Bandedmail", 75, 0, 4),
    new Armor("Platemail", 102, 0, 5)
  );

  private static final List<Ring> RINGS = List.of(
    new Ring("Damage +1", 25, 1, 0),
    new Ring("Damage +2", 50, 2, 0),
    new Ring("Damage +3", 100, 3, 0),
    new Ring("Defense +1", 20, 0, 1),
    new Ring("Defense +2", 40, 0, 2),
    new Ring("Defense +3", 80, 0, 3)
  );

  private final Boss boss;

  private int minCost = Integer.MAX_VALUE;

  private int maxCost = Integer.MIN_VALUE;

  public Day21(List<String> data) {
    boss = parseData(data);
  }

  public static void main(String[] args) throws IOException {
    Day21 d = new Day21(FileUtil.readFile(2015, 21));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  private Boss parseData(List<String> data) {
    int hp = 0;
    int damage = 0;
    int armor = 0;
    for (String line : data) {
      String[] parts = line.split(": ");
      switch (parts[0]) {
        case "Hit Points" -> hp = Integer.parseInt(parts[1]);
        case "Damage" -> damage = Integer.parseInt(parts[1]);
        case "Armor" -> armor = Integer.parseInt(parts[1]);
        default -> throw new IllegalArgumentException("Invalid line: " + line);
      }
    }

    return new Boss(hp, damage, armor);
  }

  @Override
  public Integer solvePart1() {
    for (Weapon w : WEAPONS)
      decideArmor(w);

    return minCost;
  }

  @Override
  public Integer solvePart2() {
    return maxCost;
  }

  private void decideArmor(Weapon weapon) {
    for (int i = -1; i < ARMORS.size(); i++) {
      Armor a = i == -1 ? null : ARMORS.get(i);
      decideRing1(weapon, a);
    }
  }

  private void decideRing1(Weapon weapon, Armor armor) {
    for (int i = -1; i < RINGS.size(); i++) {
      Ring r1 = i == -1 ? null : RINGS.get(i);
      decideRing2(weapon, armor, i, r1);
    }
  }

  private void decideRing2(Weapon weapon, Armor armor, int ring1Index, Ring ring1) {
    for (int i = ring1Index; i < RINGS.size(); i++) {
      Ring r2 = i == ring1Index ? null : RINGS.get(i);
      if (ring1 != null && ring1.equals(r2))
        continue;
      fight(weapon, armor, ring1, r2);
    }
  }

  private void fight(Weapon weapon, Armor armor, Ring ring1, Ring ring2) {
    Player player = new Player(100);
    player.equip(weapon);
    if (armor != null)
      player.equip(armor);
    if (ring1 != null)
      player.equip(ring1);
    if (ring2 != null)
      player.equip(ring2);

    if (playerWins(player, new Boss(boss.hp, boss.damage, boss.defense))) {
      minCost = Math.min(minCost, player.getCost());
    } else {
      maxCost = Math.max(maxCost, player.getCost());
    }
  }

  private boolean playerWins(GameCharacter player, GameCharacter boss) {
    while (player.isAlive() && boss.isAlive()) {
      boss.onDamage(player.getDamage());
      if (!boss.isAlive())
        return true;
      player.onDamage(boss.getDamage());
    }
    return player.isAlive();
  }

  private static class GameCharacter {
    protected int hp;

    protected int damage;

    protected int defense;

    protected GameCharacter(int hp) {
      this(hp, 0, 0);
    }

    protected GameCharacter(int hp, int damage, int defense) {
      this.hp = hp;
      this.damage = damage;
      this.defense = defense;
    }

    protected void setDamage(int damage) {
      this.damage += damage;
    }

    protected int getDamage() {
      return damage;
    }

    protected void setDefense(int defense) {
      this.defense += defense;
    }

    protected int getDefense() {
      return defense;
    }

    protected void onDamage(int damage) {
      this.hp -= Math.max(1, (damage - this.defense));
    }

    public boolean isAlive() {
      return hp > 0;
    }
  }


  private static class Boss extends GameCharacter {
    public Boss(int hp, int damage, int defense) {
      super(hp, damage, defense);
    }
  }


  private static class Player extends GameCharacter {
    private Equipment weapon;

    private Equipment armor;

    private final List<Equipment> rings = new ArrayList<>();

    public Player(int hp) {
      super(hp);
    }

    public void equip(Equipment equipment) {
      if (equipment instanceof Weapon w) {
        if (this.weapon != null)
          unequip(this.weapon);
        this.weapon = w;
        setDamage(w.getDamage());
      } else if (equipment instanceof Armor a) {
        if (this.armor != null)
          unequip(this.armor);
        this.armor = a;
        setDefense(a.getDefense());
      } else if (equipment instanceof Ring r && this.rings.size() < MAX_RING) {
        this.rings.add(r);
        setDamage(r.getDamage());
        setDefense(r.getDefense());
      }
    }

    public void unequip(Equipment equipment) {
      if (equipment instanceof Weapon) {
        setDamage(-equipment.getDamage());
        this.weapon = null;
      } else if (equipment instanceof Armor) {
        setDefense(-equipment.getDefense());
        this.armor = null;
      } else if (equipment instanceof Ring) {
        int index = rings.indexOf(equipment);
        if (index != -1) {
          setDefense(-equipment.getDefense());
          setDamage(-equipment.getDamage());
          rings.remove(index);
        }
      }
    }

    public int getCost() {
      return Optional.ofNullable(weapon).map(Equipment::getCost).orElse(0) + Optional.ofNullable(
        armor).map(Equipment::getCost).orElse(0) + rings.stream().map(Equipment::getCost)
        .reduce(0, Integer::sum);
    }
  }


  private static sealed class Equipment {
    private final String name;
    private final int cost;

    private final int damage;

    private final int defense;

    public Equipment(String name, int cost, int damage, int armor) {
      this.name = name;
      this.cost = cost;
      this.damage = damage;
      this.defense = armor;
    }

    public String getName() {
      return name;
    }

    public int getCost() {
      return cost;
    }

    public int getDamage() {
      return damage;
    }

    public int getDefense() {
      return defense;
    }

    @Override
    public String toString() {
      return "Equipment{" +
        "name='" + name + '\'' +
        ", cost=" + cost +
        '}';
    }
  }


  private static final class Weapon extends Equipment {
    public Weapon(String name, int cost, int damage, int defense) {
      super(name, cost, damage, defense);
    }
  }


  private static final class Armor extends Equipment {
    public Armor(String name, int cost, int damage, int defense) {
      super(name, cost, damage, defense);
    }
  }


  private static final class Ring extends Equipment {
    public Ring(String name, int cost, int damage, int defense) {
      super(name, cost, damage, defense);
    }
  }
}
