package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.bleckshiba.y2016.Day01;

class Day01Test {

  @Test
  void solveDay01() {
    Day01 a = new Day01();
    a.setInput("R2, L3");
    assertEquals(5, a.solvePart1());

    a.setInput("R2, R2, R2");
    assertEquals(2, a.solvePart1());

    a.setInput("R5, L5, R5, R3");
    assertEquals(12, a.solvePart1());

    a.setInput("R8, R4, R4, R8");
    assertEquals(4, a.solvePart2());
  }

}
