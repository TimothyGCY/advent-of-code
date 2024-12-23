package y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2024.Day09;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

  private static Day09 day09;

  @BeforeAll
  public static void init() throws IOException {
    day09 = new Day09(FileUtil.readFile(2024, 9).getFirst());
  }

  @Test
  void testComputeDisk() {
    assertEquals("00...111...2...333.44.5555.6666.777.888899", day09.computeDisk());
  }

  @Test
  void testDefrag() {
    assertEquals("0099811188827773336446555566..............", day09.defrag(day09.computeDisk()));
  }

  @Test
  void testSolvePart1() {
    assertEquals(1928L, day09.solvePart1());
  }
}
