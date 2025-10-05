package y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2016.Day07;

class Day07Test {
  @Test
  void testSolvePart1() throws IOException {
    Day07 d = new Day07(FileUtil.readFile(2016, 7));
    assertEquals(2, d.solvePart1());
  }

  @Test
  void testSolvePart2() {
    Day07 d = new Day07(List.of("aba[bab]xyz", "xyx[xyx]xyx", "aaa[kek]eke", "zazbz[bzb]cdb"));
    assertEquals(3, d.solvePart2());
  }
}
