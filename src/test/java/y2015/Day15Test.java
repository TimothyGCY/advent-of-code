package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day15;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

  @Test
  void testSolvePart1() throws IOException {
    final List<String> data = FileUtil.readFile(2015, 15);
    Day15 d = new Day15(data);
    assertEquals(62842880, d.solvePart1());
  }

  @Test
  void testSolvePart2() throws IOException {
    final List<String> data = FileUtil.readFile(2015, 15);
    Day15 d = new Day15(data);
    d.setTargetCalories(500);
    assertEquals(57600000, d.solvePart2());
  }

}
