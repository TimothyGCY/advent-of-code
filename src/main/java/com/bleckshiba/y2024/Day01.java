package com.bleckshiba.y2024;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;

public class Day01 extends Solution {

    private final List<Integer> left = new ArrayList<>();

    private final List<Integer> right = new ArrayList<>();

    public Day01(List<String> data) {
        for (String line : data) {
            String[] parts = line.split(" {3}");
            int l = Integer.parseInt(parts[0]);
            int r = Integer.parseInt(parts[1]);
            this.left.add(l);
            this.right.add(r);
        }
        this.left.sort(Integer::compareTo);
        this.right.sort(Integer::compareTo);
    }

    public static void main(String[] args) throws IOException {
        List<String> content = FileUtil.readFile(2024, 1);
        Day01 solution = new Day01(content);
        System.out.println(solution.solvePart1());
        System.out.println(solution.solvePart2());
    }

    @Override
    protected int solvePart1() {
        super.solvePart1();
        int total = 0;
        for (int i = 0; i < left.size(); i++) {
            total += Math.abs(left.get(i) - right.get(i));
        }

        return total;
    }

    @Override
    protected int solvePart2() {
        super.solvePart2();
        int total = 0;
        for (int l : left) {
            total += l * right.stream().filter(x -> x.equals(l)).toList().size();
        }
        return total;
    }
}
