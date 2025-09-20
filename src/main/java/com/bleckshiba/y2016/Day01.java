package com.bleckshiba.y2016;

import java.util.HashSet;
import java.util.Set;

import com.bleckshiba.Pair;
import com.bleckshiba.Solution;

public class Day01 extends Solution<Integer> {

  private String input;

  public void setInput(String input) {
    this.input = input;
  }

  public static void main(String[] args) {
    Day01 d = new Day01();
    d.setInput(
      "R4, R3, R5, L3, L5, R2, L2, R5, L2, R5, R5, R5, R1, R3, L2, L2, L1, R5, L3, R1, L2, R1, L3, L5, L1, R3, L4, R2, R4, L3, L1, R4, L4, R3, L5, L3, R188, R4, L1, R48, L5, R4, R71, R3, L2, R188, L3, R2, L3, R3, L5, L1, R1, L2, L4, L2, R5, L3, R3, R3, R4, L3, L4, R5, L4, L4, R3, R4, L4, R1, L3, L1, L1, R4, R1, L4, R1, L1, L3, R2, L2, R2, L1, R5, R3, R4, L5, R2, R5, L5, R1, R2, L1, L3, R3, R1, R3, L4, R4, L4, L1, R1, L2, L2, L4, R1, L3, R4, L2, R3, L1, L5, R4, R5, R2, R5, R1, R5, R1, R3, L3, L2, L2, L5, R2, L2, R5, R5, L2, R3, L5, R5, L2, R4, R2, L1, R3, L5, R3, R2, R5, L1, R3, L2, R2, R1");
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    Direction currentDirection = Direction.UP;
    int[] block = new int[] {0, 0};
    String[] parts = input.split(", ");
    for (String part : parts) {
      char d = part.charAt(0);
      int v = Integer.parseInt(part.substring(1));
      Pair<Direction, int[]> res;
      if (d == 'R') {
        res = currentDirection.turnRight(block, v);
      } else {
        res = currentDirection.turnLeft(block, v);
      }
      block = res.right();
      currentDirection = res.left();
    }

    return Math.abs(block[0]) + Math.abs(block[1]);
  }

  @Override
  public Integer solvePart2() {
    Direction currentDirection = Direction.UP;
    int[] block = new int[] {0, 0};
    Set<String> visited = new HashSet<>();
    boolean end = false;
    String[] parts = input.split(", ");
    for (String part : parts) {
      char d = part.charAt(0);
      int v = Integer.parseInt(part.substring(1));
      for (int i = 0; i < v; i++) {
        move(d, currentDirection, block);

        String current = String.format("%d,%d", block[0], block[1]);
        if (visited.contains(current)) {
          end = true;
          break;
        } else {
          visited.add(current);
        }
      }

      if (end)
        break;

      currentDirection = determineNextDirect(d, currentDirection);
    }

    return Math.abs(block[0]) + Math.abs(block[1]);
  }

  private void move(char d, Direction currentDirection, int[] block) {
    if (d == 'R') {
      switch (currentDirection) {
        case UP -> block[0]++;
        case DOWN -> block[0]--;
        case LEFT -> block[1]++;
        case RIGHT -> block[1]--;
      }
    } else {
      switch (currentDirection) {
        case UP -> block[0]--;
        case DOWN -> block[0]++;
        case LEFT -> block[1]--;
        case RIGHT -> block[1]++;
      }
    }
  }

  private Direction determineNextDirect(char d, Direction currentDirection) {
    if (d == 'R') {
      return switch (currentDirection) {
        case UP -> Direction.RIGHT;
        case RIGHT -> Direction.DOWN;
        case DOWN -> Direction.LEFT;
        case LEFT -> Direction.UP;
      };
    } else {
      return switch (currentDirection) {
        case UP -> Direction.LEFT;
        case LEFT -> Direction.DOWN;
        case DOWN -> Direction.RIGHT;
        case RIGHT -> Direction.UP;
      };
    }
  }

  private enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    Pair<Direction, int[]> turnLeft(int[] current, int n) {
      return switch (this) {
        case UP -> {
          current[0] -= n;
          yield new Pair<>(Direction.LEFT, current);
        }
        case DOWN -> {
          current[0] += n;
          yield new Pair<>(Direction.RIGHT, current);
        }
        case LEFT -> {
          current[1] -= n;
          yield new Pair<>(Direction.DOWN, current);
        }
        case RIGHT -> {
          current[1] += n;
          yield new Pair<>(Direction.UP, current);
        }
      };
    }

    Pair<Direction, int[]> turnRight(int[] current, int n) {
      return switch (this) {
        case UP -> {
          current[0] += n;
          yield new Pair<>(Direction.RIGHT, current);
        }
        case DOWN -> {
          current[0] -= n;
          yield new Pair<>(Direction.LEFT, current);
        }
        case LEFT -> {
          current[1] += n;
          yield new Pair<>(Direction.UP, current);
        }
        case RIGHT -> {
          current[1] -= n;
          yield new Pair<>(Direction.DOWN, current);
        }
      };
    }
  }
}
