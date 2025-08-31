package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day07;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

  private final List<TestCase> part1TestCases =
    List.of(new TestCase("d", 72), new TestCase("e", 507),
      new TestCase("f", 492), new TestCase("g", 114), new TestCase("h", 65412),
      new TestCase("i", 65079), new TestCase("x", 123), new TestCase("y", 456));

  @Test
  void solvePart1() throws IOException {
    List<String> content = FileUtil.readFile(2015, 7);
    for  (TestCase testCase : part1TestCases) {
      final Day07 solution = new Day07(content, testCase.target);
      assertEquals(testCase.expected, solution.solvePart1());
    }
  }

  private record TestCase(String target, int expected) {
  }
}
