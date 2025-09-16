package com.bleckshiba.y2015;

import com.bleckshiba.Solution;

public class Day25 extends Solution<Long> {

  private final int row;
  private final int col;

  public Day25(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public static void main(String[] args) {
    Day25 d = new Day25(2978, 3083);
    System.out.println(d.solvePart1());
  }

  @Override
  public Long solvePart1() {
    long result = 20151125;
    int currentRow = 1;
    int currentCol = 1;
    while (currentRow != row || currentCol != col) {
      if (currentRow == 1) {
        currentRow = currentCol + 1;
        currentCol = 1;
      } else {
        currentRow--;
        currentCol++;
      }
      result = (result * 252533) % 33554393;
    }

    return result;
  }

  @Override
  public Long solvePart2() {
    return 0L;
  }
}
