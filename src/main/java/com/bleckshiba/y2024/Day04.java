package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.List;

public class Day04 extends Solution<Integer> {

  final char[][] data;

  public Day04(char[][] data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2024, 4);
    char[][] data = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < data.length; i++) data[i] = content.get(i).toCharArray();
    Day04 solution = new Day04(data);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int count = 0;

    for (int i = 0; i < data.length; i++) {
      char[] currentRow = data[i];
      for (int j = 0; j < currentRow.length; j++) {
        char currentCell = currentRow[j];
        if (j < currentRow.length - 3
            && currentCell == 'X'
            && currentRow[j + 1] == 'M'
            && currentRow[j + 2] == 'A'
            && currentRow[j + 3] == 'S') count++;
        if (j >= 3
            && currentCell == 'X'
            && currentRow[j - 1] == 'M'
            && currentRow[j - 2] == 'A'
            && currentRow[j - 3] == 'S') count++;
        if (i < data.length - 3
            && currentCell == 'X'
            && data[i + 1][j] == 'M'
            && data[i + 2][j] == 'A'
            && data[i + 3][j] == 'S') count++;
        if (i >= 3
            && currentCell == 'X'
            && data[i - 1][j] == 'M'
            && data[i - 2][j] == 'A'
            && data[i - 3][j] == 'S') count++;

        if (i < data.length - 3
            && j < currentRow.length - 3
            && currentCell == 'X'
            && data[i + 1][j + 1] == 'M'
            && data[i + 2][j + 2] == 'A'
            && data[i + 3][j + 3] == 'S') count++;
        if (i < data.length - 3
            && j >= 3
            && currentCell == 'X'
            && data[i + 1][j - 1] == 'M'
            && data[i + 2][j - 2] == 'A'
            && data[i + 3][j - 3] == 'S') count++;
        if (i >= 3
            && j < currentRow.length - 3
            && currentCell == 'X'
            && data[i - 1][j + 1] == 'M'
            && data[i - 2][j + 2] == 'A'
            && data[i - 3][j + 3] == 'S') count++;
        if (i >= 3
            && j >= 3
            && currentCell == 'X'
            && data[i - 1][j - 1] == 'M'
            && data[i - 2][j - 2] == 'A'
            && data[i - 3][j - 3] == 'S') count++;
      }
    }

    return count;
  }

  @Override
  public Integer solvePart2() {
    int count = 0;

    for (int i = 1; i < data.length - 1; i++) {
      char[] currentRow = data[i];
      for (int j = 1; j < currentRow.length - 1; j++) {
        char currentCell = currentRow[j];
        if (currentCell == 'A'
            && ((data[i + 1][j + 1] == 'S' && data[i - 1][j - 1] == 'M')
                || (data[i + 1][j + 1] == 'M' && data[i - 1][j - 1] == 'S'))
            && ((data[i + 1][j - 1] == 'S' && data[i - 1][j + 1] == 'M')
                || (data[i + 1][j - 1] == 'M' && data[i - 1][j + 1] == 'S'))) count++;
      }
    }

    return count;
  }
}
