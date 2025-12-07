package y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bleckshiba.y2025.Day03;

class Day03Test {

  private static final String INPUT = """
    987654321111111
    811111111111119
    234234234234278
    818181911112111
    """;

  private static Day03 solution;

  @BeforeAll
  static void setup() {
    solution = new Day03(INPUT.lines().toList());
  }

  @Test
  void testSolvePart1() {
    assertEquals(357, solution.solvePart1());
  }

  @Test
  void testSolvePart2() {
    assertEquals(0, solution.solvePart2());
  }

  @Test
  void testGetMax() {
    assertEquals(98, solution.getLargestCombination("987654321111111", 2));
    assertEquals(89, solution.getLargestCombination("811111111111119", 2));
    assertEquals(78, solution.getLargestCombination("234234234234278", 2));
    assertEquals(92, solution.getLargestCombination("818181911112111", 2));
  }

  @Test
  void testGetLargestCombination() {
    assertEquals(987654321111L, solution.getLargestCombination("987654321111111", 12));
    assertEquals(811111111119L, solution.getLargestCombination("811111111111119", 12));
    assertEquals(434234234278L, solution.getLargestCombination("234234234234278", 12));
    assertEquals(888911112111L, solution.getLargestCombination("818181911112111", 12));
  }
}
