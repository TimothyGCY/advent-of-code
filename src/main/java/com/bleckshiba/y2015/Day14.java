package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;

public class Day14 extends Solution<Integer> {

  private final List<String> data;

  private final int time;

  private final List<Deer> deers = new ArrayList<>();

  private final Map<String, Integer> points = new HashMap<>();

  public Day14(List<String> data, int time) {
    this.data = data;
    this.time = time;
  }

  public static void main(String[] args) throws IOException {
    final List<String> data = FileUtil.readFile(2015, 14);
    final Day14 d = new Day14(data, 2503);
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    parseData();
    return findFastestDeer().getDistance();
  }

  @Override
  public Integer solvePart2() {
    parseData();
    findFastestDeer();
    int highest = 0;
    for (Map.Entry<String, Integer> entry : points.entrySet()) {
      highest = Math.max(highest, entry.getValue());
    }
    return highest;
  }

  private void parseData() {
    deers.clear();
    points.clear();
    for (String line : data) {
      String[] parts = line.split(" ");
      Deer deer = new Deer(parts[0], Integer.parseInt(parts[3]), Integer.parseInt(parts[6]),
        Integer.parseInt(parts[13]));
      deers.add(deer);
    }
  }

  private Deer findFastestDeer() {
    Deer fastest = null;
    for (int i = 0; i < time; i++) {
      for (Deer deer : deers) {
        deer.travel();
      }
      fastest = deers.stream().max(Comparator.comparingInt(Deer::getDistance)).orElseThrow();
      computePoints(fastest.getDistance());
    }
    if (fastest == null)
      System.exit(0);
    return fastest;
  }

  private void computePoints(int distance) {
    List<Deer> leadingDeers = deers.stream().filter(a -> a.getDistance() == distance).toList();
    for (Deer deer : leadingDeers) {
      int point = points.getOrDefault(deer.getName(), 0) + 1;
      points.put(deer.getName(), point);
    }
  }

  private static class Deer {

    private final String name;

    private final int speed;

    private final int flyDuration;

    private final int restDuration;

    private boolean rest = false;

    private int distance = 0;

    private int remainingTime;

    public Deer(String name, int speed, int flyDuration, int restDuration) {
      this.name = name;
      this.speed = speed;
      this.flyDuration = flyDuration;
      this.restDuration = restDuration;
      this.remainingTime = flyDuration;
    }

    public void travel() {
      if (!rest) {
        distance += speed;
      }
      this.remainingTime--;
      if (remainingTime == 0) {
        remainingTime = this.rest ? flyDuration : restDuration;
        this.rest = !this.rest;
      }
    }

    public String getName() {
      return name;
    }

    public int getDistance() {
      return distance;
    }
  }
}
