package com.bleckshiba.y2015;

import com.bleckshiba.Solution;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 extends Solution<Integer> {

  // secret key
  private final String secret;

  public Day04(String secret) {
    this.secret = secret;
  }

  public static void main(String[] args) {
    Day04 d = new Day04("ckczppom");
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    int i = 1;
    while (true) {
      String val = String.format("%s%d", secret, i);
      try {
        String hash = getMd5Hash(val);
        if (hash.startsWith("00000")) break;
        i++;
      } catch (NoSuchAlgorithmException e) {
        System.exit(0);
      }
    }

    return i;
  }

  @Override
  protected Integer solvePart2() {
    int i = 1;
    while (true) {
      String val = String.format("%s%d", secret, i);
      try {
        String hash = getMd5Hash(val);
        if (hash.startsWith("000000")) break;
        i++;
      } catch (NoSuchAlgorithmException e) {
        System.exit(0);
      }
    }

    return i;
  }

  private String getMd5Hash(String src) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(src.getBytes());
    byte[] digest = md.digest();
    return String.format("%032x", new BigInteger(1, digest));
  }
}
