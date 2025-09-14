package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;

public class Day13 extends Solution<Long> {
  private static final String ME = "me";

  private final List<String> data;

  private final Map<String, Map<String, Integer>> happiness = new HashMap<>();

  private final List<String> people = new ArrayList<>();

  public Day13(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    final List<String> data = FileUtil.readFile(2015, 13);
    Day13 solution = new Day13(data);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Long solvePart1() {
    parseData();
    return permute(new ArrayList<>(), new HashSet<>());
  }

  @Override
  public Long solvePart2() {
    parseData();
    people.add(ME);
    for (String name : people) {
      happiness.computeIfAbsent(name, k -> new HashMap<>()).put(ME, 0);
      happiness.computeIfAbsent(ME, k -> new HashMap<>()).put(name, 0);
    }
    return permute(new  ArrayList<>(), new HashSet<>());
  }

  private void parseData() {
    people.clear();
    happiness.clear();
    for (String line : data) {
      String[] parts = line.split(" ");
      String a = parts[0];
      String b = parts[10].substring(0, parts[10].length() - 1);
      int d = Integer.parseInt(parts[3]);
      if ("lose".equals(parts[2]))
        d *= -1;

      happiness.computeIfAbsent(a, k -> new HashMap<>()).put(b, d);
      if (!people.contains(a))
        people.add(a);
    }
  }

  private long permute(List<String> currentArrangement, Set<String> used) {
    if (currentArrangement.size() == people.size()) {
      return calculateHappiness(currentArrangement);
    }

    long max = Long.MIN_VALUE;

    for (String person : people) {
      if (!used.contains(person)) {
        used.add(person);
        currentArrangement.add(person);
        max = Math.max(max, permute(currentArrangement, used));

        currentArrangement.removeLast();
        used.remove(person);
      }
    }

    return max;
  }

  private long calculateHappiness(List<String> arrangement) {
    long currentHappiness = 0;
    int n = arrangement.size();

    for (int i = 0; i < n; i++) {
      String current = arrangement.get(i);
      String next = arrangement.get((i + 1) % n);

      currentHappiness += happiness.get(current).get(next);
      currentHappiness += happiness.get(next).get(current);
    }

    return currentHappiness;
  }
}
