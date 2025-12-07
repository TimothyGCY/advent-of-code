package com.bleckshiba.y2025;

import java.io.IOException;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day04 extends Solution<Integer> {
  private static final int[][] DIRS = {
    {-1, -1}, {-1, 0}, {-1, 1},
    {0, -1}, {0, 1},
    {1, -1}, {1, 0}, {1, 1}
  };

  private final List<String> input;

  public Day04(List<String> input) {
    this.input = input;
  }

  static void main() throws IOException {
    List<String> input = FileUtil.readFile(2025, 4);
    Solution<Integer> solution = new Day04(input);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int total = 0;
    final int rows = input.size();
    final int cols = input.getFirst().length();
    boolean[][] rolls = parseRolls(rows, cols);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!rolls[i][j])
          continue;
        int totalAdjRolls = countAdjacent(rolls, i, j);
        if (totalAdjRolls < 4)
          total++;
      }
    }
    return total;
  }

  // FIXME: SonarLint - Complexity
  @Override
  public Integer solvePart2() {
    int total = 0;
    final int rows = input.size();
    final int cols = input.getFirst().length();
    boolean[][] rolls = parseRolls(rows, cols);

    while (true) {
      int count = 0;
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (!rolls[i][j])
            continue;
          int totalAdjRolls = countAdjacent(rolls, i, j);
          if (totalAdjRolls < 4) {
            count++;
            rolls[i][j] = false;
          }
        }
      }
      if (count == 0)
        break;
      total += count;
    }

    return total;
  }

  private boolean[][] parseRolls(final int row, final int col) {
    boolean[][] rolls = new boolean[row][col];
    int r = 0;
    for (String line : input) {
      for (int c = 0; c < col; c++) {
        if (line.charAt(c) == '@')
          rolls[r][c] = true;
      }
      r++;
    }
    return rolls;
  }

  private int countAdjacent(boolean[][] rolls, int row, int col) {
    int count = 0;
    int rows = rolls.length;
    int cols = rolls[0].length;

    for (int[] dir : DIRS) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && rolls[nr][nc])
        count++;
    }
    return count;
  }
}
