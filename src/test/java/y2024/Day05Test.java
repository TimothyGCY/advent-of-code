package y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2024.Day05;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

  private static Day05 day05;

  @BeforeAll
  static void setUpBeforeClass() throws IOException {
    List<String> content = FileUtil.readFile(2024, 5);
    final int separator = content.indexOf("");
    final List<String> rules = content.subList(0, separator);
    final List<String> updates = content.subList(separator + 1, content.size());
    day05 = new Day05(rules, updates);
  }

  @Test
  void testSolvePart1() {
    assertEquals(143, day05.solvePart1());
  }

  @Test
  void testSolvePart2() {
    assertEquals(123, day05.solvePart2());
  }
}
