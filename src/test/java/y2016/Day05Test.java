package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.bleckshiba.y2016.Day05;

class Day05Test {

  @Test
  void testSolveDay05() throws NoSuchAlgorithmException {
    Day05 d = new Day05("abc");
    assertEquals("18f47a30", d.solvePart1());

    assertEquals("05ace8e3", d.solvePart2());
  }

}
