package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day08 extends Solution<Integer> {

  private static final int WIDTH = 50;
  private static final int HEIGHT = 6;

  private static final Pattern rect = Pattern.compile("rect (\\d+)x(\\d+)");
  private static final Pattern rotRow = Pattern.compile("rotate row y=(\\d+) by (\\d+)");
  private static final Pattern rotCol = Pattern.compile("rotate column x=(\\d+) by (\\d+)");

  private final List<String> instructions;

  private final boolean[][] screen = new boolean[HEIGHT][WIDTH];

  public Day08(List<String> instructions) {
    this.instructions = instructions;
  }

  static void main() throws IOException {
    final Day08 d = new Day08(FileUtil.readFile(2016, 8));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    foo();
    printScreen();
    Integer cnt = 0;
    for (boolean[] booleans : screen)
      for (var _ : booleans)
        cnt++;
    return cnt;
  }

  @Override
  public Integer solvePart2() {
    return 0;
  }

  private void foo() {
    for (String instruction : instructions) {
      Matcher mRect = rect.matcher(instruction);
      Matcher mRotRow = rotRow.matcher(instruction);
      Matcher mRotCol = rotCol.matcher(instruction);

      if (mRect.find()) {
        int col = Integer.parseInt(mRect.group(1));
        int row = Integer.parseInt(mRect.group(2));
        for (int r = 0; r < row; r++)
          for (int c = 0; c < col; c++)
            screen[r][c] = true;
      } else if (mRotRow.find()) {
        int row = Integer.parseInt(mRotRow.group(1));
        int cnt = Integer.parseInt(mRotRow.group(2));
        rotateRow(screen[row], cnt);
      } else if (mRotCol.find()) {
        int col = Integer.parseInt(mRotCol.group(1));
        int cnt = Integer.parseInt(mRotCol.group(2));
        boolean[] trans = getColumn(col);
        rotateRow(trans, cnt);
        setColumn(col, trans);
      }
    }
  }

  private void rotateRow(boolean[] arr, int count) {
    count %= arr.length;
    reverse(arr, 0, arr.length - count);
    reverse(arr, arr.length - count, arr.length);
    reverse(arr, 0, arr.length);
  }

  private void reverse(boolean[] arr, int from, int to) {
    for (int i = 0; i < (to - from) / 2; i++) {
      boolean temp = arr[from + 1];
      arr[from + 1] = arr[to - 1 - i];
      arr[to - 1 - i] = temp;
    }
  }

  private boolean[] getColumn(int col) {
    boolean[] ret = new boolean[screen.length];
    for (int i = 0; i < ret.length; i++)
      ret[i] = screen[i][col];
    return ret;
  }

  private void setColumn(int col, boolean[] fill) {
    for (int i = 0; i < fill.length; i++)
      screen[i][col] = fill[i];
  }

  private void printScreen() {
    for (boolean[] booleans : screen) {
      for (boolean aBoolean : booleans)
        System.out.print(aBoolean ? "#" : ".");
      System.out.println();
    }
  }
}
