package y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.y2015.Day08;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    private static List<String> data;

    @BeforeAll
    static void init() throws IOException {
        data = FileUtil.readFile(2015, 8);
    }

    @Test
    void testSolvePart1() {
        Day08 solution = new Day08(data);
        assertEquals(12, solution.solvePart1());
    }

}
