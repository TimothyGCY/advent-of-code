package y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2024.Day08;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

  private static Day08 day08;

  @BeforeAll
  public static void init() throws IOException {
    List<String> content = FileUtil.readFile(2024, 8);
    char[][] map = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < content.size(); i++) {
      map[i] = content.get(i).toCharArray();
    }
    day08 = new Day08(map);
  }

  @Test
  void testSolvePart1() {
    assertEquals(14, day08.solvePart1());
  }

  @Test
  void testCase2() {
    List<String> content =
        Arrays.asList(
            """
T....#....
...T......
.T....#...
.........#
..#.......
..........
...#......
..........
....#.....
..........
"""
                .split("\n"));
    char[][] map = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < content.size(); i++) {
      map[i] = content.get(i).toCharArray();
    }
    Day08 solution = new Day08(map);
    assertEquals(9, solution.solvePart2());
  }

  @Test
  void testSolvePart2() {
    assertEquals(34, day08.solvePart2());
  }
}
