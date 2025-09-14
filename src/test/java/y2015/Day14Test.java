package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day14;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {
  @Test
  void testSolvePart1() throws IOException {
    List<String> data = FileUtil.readFile(2015, 14);
    Day14 d = new Day14(data, 1000);
    assertEquals(1120, d.solvePart1());
  }

  @Test
  void testSolvePart2() throws IOException {
    List<String> data = FileUtil.readFile(2015, 14);
    Day14 d = new Day14(data, 1000);
    assertEquals(689, d.solvePart2());
  }
}
