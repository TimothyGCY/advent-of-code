package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day24;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {
  @Test
  void testSolvePart1() throws IOException {
    Day24 d = new Day24(FileUtil.readFile(2015, 24)
      .stream().map(Integer::parseInt).toList());
    assertEquals(99,  d.solvePart1());
  }
}
