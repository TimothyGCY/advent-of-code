package com.bleckshiba.y2015;

import com.bleckshiba.Solution;

public class Day20 extends Solution<Integer> {

  private final int input;

  public Day20(int input) {
    this.input = input;
  }

  public static void main(String[] args) {
    Day20 d = new Day20(33100000);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int maxHouse = 1_000_000; // sampling, takes longer time if using `input`
    long[] houses = new long[maxHouse];
    for (int i = 1; i < maxHouse; i++)
      for (int j = i; j < maxHouse; j += i)
        houses[j] += i * 10L;

    for (int i = 0; i < houses.length; i++)
      if (houses[i] >= input)
        return i;

    return 0;
  }

  @Override
  public Integer solvePart2() {
    int maxHouse = 1_000_000;
    long[] houses = new long[maxHouse];
    for (int i = 1; i < maxHouse; i++) {
      int visited = 0;
      for (int j = i; j < maxHouse; j += i) {
        houses[j] += i * 11L;
        visited++;
        if (visited == 50)
          break;
      }
    }

    for (int i = 0; i < houses.length; i++)
      if (houses[i] >= input)
        return i;
    return 0;
  }
}
