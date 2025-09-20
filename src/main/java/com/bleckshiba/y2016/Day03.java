package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;
import com.bleckshiba.Triple;

public class Day03 extends Solution<Integer> {

  private final List<String> input;

  public Day03(List<String> input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    Day03 d = new Day03(FileUtil.readFile(2016, 3));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    return Math.toIntExact(input.stream()
      .map(l -> Arrays.stream(l.trim().split("\\s+"))
        .mapToInt(Integer::parseInt).sorted().toArray())
      .filter(l -> l[0] + l[1] > l[2]).count());
  }

  @Override
  public Integer solvePart2() {
    int count = 0;
    List<int[]> list = new ArrayList<>();
    int j = 0;
    for (String line : input) {
      Triple<Integer, Integer, Integer> triple = parseLine(line);
      int a = triple.left();
      int b = triple.middle();
      int c = triple.right();

      int[] t = new int[] {a, b, c};
      list.add(t);

      j = (j + 1) % 3;
      if (j == 0) {
        count += countValid(list);
        list.clear();
      }
    }

    return count;
  }

  private int countValid(List<int[]> list) {
    int count = 0;
    for (int i = 0; i < list.size(); i++) {
      int a = list.get(0)[i];
      int b = list.get(1)[i];
      int c = list.get(2)[i];
      if ((a + b > c) && (b + c > a) && (a + c > b))
        count++;
    }
    return count;
  }

  private Triple<Integer, Integer, Integer> parseLine(String line) {
    String[] parts = line.trim().split("\\s+");
    return new Triple<>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
      Integer.parseInt(parts[2]));
  }
}
