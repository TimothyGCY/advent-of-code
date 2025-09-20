package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2016.Day02;

class Day02Test {

  @Test
  void testSolvePart1() throws IOException {
    Day02 d = new Day02(FileUtil.readFile(2016, 2));
    assertEquals("1985", d.solvePart1());
  }

  @Test
  void testSolvePart2() throws IOException {
    Day02 d = new Day02(FileUtil.readFile(2016, 2));
    assertEquals("5DB3", d.solvePart2());
  }
}
