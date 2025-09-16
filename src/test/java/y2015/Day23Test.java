package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day23;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {
  @Test
  void testSolvePart1() throws IOException {
    Day23 d = new Day23(FileUtil.readFile(2015, 23));
    assertEquals(2, d.solvePart1()[0]);
  }
}
