package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;

public class Day08 extends Solution {

  private final char[][] map;

  public Day08(char[][] map) {
    this.map = map;
  }

  public static void main(String[] args) throws IOException {
    List<String> content = FileUtil.readFile(2024, 8);
    char[][] map = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < content.size(); i++) {
      map[i] = content.get(i).toCharArray();
    }
    Day08 solution = new Day08(map);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public int solvePart1() {
    Set<String> nodes = new HashSet<>();
    Map<Character, Antenna> antennas = init();
    for (Antenna antenna : antennas.values()) {
      nodes.addAll(antenna.placeAntiNodes(false));
    }
    return nodes.size();
  }

  @Override
  public int solvePart2() {
    Set<String> nodes = new HashSet<>();
    Map<Character, Antenna> antennas = init();
    for (Antenna antenna : antennas.values()) {
      nodes.addAll(antenna.placeAntiNodes(true));
    }
    return nodes.size();
  }

  private Map<Character, Antenna> init() {
    Map<Character, Antenna> antennas = new HashMap<>();
    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[0].length; col++) {
        char c = map[row][col];
        if (c == '.' || c == '#') continue;
        Antenna antenna;
        if (antennas.containsKey(c)) {
          antenna = antennas.get(c);
        } else {
          antenna = new Antenna(new int[] {map.length, map[0].length});
        }
        antenna.addLocation(new int[] {row, col});
        antennas.put(c, antenna);
      }
    }
    return antennas;
  }

  private static class Antenna {
    private static final String COORD = "%d,%d";

    private final int[] mapSize;

    private final List<int[]> locations = new ArrayList<>();

    public Antenna(int[] mapSize) {
      this.mapSize = mapSize;
    }

    public List<int[]> getLocations() {
      return locations;
    }

    public void addLocation(int[] location) {
      locations.add(location);
    }

    public Set<String> placeAntiNodes(boolean continuous) {
      Set<String> pos = new HashSet<>();
      for (int i = 0; i < locations.size() - 1; i++) {
        int[] cur = locations.get(i);
        for (int j = i + 1; j < locations.size(); j++) {
          int[] next = locations.get(j);
          int multiplier = continuous ? 0 : 1;
          while (true) {
            int dy = (next[0] - cur[0]) * multiplier;
            int dx = (next[1] - cur[1]) * multiplier;

            int px = cur[1] - dx;
            int py = cur[0] - dy;
            boolean conditionA = py >= 0 && isWithin(px, mapSize[1]);
            if (conditionA) {
              pos.add(String.format(COORD, py, px));
            }

            px = next[1] + dx;
            py = next[0] + dy;
            boolean conditionB = py < mapSize[0] && isWithin(px, mapSize[1]);
            if (conditionB) {
              pos.add(String.format(COORD, py, px));
            }

            if (!continuous || (!conditionA && !conditionB)) break;
            multiplier++;
          }
        }
      }
      return pos;
    }

    private boolean isWithin(int a, int size) {
      return a >= 0 && a < size;
    }

    @Override
    public String toString() {
      return "Antenna{" + "locations=" + locations.stream().map(Arrays::toString).toList() + '}';
    }
  }
}
