package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day06 extends Solution<String> {

  private final List<String> input;

  public Day06(List<String> input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    Day06 d = new Day06(FileUtil.readFile(2016, 6));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public String solvePart1() {
    StringBuilder sb = new StringBuilder();
    for (Map<Character, Integer> map : countChars()) {
      Optional<Character> c =
        map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
      assert (c.isPresent());
      sb.append(c.get());
    }

    return sb.toString();
  }

  @Override
  public String solvePart2() {
    StringBuilder sb = new StringBuilder();
    for (Map<Character, Integer> map : countChars()) {
      Optional<Character> c =
        map.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
      assert (c.isPresent());
      sb.append(c.get());
    }

    return sb.toString();
  }

  private List<Map<Character, Integer>> countChars() {
    List<Map<Character, Integer>> maps = new ArrayList<>();
    for (int i = 0; i < input.getFirst().length(); i++)
      maps.add(new TreeMap<>());

    for (String line : input) {
      char[] chars = line.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        char c = chars[i];
        maps.get(i).put(c, maps.get(i).getOrDefault(c, 0) + 1);
      }
    }
    return maps;
  }
}
