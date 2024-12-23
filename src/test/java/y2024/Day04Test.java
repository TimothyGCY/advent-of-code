package y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2024.Day04;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

  private static Day04 day04;

  @BeforeAll
  static void setUpBeforeClass() throws IOException {
    List<String> content = FileUtil.readFile(2024, 4);
    char[][] data = new char[content.size()][content.getFirst().length()];
    for (int i = 0; i < data.length; i++) data[i] = content.get(i).toCharArray();
    day04 = new Day04(data);
  }

  @Test
  void testSolvePart1() {
    assertEquals(18, day04.solvePart1());
  }

  @Test
  void testSolvePart2() {
    assertEquals(9, day04.solvePart2());
  }
}
