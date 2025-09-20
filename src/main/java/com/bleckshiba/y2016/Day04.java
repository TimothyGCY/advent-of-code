package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day04 extends Solution<Long> {

  private final List<String> input;

  private final List<Room> part2 = new ArrayList<>();

  public Day04(List<String> input) {
    this.input = input;
  }

  public static void main(String[] args) throws IOException {
    Day04 d = new Day04(FileUtil.readFile(2016, 4));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Long solvePart1() {
    long sum = 0;
    for (String line : input) {
      Room room =  new Room(line);
      if (room.isRealRoom())
        sum += room.id;
    }

    return sum;
  }

  @Override
  public Long solvePart2() {
    for (String line : input) {
      Room room =  new Room(line);
      if (room.isRealRoom())
        part2.add(room);
    }

    for (Room r : part2) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i< r.getRooms().length; i++) {
        String rooms = r.getRooms()[i];
        for (int j = 0; j < rooms.length(); j++) {
          sb.append(rotateChar(r.getId(), rooms.charAt(j)));
        }
        sb.append("-");
      }
      if (sb.toString().startsWith("northpole"))
        return (long) r.getId();
    }

    return 0L;
  }

  private char rotateChar(int rotation, char c) {
    return (char) (((c - 'a') + rotation) % 26 + 'a');
  }

  private static class Room {

    private final String[] rooms;

    private final int id;

    private final String checksum;

    private final boolean isRealRoom;

    public Room(String input) {
      String[] parts = input.split("-");
      String last = parts[parts.length - 1];
      this.id = Integer.parseInt(last.substring(0, last.indexOf("[")));
      this.checksum = last.substring(last.indexOf("[") + 1, last.indexOf("]"));
      this.rooms = Arrays.copyOfRange(parts, 0, parts.length - 1);
      this.isRealRoom = checkRoom();
    }

    public String[] getRooms() {
      return rooms;
    }

    public int getId() {
      return id;
    }

    public boolean isRealRoom() {
      return isRealRoom;
    }

    private boolean checkRoom() {
      Map<Character, Integer> map = new TreeMap<>();
      for (String room : rooms) {
        room.chars().forEach(c -> map.put((char) c, map.getOrDefault((char) c, 0) + 1));
      }

      String top5 = map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .limit(5)
        .map(Map.Entry::getKey)
        .map(c -> Character.toString(c))
        .collect(Collectors.joining());

      return top5.equals(checksum);
    }
  }
}
