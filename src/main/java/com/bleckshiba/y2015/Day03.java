package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day03 extends Solution<Integer> {

  private final String data;

  public Day03(String data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2015, 3);
    Day03 solution = new Day03(content.getFirst());
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  protected Integer solvePart1() {
    int x = 1;
    int y = 1;

    Set<String> set = new HashSet<>();
    set.add(String.format("%d,%d", x, y));

    for (int i = 0; i < data.length(); i++) {
      if ('<' == data.charAt(i)) {
        x -= 1;
      } else if ('>' == data.charAt(i)) {
        x += 1;
      } else if ('^' == data.charAt(i)) {
        y += 1;
      } else {
        y -= 1;
      }
      String posStr = String.format("%d,%d", x, y);
      if (set.contains(posStr)) continue;
      set.add(posStr);
    }
    return set.size();
  }

  @Override
  protected Integer solvePart2() {
    int sx = 1;
    int sy = 1;
    int rx = 1;
    int ry = 1;

    Set<String> santa = new HashSet<>();
    santa.add(String.format("%d,%d", sx, sy));
    Set<String> robo = new HashSet<>();
    robo.add(String.format("%d,%d", rx, ry));

    for (int i = 0; i < data.length(); i++) {
      if (i % 2 == 0) {
        if ('<' == data.charAt(i)) {
          sx -= 1;
        } else if ('>' == data.charAt(i)) {
          sx += 1;
        } else if ('^' == data.charAt(i)) {
          sy += 1;
        } else {
          sy -= 1;
        }
        String posStr = String.format("%d,%d", sx, sy);
        if (santa.contains(posStr) || robo.contains(posStr)) continue;
        santa.add(posStr);
      } else {
        if ('<' == data.charAt(i)) {
          rx -= 1;
        } else if ('>' == data.charAt(i)) {
          rx += 1;
        } else if ('^' == data.charAt(i)) {
          ry += 1;
        } else {
          ry -= 1;
        }
        String posStr = String.format("%d,%d", rx, ry);
        if (robo.contains(posStr) || santa.contains(posStr)) continue;
        robo.add(posStr);
      }
    }
    return santa.size() + robo.size() - 1;
  }
}
