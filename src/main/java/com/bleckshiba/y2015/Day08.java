package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.util.List;

public class Day08 extends Solution {

    private final List<String> data;

    public Day08(List<String> data) {
        this.data = data;
    }

    public static void main(String[] args) throws IOException {
        Day08 solution = new Day08(FileUtil.readFile(2015, 8));
        System.out.println(solution.solvePart1());
        System.out.println(solution.solvePart2());
    }

    @Override
    public int solvePart1() {
        super.solvePart1();
        int charCount = data.stream().map(String::length).reduce(0, Integer::sum);
        List<String> escaped = data
                .stream()
                .map(v -> v.substring(1, v.length() - 1))
                .map(StringEscapeUtils::unescapeJava).toList();
        System.out.println(escaped);
        int memCount = escaped.stream().map(String::length).reduce(0, Integer::sum);
        return charCount - memCount;
    }

    @Override
    protected int solvePart2() {
        super.solvePart2();
        return -1;
    }
}
