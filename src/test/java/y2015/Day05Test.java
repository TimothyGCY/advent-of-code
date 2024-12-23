package y2015;

import com.bleckshiba.y2015.Day05;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

  @Test
  void testSolvePart1() {
    String src =
        """
                ugknbfddgicrmopn
                aaa
                jchzalrnumimnmhp
                haegwjzuvuyypxyu
                dvszwmarrgswjxmb
                """;
    Day05 day05 = new Day05(Arrays.stream(src.split("\n")).toList());
    assertEquals(2, day05.solvePart1());
  }

  @Test
  void testSolvePart2() {
    String src =
        """
                qjhvhtzxzqqjkmpb
                xxyxx
                uurcxstgmygtbstg
                ieodomkazucvgmuy
                """;
    Day05 day05 = new Day05(Arrays.stream(src.split("\n")).toList());
    assertEquals(2, day05.solvePart2());
  }
}
