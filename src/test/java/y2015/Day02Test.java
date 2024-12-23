package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day02;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

  private static List<String> data;

  @BeforeAll
  static void init() throws IOException {
    data = FileUtil.readFile(2015, 2);
  }

  @Test
  void testSolvePart2() {
    Day02 d = new Day02(data);
    assertEquals(48, d.solvePart2());
  }
}
