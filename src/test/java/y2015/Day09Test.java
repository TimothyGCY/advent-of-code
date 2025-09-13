package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day09;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {

  @Test
  void testSolvePart1() throws IOException {
    List<String> content = FileUtil.readFile(2015, 9);
    Day09 solution = new Day09(content);
    assertEquals(605,  solution.solvePart1());
  }

  @Test
  void  testSolvePart2() throws IOException {
    List<String> content = FileUtil.readFile(2015, 9);
    Day09 solution = new Day09(content);
    assertEquals(982,  solution.solvePart2());
  }
}
