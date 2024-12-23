package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day09 extends Solution<Long> {

  private final String diskMap;

  public Day09(String diskMap) {
    this.diskMap = diskMap;
  }

  public static void main(String[] args) throws IOException {
    String content = FileUtil.readFile(2024, 9).getFirst();
    Day09 solution = new Day09(content);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Long solvePart1() {
    return computeChecksum(defrag(computeDisk()));
  }

  @Override
  public Long solvePart2() {
    return 1L;
  }

  public String computeDisk() {
    StringBuilder output = new StringBuilder();
    int id = 0;
    for (int i = 0; i < diskMap.length(); i++) {
      int space = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
      if (i % 2 == 0) {
        output.append(String.join("", Collections.nCopies(space, String.valueOf(id))));
        id++;
      } else {
        output.append(String.join("", Collections.nCopies(space, ".")));
      }
    }
    return output.toString();
  }

  public String defrag(String fragment) {
    char[] chars = fragment.toCharArray();
    int i = 0;
    int j = chars.length - 1;

    while (i < j) {
      if (chars[i] != '.' || chars[j] == '.') {
        if (chars[i] != '.') i++;
        if (chars[j] == '.') j--;
        continue;
      }

      char tmp = chars[i];
      chars[i] = chars[j];
      chars[j] = tmp;
      i++;
    }
    return Stream.of(chars).map(String::valueOf).collect(Collectors.joining());
  }

  public Long computeChecksum(String fragment) {
    long checksum = 0L;
    for (long i = 0; i < fragment.length(); i++) {
      char c = fragment.charAt((int) i);
      if (c == '.') continue;
      long num = Long.parseLong(String.valueOf(c));
      checksum += num * i;
    }
    return checksum;
  }
}
