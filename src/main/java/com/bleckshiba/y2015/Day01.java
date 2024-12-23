package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.List;

public class Day01 extends Solution<Integer> {

  private final String data;

  public Day01(String data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2015, 1);
    assert !content.isEmpty();
    Day01 solution = new Day01(content.getFirst());
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int floor = 0;
    for (int i = 0; i < data.length(); i++) {
      if ('(' == data.charAt(i)) {
        floor++;
      } else if (')' == data.charAt(i)) {
        floor--;
      }
    }
    return floor;
  }

  @Override
  public Integer solvePart2() {
    int floor = 0;
    for (int i = 0; i < data.length(); i++) {
      if ('(' == data.charAt(i)) {
        floor++;
      } else if (')' == data.charAt(i)) {
        floor--;
      }

      if (floor < 0) return i + 1;
    }
    return -1;
  }
}
