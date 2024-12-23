package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends Solution<Integer> {

  private static final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

  private static final List<String> twice =
      List.of(
          "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj", "kk", "ll", "mm", "nn", "oo",
          "pp", "qq", "rr", "ss", "tt", "uu", "vv", "ww", "xx", "yy", "zz");

  private static final List<String> ngWords = List.of("ab", "cd", "pq", "xy");

  private final List<String> data;

  public Day05(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2015, 5);
    Day05 solution = new Day05(content);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    return data.stream()
        .filter(
            l -> {
              String v =
                  l.chars()
                      .mapToObj(c -> (char) c)
                      .filter(vowels::contains)
                      .map(String::valueOf)
                      .collect(Collectors.joining());
              if (v.length() < 3) return false;

              if (twice.stream().noneMatch(l::contains)) return false;

              return ngWords.stream().noneMatch(l::contains);
            })
        .toList()
        .size();
  }

  @Override
  public Integer solvePart2() {
    return data.stream()
        .filter(l -> hasPairAppearingTwice(l) && hasRepeatWithOneBetween(l))
        .toList()
        .size();
  }

  private boolean hasPairAppearingTwice(String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      String pair = input.substring(i, i + 2);
      if (input.indexOf(pair, i + 2) != -1) {
        return true;
      }
    }
    return false;
  }

  private boolean hasRepeatWithOneBetween(String input) {
    for (int i = 0; i < input.length() - 2; i++) {
      if (input.charAt(i) == input.charAt(i + 2)) {
        return true;
      }
    }
    return false;
  }
}
