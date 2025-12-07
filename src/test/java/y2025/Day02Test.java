package y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bleckshiba.y2025.Day02;

class Day02Test {

  private static final String INPUT =
    "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

  private static Day02 solution;

  @BeforeAll
  static void setUp() {
    solution = new Day02(INPUT);
  }

  @Test
  void solvePart1() {
    assertEquals(1227775554, solution.solvePart1());
  }

  @Test
  void solvePart2() {
    assertEquals(4174379265L, solution.solvePart2());
  }
}
