package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day22 extends Solution<Integer> {

  private final Boss initialBoss;

  private int minManaSpent = Integer.MAX_VALUE;

  public Day22(List<String> data) {
    this.initialBoss = parseData(data);
  }

  public static void main(String[] args) throws IOException {
    Day22 d = new Day22(FileUtil.readFile(2015, 22));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    return fight(false);
  }

  @Override
  public Integer solvePart2() {
    minManaSpent = Integer.MAX_VALUE;
    return fight(true);
  }

  private Boss parseData(List<String> data) {
    int hp = 0;
    int damage = 0;
    for (String line : data) {
      String[] parts = line.split(": ");
      switch (parts[0]) {
        case "Hit Points" -> hp = Integer.parseInt(parts[1]);
        case "Damage" -> damage = Integer.parseInt(parts[1]);
        default -> throw new IllegalArgumentException("Invalid line: " + line);
      }
    }

    return new Boss(hp, damage);
  }

  private int fight(boolean isHardMode) {
    Queue<GameState> queue = new LinkedList<>();
    queue.add(new GameState(
      new Player(50, 500),
      new Boss(initialBoss.hp, initialBoss.damage),
      0, 0, 0, 0, true));

    while (!queue.isEmpty()) {
      GameState currentState = queue.poll();

      if (isHardMode && currentState.playerTurn)
        currentState.player.onDamage(1);

      if (!currentState.player.isAlive())
        continue;

      applySpecialEffects(currentState);

      if (!currentState.boss.isAlive()) {
        minManaSpent = Math.min(minManaSpent, currentState.manaSpent);
        continue;
      }

      if (!currentState.playerTurn) {
        GameState state = damageToPlayer(currentState);
        if (state != null)
          queue.add(state);
        continue;
      }

      for (Spell spell : Spell.values())
        if (canCastSpell(currentState, spell))
          queue.add(castSpell(currentState, spell));
    }

    return minManaSpent;
  }

  private void applySpecialEffects(final GameState state) {
    if (state.shieldTimer > 0) {
      state.shieldTimer--;
    }
    if (state.poisonTimer > 0) {
      state.boss.onDamage(3);
      state.poisonTimer--;
    }
    if (state.rechargeTimer > 0) {
      state.player.recoverMp(101);
      state.rechargeTimer--;
    }
  }

  private boolean canCastSpell(GameState currentState, Spell spell) {
    if (currentState.manaSpent + spell.cost >= minManaSpent)
      return false;
    if (currentState.player.mp < spell.cost)
      return false;
    if (spell == Spell.SHIELD && currentState.shieldTimer > 0)
      return false;
    if (spell == Spell.POISON && currentState.poisonTimer > 0)
      return false;
    return spell != Spell.RECHARGE || currentState.rechargeTimer <= 0;
  }

  private GameState damageToPlayer(GameState currentState) {
    int playerDamage =
      Math.max(1, currentState.boss.getDamage() - (currentState.shieldTimer > 0 ? 7 : 0));
    currentState.player.onDamage(playerDamage);
    currentState.playerTurn = true;
    if (currentState.player.isAlive() && currentState.manaSpent < minManaSpent)
      return currentState;
    return null;
  }

  private GameState castSpell(GameState currentState, Spell spell) {
    GameState nextState = new GameState(currentState);
    nextState.playerTurn = false;
    nextState.manaSpent += spell.cost;
    nextState.player.consumeMp(spell.cost);

    switch (spell) {
      case MAGIC_MISSILE -> nextState.boss.onDamage(4);
      case DRAIN -> {
        nextState.boss.onDamage(2);
        nextState.player.recoverHp(2);
      }
      case SHIELD -> nextState.shieldTimer = 6;
      case POISON -> nextState.poisonTimer = 6;
      case RECHARGE -> nextState.rechargeTimer = 5;
    }
    return nextState;
  }

  private static class GameCharacter {
    protected int hp;

    protected GameCharacter(int hp) {
      this.hp = hp;
    }

    protected void onDamage(int damage) {
      hp -= damage;
    }

    protected boolean isAlive() {
      return hp > 0;
    }
  }


  private static class Boss extends GameCharacter {
    private final int damage;

    public Boss(int hp, int damage) {
      super(hp);
      this.damage = damage;
    }

    public int getDamage() {
      return damage;
    }
  }


  private static class Player extends GameCharacter {
    private int mp;

    public Player(int hp, int mp) {
      super(hp);
      this.mp = mp;
    }

    public void recoverHp(int amount) {
      hp += amount;
    }

    public void recoverMp(int amount) {
      mp += amount;
    }

    public void consumeMp(int amount) {
      mp -= amount;
    }
  }


  private static class GameState {
    Player player;
    Boss boss;
    int manaSpent;
    int shieldTimer;
    int poisonTimer;
    int rechargeTimer;
    boolean playerTurn;

    public GameState(Player player, Boss boss, int manaSpent,
      int shieldTimer, int poisonTimer, int rechargeTimer, boolean playerTurn) {
      this.player = player;
      this.boss = boss;
      this.manaSpent = manaSpent;
      this.shieldTimer = shieldTimer;
      this.poisonTimer = poisonTimer;
      this.rechargeTimer = rechargeTimer;
      this.playerTurn = playerTurn;
    }

    public GameState(GameState other) {
      this.player = new Player(other.player.hp, other.player.mp);
      this.boss = new Boss(other.boss.hp, other.boss.damage);
      this.manaSpent = other.manaSpent;
      this.shieldTimer = other.shieldTimer;
      this.poisonTimer = other.poisonTimer;
      this.rechargeTimer = other.rechargeTimer;
      this.playerTurn = other.playerTurn;
    }
  }


  private enum Spell {
    MAGIC_MISSILE(53),
    DRAIN(73),
    SHIELD(113),
    POISON(173),
    RECHARGE(229),
    ;

    private final int cost;

    Spell(int cost) {
      this.cost = cost;
    }

    public int getCost() {
      return cost;
    }
  }
}
