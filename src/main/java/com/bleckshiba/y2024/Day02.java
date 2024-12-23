package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day02 extends Solution<Integer> {

  private static final int THRESHOLD = 3;

  private final List<String> data;

  public Day02(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    Day02 solution = new Day02(FileUtil.readFile(2024, 2));
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int count = 0;
    for (String line : data) {
      List<Integer> parts = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
      if (isSafeReport(parts)) count++;
    }

    return count;
  }

  @Override
  public Integer solvePart2() {
    int count = 0;

    for (String datum : data) {
      List<Integer> parts = Arrays.stream(datum.split(" ")).map(Integer::parseInt).toList();

      if (isSafeReport(parts)) {
        count++;
        continue;
      }

      for (int i = 0; i < parts.size(); i++) {
        Integer[] enhanced = new Integer[parts.size() - 1];
        int index = 0;

        for (int j = 0; j < parts.size(); j++) if (j != i) enhanced[index++] = parts.get(j);

        if (isSafeReport(Arrays.asList(enhanced))) {
          count++;
          break;
        }
      }
    }

    return count;
  }

  private boolean isSafeReport(List<Integer> reports) {
    boolean isIncreasing = true;
    boolean isDecreasing = true;
    for (int i = 0; i < reports.size() - 1; i++) {
      int diff = reports.get(i + 1) - reports.get(i);
      if (diff == 0 || Math.abs(diff) > THRESHOLD) return false;
      if (diff < 0) isIncreasing = false;
      if (diff > 0) isDecreasing = false;
      if (!isIncreasing && !isDecreasing) return false;
    }
    return true;
  }
}
