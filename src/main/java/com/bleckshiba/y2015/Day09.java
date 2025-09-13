package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;

public class Day09 extends Solution<Integer> {

  private final List<String> data;

  private final Map<String, Map<String, Integer>> paths = new HashMap<>();

  private final List<String> cities = new ArrayList<>();

  public Day09(List<String> data) {this.data = data;}

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2015, 9);
    Day09 solution = new Day09(content);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    return getDistance(true);
  }

  @Override
  public Integer solvePart2() {
    return getDistance(false);
  }

  private int getDistance(boolean getShortest) {
    cities.clear();
    paths.clear();
    int result = getShortest ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    for (String[] permutation : getPermutations()) {
      int current = 0;
      for (int i = 0; i < permutation.length - 1; i++) {
        String from = permutation[i];
        String to = permutation[i + 1];
        int distance = paths.get(from).get(to);
        current += distance;
      }
      result = getShortest ? Math.min(result, current) : Math.max(result, current);
    }
    return result;
  }

  private void swap(String[] input, int a, int b) {
    String temp = input[a];
    input[a] = input[b];
    input[b] = temp;
  }

  private List<String[]> permuteIterative(int n, String[] elements) {
    List<String[]> permutations = new ArrayList<>();
    int[] indices = new int[n];
    Arrays.fill(indices, 0);
    permutations.add(elements.clone());

    int i = 0;
    while (i < n) {
      if (indices[i] < i) {
        swap(elements, i % 2 == 0 ? 0 : indices[i], i);
        permutations.add(elements.clone());
        indices[i]++;
        i = 0;
      } else {
        indices[i] = 0;
        i++;
      }
    }
    return permutations;
  }

  private List<String[]> getPermutations() {
    for (String s : data) {
      String[] parts = s.split(" ");
      storeCities(parts[0], parts[2]);
      addConnection(parts[0], parts[2], Integer.parseInt(parts[4]));
    }
    return permuteIterative(cities.size(), cities.toArray(new String[0]));
  }

  private void storeCities(String a, String b) {
    if (!cities.contains(a))
      cities.add(a);
    if (!cities.contains(b))
      cities.add(b);
  }

  private void addConnection(String a, String b, int d) {
    Map<String, Integer> fromConnection = paths.getOrDefault(a, new HashMap<>());
    Map<String, Integer> toConnection = paths.getOrDefault(b, new HashMap<>());
    fromConnection.put(b, d);
    toConnection.put(a, d);
    paths.put(a, fromConnection);
    paths.put(b, toConnection);
  }
}
