package com.bleckshiba.y2025;

import com.bleckshiba.Solution;

public class Day02 extends Solution<Long> {
  private final String input;

  public Day02(String input) {
    this.input = input;
  }

  static void main() {
    Day02 solution = new Day02(
      "851786270-851907437,27-47,577-1044,1184-1872,28214317-28368250,47766-78575,17432-28112,2341-4099,28969-45843,5800356-5971672,6461919174-6461988558,653055-686893,76-117,2626223278-2626301305,54503501-54572133,990997-1015607,710615-802603,829001-953096,529504-621892,8645-12202,3273269-3402555,446265-471330,232-392,179532-201093,233310-439308,95134183-95359858,3232278502-3232401602,25116215-25199250,5489-8293,96654-135484,2-17");
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Long solvePart1() {
    return solve(false);
  }

  @Override
  public Long solvePart2() {
    return solve(true);
  }

  private Long solve(boolean repeating) {
    long sum = 0;

    for (String range : input.split(",")) {
      String[] parts = range.split("-");
      long start = Long.parseLong(parts[0]);
      long end = Long.parseLong(parts[1]);
      for (long i = start; i <= end; i++) {
        String str = Long.toString(i);
        boolean isInvalid = repeating ? checkRepeatingAtLeastTwice(str) : checkRepeatOnce(str);
        if (isInvalid)
          sum += i;
      }
    }

    return sum;

  }

  private boolean checkRepeatOnce(String num) {
    int mid = num.length() / 2;
    String a = num.substring(0, mid);
    String b = num.substring(mid);
    return a.equals(b);
  }

  private boolean checkRepeatingAtLeastTwice(String num) {
    int length = num.length();
    for (int i = 1; i <= length / 2; i++) {
      if (length % i == 0 && checkDuplicateSequence(num, i))
        return true;
    }
    return false;
  }

  private boolean checkDuplicateSequence(String num, int size) {
    String sub = num.substring(0, size);
    for (int i = size; i < num.length(); i += size) {
      if (!sub.equals(num.substring(i, i + size)))
        return false;
    }
    return true;
  }
}
