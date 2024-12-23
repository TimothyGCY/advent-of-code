package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bleckshiba.y2015.Day06.COORD_FORMAT;

public class Day06 extends Solution<Integer> {

  public static final String COORD_FORMAT = "%d,%d";

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
  protected Integer solvePart1() {
    Map<String, Boolean> board = new HashMap<>();

    for (String line : data) {
      String[] parts = line.split(" ");
      if (parts.length == 4) {
        board = Action.TOGGLE.operation.toggleOnOff(board, parts[1], parts[3]);
      } else {
        Action action = Action.identifyAction(String.format("%s %s", parts[0], parts[1]));
        board = action.operation.toggleOnOff(board, parts[2], parts[4]);
      }
    }

    return board.size();
  }

  @Override
  protected Integer solvePart2() {
    int[][] board = new int[1000][1000];
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
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        total += board[x][y];
      }
    }
    return total;
  }
}

interface Operation {
  Map<String, Boolean> toggleOnOff(Map<String, Boolean> src, String from, String to);

  int[][] toggleBrightness(int[][] src, String from, String to);
}

enum Action {
  TURN_ON(
      "turn on",
      new Operation() {
        @Override
        public Map<String, Boolean> toggleOnOff(Map<String, Boolean> src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              String cur = String.format(COORD_FORMAT, x, y);
              if (!Boolean.TRUE.equals(src.get(cur))) {
                src.put(cur, true);
              }
            }
          }
          return src;
        }

        @Override
        public int[][] toggleBrightness(int[][] src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              src[x][y] += 1;
            }
          }
          return src;
        }
      }),

  TURN_OFF(
      "turn off",
      new Operation() {
        @Override
        public Map<String, Boolean> toggleOnOff(Map<String, Boolean> src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              String cur = String.format(COORD_FORMAT, x, y);
              src.remove(cur);
            }
          }
          return src;
        }

        @Override
        public int[][] toggleBrightness(int[][] src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              src[x][y] += -1;
              src[x][y] = Math.max(0, src[x][y]);
            }
          }
          return src;
        }
      }),

  TOGGLE(
      "toggle",
      new Operation() {
        @Override
        public Map<String, Boolean> toggleOnOff(Map<String, Boolean> src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              String cur = String.format(COORD_FORMAT, x, y);
              if (src.containsKey(cur)) {
                src.remove(cur);
              } else {
                src.putIfAbsent(cur, true);
              }
            }
          }
          return src;
        }

        @Override
        public int[][] toggleBrightness(int[][] src, String from, String to) {
          int[] f = Arrays.stream(from.split(",")).mapToInt(Integer::parseInt).toArray();
          int[] t = Arrays.stream(to.split(",")).mapToInt(Integer::parseInt).toArray();
          for (int x = f[0]; x <= t[0]; x++) {
            for (int y = f[1]; y <= t[1]; y++) {
              src[x][y] += 2;
            }
          }
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
