package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day06 extends Solution<Integer> {

  private static final int BOARD_SIZE = 1000;
  private final List<String> data;

  public Day06(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    Day06 solution = new Day06(FileUtil.readFile(2015, 6));
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];
    for (String line : data) {
      String[] parts = line.split(" ");
      if (parts.length == 4) {
        board = Action.TOGGLE.operation.toggleOnOff(board, parts[1], parts[3]);
      } else {
        Action action = Action.identifyAction(String.format("%s %s", parts[0], parts[1]));
        board = action.operation.toggleOnOff(board, parts[2], parts[4]);
      }
    }

    int total = 0;
    for (int x = 0; x < BOARD_SIZE; x++)
      for (int y = 0; y < BOARD_SIZE; y++)
        if (board[x][y])
          total++;
    return total;
  }

  @Override
  public Integer solvePart2() {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    Arrays.stream(board).forEach(row -> Arrays.fill(row, 0));

    for (String line : data) {
      String[] parts = line.split(" ");
      if (parts.length == 4) {
        board = Action.TOGGLE.operation.toggleBrightness(board, parts[1], parts[3]);
      } else {
        Action action = Action.identifyAction(String.format("%s %s", parts[0], parts[1]));
        board = action.operation.toggleBrightness(board, parts[2], parts[4]);
      }
    }

    int total = 0;
    for (int x = 0; x < BOARD_SIZE; x++)
      for (int y = 0; y < BOARD_SIZE; y++)
        total += board[x][y];
    return total;
  }
}


interface Operation {
  boolean[][] toggleOnOff(boolean[][] src, String from, String to);

  int[][] toggleBrightness(int[][] src, String from, String to);
}


enum Action {
  TURN_ON(
    "turn on",
    new Operation() {
      @Override
      public boolean[][] toggleOnOff(boolean[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] = true;
        return src;
      }

      @Override
      public int[][] toggleBrightness(int[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] += 1;
        return src;
      }
    }),

  TURN_OFF(
    "turn off",
    new Operation() {
      @Override
      public boolean[][] toggleOnOff(boolean[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] = false;
        return src;
      }

      @Override
      public int[][] toggleBrightness(int[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] = Math.max(0, --src[x][y]);
        return src;
      }
    }),
  TOGGLE(
    "toggle",
    new Operation() {
      @Override
      public boolean[][] toggleOnOff(boolean[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] = !src[x][y];
        return src;
      }

      @Override
      public int[][] toggleBrightness(int[][] src, String from, String to) {
        int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int x = f[0]; x <= t[0]; x++)
          for (int y = f[1]; y <= t[1]; y++)
            src[x][y] += 2;
        return src;
      }
    }),
  ;

  final String cmd;

  final Operation operation;

  Action(String cmd, Operation operation) {
    this.cmd = cmd;
    this.operation = operation;
  }

  public static Action identifyAction(String cmd) {
    return Arrays.stream(values()).filter(a -> a.cmd.equals(cmd)).findFirst().orElseThrow();
  }
}
