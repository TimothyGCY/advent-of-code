package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day18;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

  @Test
  void testSolvePart1() throws IOException {
    Day18 d = new Day18(FileUtil.readFile(2015, 18), 6, 4);
    assertEquals(4, d.solvePart1());
  }

  @Test
  void testSolvePart2() throws IOException {
    Day18 d = new Day18(FileUtil.readFile(2015, 18), 6, 5);
    assertEquals(17, d.solvePart2());
  }
}
