package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Day02 extends Solution {

    private final List<String> data;

    public Day02(List<String> data) {
        this.data = data;
    }

    public static void main(String[] args) throws IOException {
        Day02 day02 = new Day02(FileUtil.readFile(2015, 2));
        System.out.println(day02.solvePart1());
        System.out.println(day02.solvePart2());
    }

    @Override
    public int solvePart1() {
        super.solvePart1();
        int total = 0;
        for (String line : data) {
            String[] parts = line.split("x");
            int l = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            int h = Integer.parseInt(parts[2]);
            int m = Stream.of(l * w, w * h, h * l).min(Integer::compareTo).orElseThrow();
            total += (2 * l * w + 2 * w * h + 2 * h * l) + m;
            // 1598415
        }
        return total;
    }

    @Override
    public int solvePart2() {
        super.solvePart2();
        int total = 0;
        for (String line : data) {
            String[] parts = line.split("x");
            int l = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            int h = Integer.parseInt(parts[2]);
            int m = Stream.of(l * w, w * h, h * l).min(Integer::compareTo).orElseThrow();
            int x = -1;
            int y = -1;
            int z = -1;
            if (m == l * w) {
                x = l;
                y = w;
                z = h;
            } else if (m == w * h) {
                x = w;
                y = h;
                z = l;
            } else if (m == h * l) {
                x = h;
                y = l;
                z = w;
            }
            int p = 2 * (x + y);
            int a = x * y * z;
            total += p + a;
            // 3829537
        }
        return total;
    }
}
