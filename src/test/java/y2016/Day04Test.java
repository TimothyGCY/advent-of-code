package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2016.Day04;

class Day04Test {

  @Test
  void testSolvePart1() throws IOException {
    Day04 d = new Day04(FileUtil.readFile(2016, 4));
    assertEquals(1514, d.solvePart1());
  }

}
