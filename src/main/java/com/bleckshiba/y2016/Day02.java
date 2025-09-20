package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day02 extends Solution<String> {
  private static final String[][] NORMAL_KEYPAD = new String[][] {
    {"1", "2", "3"},
    {"4", "5", "6"},
    {"7", "8", "9"}
  };

  private static final String[][] ACTUAL_KEYPAD = new String[][] {
    {"", "", "1", "", ""},
    {"", "2", "3", "4", ""},
    {"5", "6", "7", "8", "9"},
    {"", "A", "B", "C", ""},
    {"", "", "D", "", ""},
  };

  private final List<String> input;

  public Day02(List<String> input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    Day02 d = new Day02(FileUtil.readFile(2016, 2));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public String solvePart1() {
    final int[] startingPoint = new int[] {1, 1};

    StringBuilder result = new StringBuilder();
    for (String line : input) {
      getNextPinPosition(line.split(""), startingPoint, 2, NORMAL_KEYPAD);
      result.append(NORMAL_KEYPAD[startingPoint[1]][startingPoint[0]]);
    }
    return result.toString();
  }

  @Override
  public String solvePart2() {
    final int[] startingPoint = new int[] {0, 2};
    StringBuilder result = new StringBuilder();
    for (String line : input) {
      getNextPinPosition(line.split(""), startingPoint, 4, ACTUAL_KEYPAD);
      result.append(ACTUAL_KEYPAD[startingPoint[1]][startingPoint[0]]);
    }
    return result.toString();
  }

  private void getNextPinPosition(String[] parts, int[] startingPoint, int size,
    String[][] keypad) {
    for (String part : parts) {
      switch (part) {
        case "U" -> {
          int nextIndex = startingPoint[1] - (startingPoint[1] == 0 ? 0 : 1);
          if (!keypad[nextIndex][startingPoint[0]].isEmpty())
            startingPoint[1] = nextIndex;
        }
        case "D" -> {
          int nextIndex = startingPoint[1] + (startingPoint[1] == size ? 0 : 1);
          if (!keypad[nextIndex][startingPoint[0]].isEmpty())
            startingPoint[1] = nextIndex;
        }
        case "R" -> {
          int nextIndex =
            startingPoint[0] + (startingPoint[0] == size ? 0 : 1);
          if (!keypad[startingPoint[1]][nextIndex].isEmpty())
            startingPoint[0] = nextIndex;
        }
        default -> {
          int nextIndex = startingPoint[0] - (startingPoint[0] == 0 ? 0 : 1);
          if (!keypad[startingPoint[1]][nextIndex].isEmpty())
            startingPoint[0] = nextIndex;
        }
      }
    }
  }
}
