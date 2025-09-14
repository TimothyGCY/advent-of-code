package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 extends Solution<Integer> {

  private final List<String> data;

  private final Map<String, Integer> clues;

  final Map<Integer, Map<String, Integer>> aunts = new HashMap<>();

  public Day16(List<String> data, Map<String, Integer> clues) {
    this.data = data;
    this.clues = clues;
    parseData();
  }

  public static void main(String[] args) throws IOException {
    final List<String> data = FileUtil.readFile(2015, 16);
    Map<String, Integer> clues = new HashMap<>();
    clues.put("children", 3);
    clues.put("cats", 7);
    clues.put("samoyeds", 2);
    clues.put("pomeranians", 3);
    clues.put("akitas", 0);
    clues.put("vizslas", 0);
    clues.put("goldfish", 5);
    clues.put("trees", 3);
    clues.put("cars", 2);
    clues.put("perfumes", 1);
    Day16 d = new Day16(data, clues);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    for (Map.Entry<Integer, Map<String, Integer>> entry : aunts.entrySet()) {
      boolean isMatch = true;
      for (Map.Entry<String, Integer> propEntry : entry.getValue().entrySet()) {
        if (clues.containsKey(propEntry.getKey()) && !clues.get(propEntry.getKey())
          .equals(propEntry.getValue())) {
          isMatch = false;
          break;
        }
      }
      if (isMatch)
        return entry.getKey();
    }

    return 0;
  }

  @Override
  public Integer solvePart2() {
    for (Map.Entry<Integer, Map<String, Integer>> entry : aunts.entrySet()) {
      boolean isMatch = true;
      for (Map.Entry<String, Integer> propEntry : entry.getValue().entrySet()) {
        final String key = propEntry.getKey();
        final int value = propEntry.getValue();
        if (clues.containsKey(key)) {
          final int clueValue = clues.get(key);
          isMatch = applyNewInstruction(key, value, clueValue);
        }
        if (!isMatch)
          break;
      }
      if (isMatch)
        return entry.getKey();
    }

    return 0;
  }

  private void parseData() {
    for (int i = 0; i < data.size(); i++) {
      final String[] parts = data.get(i).split("Sue \\d+: ")[1].split(", ");
      Map<String, Integer> a = aunts.getOrDefault(i + 1, new HashMap<>());
      for (String part : parts) {
        String[] split = part.split(": ");
        a.put(split[0], a.getOrDefault(split[0], 0) + Integer.parseInt(split[1]));
      }
      aunts.put(i + 1, a);
    }
  }

  // split this function out because of sonarlint
  private boolean applyNewInstruction(final String key, final int value, final int clueValue) {
    boolean isMatch = true;
    switch (key) {
      case "cats", "trees":
        if (value <= clueValue)
          isMatch = false;
        break;
      case "pomeranians", "goldfish":
        if (value >= clueValue)
          isMatch = false;
        break;
      default:
        if (value != clueValue)
          isMatch = false;
        break;
    }
    return isMatch;
  }
}
