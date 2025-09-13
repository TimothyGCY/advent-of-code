package com.bleckshiba.y2015;

import com.bleckshiba.Solution;

import java.util.*;

public class Day11 extends Solution<String> {
  private static final char MIN = 'a';

  private static final char MAX = 'z';

  private final String data;

  public Day11(String data) {
    this.data = data;
  }

  public static void main(String[] args) {
    Day11 solution = new Day11("hxbxwxba");
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public String solvePart1() {
    return nextPassword(data);
  }

  @Override
  public String solvePart2() {
    return nextPassword(solvePart1());
  }

  private String nextPassword(String input) {
    byte[] bytes = input.getBytes();
    while (true) {
      bytes = nextNumber(bytes);

      if (isValid(bytes)) {
        return new String(bytes);
      }
    }
  }

  private byte[] nextNumber(byte[] bytes) {
    byte[] newSequence = Arrays.copyOf(bytes, bytes.length);
    int lastIndex = newSequence.length - 1;
    newSequence[lastIndex]++;

    while (newSequence[lastIndex] > MAX) {
      newSequence[lastIndex] = MIN;
      lastIndex--;
      if (lastIndex < 0) {
        return new byte[0];
      }
      newSequence[lastIndex]++;
    }

    for (int i = 0; i < newSequence.length; i++) {
      if (hasForbiddenChar(newSequence[i])) {
        newSequence[i]++;
        for (int j = i + 1; j < newSequence.length; j++)
          newSequence[j] = MIN;
      }
    }

    return newSequence;
  }

  private boolean hasForbiddenChar(byte b) {
    return b == 'i' || b == 'o' || b == 'l';
  }

  private boolean isValid(byte[] bytes) {
    boolean hasStraight = false;
    int duplicateCount = 0;
    for (byte b : bytes)
      if (hasForbiddenChar(b))
        return false;

    for (int i = 0; i < bytes.length; i++) {
      if (i + 1 < bytes.length && bytes[i] == bytes[i + 1]) {
        duplicateCount++;
        i++;
      }

      if (i + 2 < bytes.length && bytes[i] + 1 == bytes[i + 1] && bytes[i + 1] + 1 == bytes[i + 2])
        hasStraight = true;
    }
    return hasStraight && duplicateCount >= 2;
  }
}
