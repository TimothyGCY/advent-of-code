package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day05 extends Solution<Integer> {

  private final List<String> rules;

  private final List<String> updates;

  private final List<String> failed = new ArrayList<>();

  public Day05(List<String> rules, List<String> updates) {
    this.rules = rules;
    this.updates = updates;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2024, 5);
    final int separator = content.indexOf("");
    List<String> rules = content.subList(0, separator);
    List<String> updates = content.subList(separator + 1, content.size());
    Day05 solution = new Day05(rules, updates);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int sum = 0;

    for (String update : updates) {
      String[] seq = update.split(",");
      boolean found = true;
      for (int i = 0; i < seq.length - 1; i++) {
        int finalI = i;
        if (rules.stream()
            .noneMatch(r -> r.startsWith(seq[finalI]) && r.endsWith(seq[finalI + 1]))) {
          failed.add(update);
          found = false;
          break;
        }
      }
      if (found) {
        if (seq.length % 2 == 0) {
          sum += Integer.parseInt(seq[seq.length / 2]);
        } else {
          sum += Integer.parseInt(seq[(seq.length - 1) / 2]);
        }
      }
    }

    return sum;
  }

  @Override
  public Integer solvePart2() {
    int sum = 0;

    for (int n = 0; n < failed.size(); n++) {
      String[] seq = failed.get(n).split(",");
      for (int i = 0; i < seq.length - 1; i++) {
        final int I = i;

        if (rules.stream().noneMatch(r -> r.startsWith(seq[I]) && r.endsWith(seq[I + 1]))) {
          String tmp = seq[I];
          seq[I] = seq[I + 1];
          seq[I + 1] = tmp;
          String newSeq = String.join(",", seq);
          failed.set(n, newSeq);
          return solvePart2();
        }
      }

      if (seq.length % 2 == 0) {
        sum += Integer.parseInt(seq[seq.length / 2]);
      } else {
        sum += Integer.parseInt(seq[(seq.length - 1) / 2]);
      }
    }

    return sum;
  }
}
