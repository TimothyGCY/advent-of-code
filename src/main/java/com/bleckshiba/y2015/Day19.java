package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Pair;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 extends Solution<Integer> {

  private final List<String> data;

  public Day19(List<String> data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    Day19 d = new Day19(FileUtil.readFile(2015, 19));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    Pair<List<String>, String> pair = parseData();

    Set<String> distinctMolecule = new HashSet<>();
    final String molecule = pair.right();
    for (String rule : pair.left()) {
      String[] parts = rule.split(" => ");
      String from = parts[0];
      String to = parts[1];
      Pattern pattern = Pattern.compile(from);
      Matcher matcher = pattern.matcher(molecule);

      while (matcher.find())
        distinctMolecule.add(
          molecule.substring(0, matcher.start()) + to + molecule.substring(matcher.end()));
    }

    return distinctMolecule.size();
  }

  @Override
  public Integer solvePart2() {
    Pair<List<String>, String> pair = parseData();
    List<String[]> reversedRules = new ArrayList<>();
    for (String rule : pair.left()) {
      String[] parts = rule.split(" => ");
      reversedRules.add(new String[] {parts[1], parts[0]});
    }
    reversedRules.sort((a, b) -> b[0].length() - a[0].length());

    int step = 0;
    String current = pair.right();
    while (!current.equals("e")) {
      for (String[] rule : reversedRules) {
        String from = rule[0];
        String to = rule[1];
        if (current.contains(from)) {
          current = current.replaceFirst(Pattern.quote(from), Matcher.quoteReplacement(to));
          step++;
          break;
        }
      }
    }
    return step;
  }

  private Pair<List<String>, String> parseData() {
    final List<String> rules = new ArrayList<>();
    String molecule = "";
    for (String line : this.data)
      if (line.contains("=>"))
        rules.add(line);
      else if (!line.isEmpty())
        molecule = line;
    return new Pair<>(rules, molecule);
  }
}
