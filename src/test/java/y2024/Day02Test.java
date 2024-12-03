package y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2024.Day02;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    private static Day02 day02;

    @BeforeAll
    static void init() throws IOException {
        day02 = new Day02(FileUtil.readFile(2024,2));
    }

    @Test
    void testSolvePart1() {
        assertEquals(2, day02.solvePart1());
    }

    @Test
    void testSolvePart2() {
        assertEquals(4, day02.solvePart2());
    }

}
