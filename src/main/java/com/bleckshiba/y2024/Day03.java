package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Solution<Integer> {

  private final String data;

  public Day03(String data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    Day03 solution = new Day03(FileUtil.readFile(2024, 3).stream().reduce("", (a, b) -> a + b));
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int total = 0;

    Matcher matcher = createMatcher("(mul\\((\\d{1,3}),(\\d{1,3})\\))");

    while (matcher.find()) if (matcher.group().startsWith("mul")) total += multiply(matcher);
    return total;
  }

  @Override
  public Integer solvePart2() {
    int total = 0;

    Matcher matcher = createMatcher("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))");

    boolean enabled = true;

    while (matcher.find())
      if (matcher.group().startsWith("mul")) {
        if (enabled) total += multiply(matcher);
      } else if (matcher.group().equals("do()")) enabled = true;
      else if (matcher.group().equals("don't()")) enabled = false;

    return total;
  }

  private Matcher createMatcher(String regex) {
    return Pattern.compile(regex).matcher(data);
  }

  private int multiply(Matcher matcher) {
    int a = Integer.parseInt(matcher.group(2));
    int b = Integer.parseInt(matcher.group(3));
    return a * b;
  }
}
