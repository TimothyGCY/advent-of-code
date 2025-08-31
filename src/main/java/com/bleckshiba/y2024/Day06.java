package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Solution<Integer> {

  private static final String COORD = "%d,%d";

  private final char[][] grid;

  public Day06(char[][] grid) {
    this.grid = grid;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2024, 6);
    char[][] grid = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < content.size(); i++) {
      grid[i] = content.get(i).toCharArray();
    }
    Day06 solution = new Day06(grid);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    Set<String> route = new HashSet<>();

    int[] pos = getStartingPosition();
    int y = pos[0];
    int x = pos[1];
    char currentDir = grid[y][x];
    while (y > 0 && y < grid.length - 1 && x > 0 && x < grid[0].length - 1) {
      if (currentDir == '^') {
        if (grid[y - 1][x] == '#') {
          currentDir = '>';
        } else {
          route.add(String.format(COORD, x, y));
          y--;
        }
      } else if (currentDir == '>') {
        if (grid[y][x + 1] == '#') {
          currentDir = 'v';
        } else {
          route.add(String.format(COORD, x, y));
          x++;
        }
      } else if (currentDir == 'v') {
        if (grid[y + 1][x] == '#') {
          currentDir = '<';
        } else {
          route.add(String.format(COORD, x, y));
          y++;
        }
      } else {
        if (grid[y][x - 1] == '#') {
          currentDir = '^';
        } else {
          route.add(String.format(COORD, x, y));
          x--;
        }
      }
    }

    return route.size() + 1;
  }

  @Override
  public Integer solvePart2() {
    return -1;
  }

  private int[] getStartingPosition() {
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++) if (grid[j][i] == '^') return new int[] {j, i};
    return new int[] {-1, -1};
  }
}
