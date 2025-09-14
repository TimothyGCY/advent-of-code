package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day17 extends Solution<Integer> {

  private final List<String> data;

  private final int target;

  private int totalCombinations = 0;

  private List<Integer> containers;

  private static Map<Integer, Integer> combinationsByCount = new HashMap<>();

  public Day17(List<String> data, int target) {
    this.data = data;
    this.target = target;
    parseData();
  }

  public static void main(String[] args) throws IOException {
    List<String> data = FileUtil.readFile(2015, 17);
    Day17 d = new Day17(data, 150);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    findCombinations(0, target, 0);
    return totalCombinations;
  }

  @Override
  public Integer solvePart2() {
    int minContainers = Integer.MAX_VALUE;
    for (int count : combinationsByCount.keySet()) {
      if (count < minContainers) {
        minContainers = count;
      }
    }
    return combinationsByCount.get(minContainers);
  }

  private void parseData() {
    containers = data.stream().map(Integer::parseInt).toList();
  }

  private void findCombinations(int index, int remainingVolume, int containersUsed) {
    if (remainingVolume == 0) {
      totalCombinations++;
      combinationsByCount.put(containersUsed,
        combinationsByCount.getOrDefault(containersUsed, 0) + 1);
      return;
    }

    if (index >= containers.size() || remainingVolume < 0)
      return;

    findCombinations(index + 1, remainingVolume - containers.get(index), containersUsed + 1);
    findCombinations(index + 1, remainingVolume, containersUsed);
  }
}
