package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 extends Solution<Long> {
  private final String data;

  public Day12(String data) {
    this.data = data;
  }

  public static void main(String[] args) throws IOException {
    String data = FileUtil.readFile(2015, 12).getFirst();
    Day12 solution = new Day12(data);
    System.out.println(solution.solvePart1());
    System.out.println(solution.solvePart2());
  }

  @Override
  public Long solvePart1() {
    long total = 0;
    Matcher matcher = Pattern.compile("-?\\d+").matcher(data);
    while (matcher.find()) {
      total += Long.parseLong(matcher.group());
    }
    return total;
  }

  @Override
  public Long solvePart2() {
    long total = 0;
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode root = mapper.readTree(data);
      total = sumNumbers(root);
    } catch (JsonProcessingException e) {
      System.err.println("Error while parsing json: " + e.getMessage());
    }
    return total;
  }

  private long sumNumbers(JsonNode node) {
    long sum = 0;
    if (node.isNumber())
      return node.asLong();

    if (node.isTextual() && "red".equals(node.asText()))
        return 0;

    if (node.isArray())
      for (JsonNode n : node)
        sum += sumNumbers(n);

    if (node.isObject()) {
      for (JsonNode child : node)
        if (child.isTextual() && "red".equals(child.asText()))
          return 0;

      for (JsonNode child : node)
        sum += sumNumbers(child);
    }

    return sum;
  }
}
