package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day13;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {
  @Test
  void testSolvePart1() throws IOException {
    final List<String> data = FileUtil.readFile(2015, 13);
    Day13 solution = new Day13(data);
    assertEquals(330, solution.solvePart1());
  }

  @Test
  void testSolvePart2() throws IOException {
    final List<String> data = FileUtil.readFile(2015, 13);
    Day13 solution = new Day13(data);
    assertEquals(286, solution.solvePart2());
  }
}
