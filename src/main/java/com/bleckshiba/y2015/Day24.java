package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day24 extends Solution<Long> {

  private final List<Integer> packages;

  public Day24(List<Integer> packages) {
    this.packages = packages;
  }

  public static void main(String[] args) throws IOException {
    Day24 d = new Day24(FileUtil.readFile(2015, 24).stream().map(Integer::parseInt).toList());
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Long solvePart1() {
    return compute(3);
  }

  @Override
  public Long solvePart2() {
    return compute(4);
  }

  private long compute(int groupCount) {
    int totalWeight = packages.stream().reduce(0, Integer::sum);
    int targetWeight = totalWeight / groupCount;

    List<List<Integer>> combinations = new ArrayList<>();

    for (int i = 1; i <= packages.size(); i++) {
      findCombination(packages, targetWeight, new ArrayList<>(), combinations, i, 0);
      if (!combinations.isEmpty())
        break;
    }

    if (combinations.isEmpty())
      return -1L;

    long minQE = Long.MAX_VALUE;
    for (List<Integer> combination : combinations) {
      long current = combination.stream().mapToLong(Integer::intValue)
        .reduce(1L, (a, b) -> a * b);
      minQE = Math.min(minQE, current);
    }

    return minQE;
  }

  private void findCombination(List<Integer> source, int targetWeight, List<Integer> current,
    List<List<Integer>> combinations, int groupSize, int start) {
    if (current.size() == groupSize) {
      if (current.stream().reduce(0, Integer::sum) == targetWeight)
        combinations.add(new ArrayList<>(current));
      return;
    }

    if (current.size() > groupSize || start >= source.size())
      return;

    for (int i = start; i < source.size(); i++) {
      int weight = source.get(i);
      current.add(weight);
      findCombination(source, targetWeight, current, combinations, groupSize, i + 1);
      current.removeLast();
    }
  }
}
