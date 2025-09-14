package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day15 extends Solution<Long> {
  private final List<String> data;

  private final List<Recipe> recipes = new ArrayList<>();

  private long maxScore = 0;

  private int targetCalories = 0;

  public Day15(List<String> data) {
    this.data = data;
  }

  public void setTargetCalories(int targetCalories) {
    this.targetCalories = targetCalories;
  }

  public static void main(String[] args) throws IOException {
    final List<String> data = FileUtil.readFile(2015, 15);
    Day15 d = new Day15(data);
    System.out.println(d.solvePart1());
    d.setTargetCalories(500);
    System.out.println(d.solvePart2());
  }

  @Override
  public Long solvePart1() {
    parseData();
    maxScore = 0;
    int[] quantity = new int[recipes.size()];
    findBestCookie(0, 100, quantity);
    return maxScore;
  }

  @Override
  public Long solvePart2() {
    return solvePart1();
  }

  private void parseData() {
    recipes.clear();
    for (String line : data) {
      String[] parts = line.split(": |, | ");
      String name = parts[0];
      int capacity = Integer.parseInt(parts[2]);
      int durability = Integer.parseInt(parts[4]);
      int flavor = Integer.parseInt(parts[6]);
      int texture = Integer.parseInt(parts[8]);
      int calories = Integer.parseInt(parts[10]);
      recipes.add(new Recipe(name, capacity, durability, flavor, texture, calories));
    }
  }

  private void findBestCookie(int ingredientIndex, int remainingTablespoons, int[] quantities) {
    if (ingredientIndex == recipes.size() - 1) {
      quantities[ingredientIndex] = remainingTablespoons;

      long currentScore = calculateScore(quantities);
      if (currentScore > maxScore)
        maxScore = currentScore;
      return;
    }

    for (int i = 0; i <= remainingTablespoons; i++) {
      quantities[ingredientIndex] = i;
      findBestCookie(ingredientIndex + 1, remainingTablespoons - i, quantities);
    }
  }

  private long calculateScore(int[] quantities) {
    long capacity = 0;
    long durability = 0;
    long flavor = 0;
    long texture = 0;
    long totalCalories = 0;

    for (int i = 0; i < recipes.size(); i++) {
      Recipe r = recipes.get(i);
      capacity += (long) r.capacity() * quantities[i];
      durability += (long) r.durability() * quantities[i];
      flavor += (long) r.flavor() * quantities[i];
      texture += (long) r.texture() * quantities[i];
      totalCalories += (long) r.calories() * quantities[i];
    }

    if (targetCalories > 0 && totalCalories != targetCalories)
      return 0;

    if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0)
      return 0;

    return capacity * durability * flavor * texture;
  }

  private record Recipe(String name, Integer capacity, Integer durability, Integer flavor,
    Integer texture, Integer calories) {
  }
}
