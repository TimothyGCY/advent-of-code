package y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bleckshiba.y2025.Day04;

class Day04Test {

  private static final String INPUT = """
    ..@@.@@@@.
    @@@.@.@.@@
    @@@@@.@.@@
    @.@@@@..@.
    @@.@@@@.@@
    .@@@@@@@.@
    .@.@.@.@@@
    @.@@@.@@@@
    .@@@@@@@@.
    @.@.@@@.@.
    """;

  private static Day04 solution;

  @BeforeAll
  static void setup() {
    solution = new Day04(INPUT.lines().toList());
  }

  @Test
  void testSolvePart1() {
    assertEquals(13, solution.solvePart1());
  }

  @Test
  void testSolvePart2() {
    assertEquals(43, solution.solvePart2());
  }
}
