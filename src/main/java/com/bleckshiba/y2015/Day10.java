package com.bleckshiba.y2015;

import com.bleckshiba.Solution;

public class Day10 extends Solution<String> {

  private final String data;

  private int repetition;

  public Day10(String data, int repetition) {
    this.data = data;
    this.repetition = repetition;
  }

  public void setRepetition(int repetition) {
    this.repetition = repetition;
  }

  public static void main(String[] args) {
    Day10 solution = new Day10("1321131112", 40);
    System.out.println(solution.solvePart1().length());
    solution.setRepetition(50);
    System.out.println(solution.solvePart1().length());
  }

  @Override
  public String solvePart1() {
    String answer = data;
    for (int i = 1; i <= repetition; i++) {
      answer = read(answer);
    }
    return answer;
  }

  @Override
  public String solvePart2() {
    return "";
  }

  private String read(String input) {
    char prev = input.charAt(0);
    int count = 0;
    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      Character c = input.charAt(i);
      if (c.equals(prev)) {
        count++;
      } else {
        answer.append(String.format("%d%c", count, prev));
        count = 1;
        prev = c;
      }
    }
    answer.append(String.format("%d%c", count, prev));

    return answer.toString();
  }
}
