package com.bleckshiba.y2016;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.bleckshiba.Solution;

public class Day05 extends Solution<String> {

  private final MessageDigest md5Digest;

  private final String input;

  public Day05(String input) throws NoSuchAlgorithmException {
    this.input = input;
    this.md5Digest = MessageDigest.getInstance("MD5");
  }

  public static void main(String[] args) throws NoSuchAlgorithmException {
    Day05 d = new Day05("reyedfim");
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public String solvePart1() {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    for (int i = 0; i < 8; i++) {
      while (true) {
        String temp = input + index++;
        String hash = md5(temp);
        if (hash.startsWith("00000")) {
          sb.append(hash.charAt(5));
          break;
        }
      }
    }
    return sb.toString();
  }

  @Override
  public String solvePart2() {
    Character[] chars = new Character[8];
    Arrays.fill(chars, '-');
    int index = 0;
    while (true) {
      while (true) {
        String temp = input + index++;
        String hash = md5(temp);
        if (hash.startsWith("00000")) {
          int i = Integer.parseInt(hash.substring(5, 6), 16);
          if (isBetween(i, 0, 7) && chars[i] == '-')
            chars[i] = hash.charAt(6);
          break;
        }
      }
      if (Arrays.stream(chars).noneMatch(c -> c == '-'))
        break;
    }

    return Arrays.stream(chars).map(c -> Character.toString(c)).collect(Collectors.joining(""));
  }

  private String md5(String input) {
    return toHex(md5Digest.digest(input.getBytes()));
  }

  private String toHex(byte[] bytes) {
    BigInteger bi = new BigInteger(1, bytes);
    return String.format("%032x", bi);
  }

  private boolean isBetween(int a, int min, int max) {
    return a >= min && a <= max;
  }
}
