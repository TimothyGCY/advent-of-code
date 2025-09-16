package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day23 extends Solution<int[]> {

  private final List<String> input;

  public Day23(List<String> input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    Day23 d = new Day23(FileUtil.readFile(2015, 23));
    System.out.println(Arrays.toString(d.solvePart1()));
    System.out.println(Arrays.toString(d.solvePart2()));
  }

  @Override
  public int[] solvePart1() {
    return compute(0);
  }

  @Override
  public int[] solvePart2() {
    return compute(1);
  }

  private int[] compute(int a) {
    int idx = 0;
    int[] values = new int[] {a, 0};
    while (idx < input.size()) {
      String[] parts = input.get(idx).split(" |, ");
      final String instruction = parts[0];
      final int register = switch (instruction) {
        case "jmp", "jie", "jio" -> 0;
        default -> "b".equals(parts[1]) ? 1 : 0;
      };
      final int offset = switch (instruction) {
        case "jmp" -> Integer.parseInt(parts[1]);
        case "jie", "jio" -> Integer.parseInt(parts[2]);
        default -> 0;
      };


      switch (instruction) {
        case "hlf" -> values[register] /= 2;
        case "tpl" -> values[register] *= 3;
        case "inc" -> values[register]++;
        case "jmp" -> idx += (offset - 1);
        case "jie" -> idx += (values[register] % 2 == 0) ? offset - 1 : 0;
        case "jio" -> idx += (values[register] == 1) ? offset - 1 : 0;
        default -> throw new IllegalArgumentException("Unknown instruction: " + instruction);
      }
      idx++;
    }

    return values;
  }
}
