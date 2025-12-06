package com.bleckshiba.y2025;

import java.io.IOException;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day01 extends Solution<Long> {
  final List<String> instructions;

  public Day01(List<String> instructions) {
    this.instructions = instructions;
  }

  static void main() throws IOException {
    List<String> instructions = FileUtil.readFile(2025, 1);
    Day01 solution = new Day01(instructions);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Long solvePart1() {
    long count = 0;
    int dial = 50;
    for (String instruction : instructions) {
      int val = Integer.parseInt(instruction.substring(1)) % 100;
      final char direction = instruction.charAt(0);
      if (direction == 'L')
        val *= -1;

      dial = (dial + val + 100) % 100;
      if (dial == 0)
        count++;
    }
    return count;
  }

  @Override
  public Long solvePart2() {
    long count = 0;
    int dial = 50;
    int prev;
    for (String instruction : instructions) {
      prev = dial;
      int val = Integer.parseInt(instruction.substring(1));
      if (instruction.charAt(0) == 'L')
        dial -= val;
      else
        dial += val;

      if (dial == 0 || prev * dial < 0)
        count++;
      count += Math.abs(dial / 100);
      dial %= 100;
    }
    return count;
  }
}
