package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.List;

public class Day18 extends Solution<Integer> {
  private static final char ON = '#';

  private final List<String> data;

  private final int size;

  private final int step;

  public Day18(List<String> data, int size, int step) {
    this.data = data;
    this.size = size;
    this.step = step;
  }

  public static void main(String[] args) throws IOException {
    Day18 d = new Day18(FileUtil.readFile(2015, 18), 100, 100);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  private boolean[][] createBoard(List<String> data) {
    boolean[][] board = new boolean[size][size];
    for (int y = 0; y < data.size(); y++) {
      String[] parts = data.get(y).split("");
      for (int x = 0; x < parts.length; x++)
        board[x][y] = parts[x].charAt(0) == ON;
    }
    return board;
  }

  @Override
  public Integer solvePart1() {
    boolean[][] board = createBoard(data);
    return solve(board, false);
  }

  @Override
  public Integer solvePart2() {
    boolean[][] board = createBoard(data);
    // corner always on
    board[0][0] = true;
    board[0][size - 1] = true;
    board[size - 1][0] = true;
    board[size - 1][size - 1] = true;
    return solve(board, true);
  }

  private int solve(boolean[][] board, boolean cornersStuck) {
    for (int i = 0; i < step; i++)
      board = toggleLight(board, cornersStuck);
    return getLightsOn(board);
  }

  private boolean[][] toggleLight(boolean[][] board, boolean cornersStuck) {
    boolean[][] nextGrid = new boolean[size][size];

    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {

        if (cornersStuck && isCorner(x, y)) {
          nextGrid[x][y] = true;
          continue;
        }

        int neighborsOn = countNeighborsOn(board, x, y);

        nextGrid[x][y] = board[x][y] ? neighborsOn == 2 || neighborsOn == 3 : neighborsOn == 3;
      }
    }
    return nextGrid;
  }

  private int getLightsOn(boolean[][] board) {
    int total = 0;
    for (boolean[] chars : board)
      for (boolean aChar : chars)
        if (aChar)
          total++;
    return total;
  }

  private boolean isCorner(int x, int y) {
    return
      (x == 0 && y == 0) ||
        (x == 0 && y == size - 1) ||
        (x == size - 1 && y == 0) ||
        (x == size - 1 && y == size - 1);
  }

  private int countNeighborsOn(boolean[][] grid, int x, int y) {
    int count = 0;
    int gridSize = grid.length;

    for (int dx = -1; dx <= 1; dx++) {
      for (int dy = -1; dy <= 1; dy++) {
        if (dx == 0 && dy == 0)
          continue;

        int nx = x + dx;
        int ny = y + dy;

        if (nx >= 0 && nx < gridSize && ny >= 0 && ny < gridSize && grid[nx][ny])
          count++;
      }
    }
    return count;
  }
}
