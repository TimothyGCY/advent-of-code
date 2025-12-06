package y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bleckshiba.y2025.Day01;

class Day01Test {

  private static final String data = """
    L68
    L30
    R48
    L5
    R60
    L55
    L1
    L99
    R14
    L82
    """;

  @Test
  void testSolvePart1() {
    final List<String> instructions = List.of(data.split("\n"));
    final Day01 solution = new Day01(instructions);
    assertEquals(3, solution.solvePart1());
  }

  @Test
  void testSolvePart2() {
    final List<String> instructions = List.of(data.split("\n"));
    final Day01 solution = new Day01(instructions);
    assertEquals(6, solution.solvePart2());
  }

}
