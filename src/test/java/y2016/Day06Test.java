package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2016.Day06;

class Day06Test {
  @Test
  void testDay06() throws IOException {
    Day06 d = new Day06(FileUtil.readFile(2016, 6));
    assertEquals("easter", d.solvePart1());
    assertEquals("advent", d.solvePart2());
  }

}
