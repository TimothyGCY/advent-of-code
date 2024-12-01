package y2015;

import com.bleckshiba.y2015.Day04;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    void testSolvePart1() {
        Day04 solution = new Day04("abcdef");
        assertEquals(609043, solution.solvePart1());
    }

}
