package com.bleckshiba.y2025;

import java.io.IOException;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day03 extends Solution<Long> {
  private final List<String> input;

  public Day03(List<String> input) {
    this.input = input;
  }

  static void main() throws IOException {
    List<String> input = FileUtil.readFile(2025, 3);
    Day03 d = new Day03(input);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Long solvePart1() {
    return input.stream().map(l -> getLargestCombination(l, 2)).reduce(0L, Long::sum);
  }

  @Override
  public Long solvePart2() {
    return input.stream().map(l -> getLargestCombination(l, 12)).reduce(0L, Long::sum);
  }

  public long getLargestCombination(final String line, final int count) {
    long max = 0;
    int prevIndex = -1;
    for (int pos = count; pos > 0; pos--) {
      int bestIndex = prevIndex + 1;
      for (int i = prevIndex + 1; i <= line.length() - pos; i++)
        if (line.charAt(bestIndex) < line.charAt(i))
          bestIndex = i;
      prevIndex = bestIndex;
      max = (max * 10) + Character.getNumericValue(line.charAt(bestIndex));
    }
    return max;
  }
}
