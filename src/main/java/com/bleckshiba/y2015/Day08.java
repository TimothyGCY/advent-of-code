package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.List;

public class Day08 extends Solution<Integer> {

  private final List<String> data;

  public Day08(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    Day08 solution = new Day08(FileUtil.readFile(2015, 8));
    System.out.println(solution.solvePart1()); // 1371
    System.out.println(solution.solvePart2()); // 2117
  }

  @Override
  public Integer solvePart1() {
    int charCount = data.stream().map(String::length).reduce(0, Integer::sum);
    List<String> escaped = data.stream()
      .map(this::escapeString)
      .toList();
    int memCount = escaped.stream().map(String::length).reduce(0, Integer::sum);
    return charCount - memCount;
  }

  @Override
  public Integer solvePart2() {
    int charCount = data.stream().map(String::length).reduce(0, Integer::sum);
    List<String> escaped = data.stream()
      .map(this::encodeString)
      .toList();
    int memCount = escaped.stream().map(String::length).reduce(0, Integer::sum);
    return Math.abs(charCount - memCount);
  }

  private String escapeString(String input) {
    if (input == null || input.length() < 2 || !input.startsWith("\"") || !input.endsWith("\"")) {
      return "";
    }
    return escape(input);
  }

  private String escape(String input) {
    String trimmed = input.substring(1, input.length() - 1);
    StringBuilder result = new StringBuilder();

    int i = 0;
    for (; i < trimmed.length(); i++) {
      char currentChar = trimmed.charAt(i);
      if (!(currentChar == '\\' && i + 1 < trimmed.length())) {
        result.append(currentChar);
        continue;
      }
      char nextChar = trimmed.charAt(++i);
      switch (nextChar) {
        case '\\', '"':
          result.append(nextChar);
          break;
        case 'x':
          if (i + 2 < trimmed.length()) {
            try {
              String hex = trimmed.substring(i + 1, i + 3);
              int charCode = Integer.parseInt(hex, 16);
              result.append((char) charCode);
              i += 2;
            } catch (NumberFormatException e) {
              result.append('\\').append(nextChar);
            }
          } else {
            result.append('\\').append(nextChar);
          }
          break;
        default:
          result.append('\\').append(nextChar);
          break;
      }
    }
    return result.toString();
  }

  private String encodeString(String input) {
    StringBuilder encoded = new StringBuilder("\"");
    for (char c : input.toCharArray()) {
      if (c == '"') {
        encoded.append("\\\"");
      } else if (c == '\\') {
        encoded.append("\\\\");
      } else {
        encoded.append(c);
      }
    }
    encoded.append("\"");
    return encoded.toString();
  }
}
