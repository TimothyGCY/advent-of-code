package com.bleckshiba.y2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

public class Day07 extends Solution<Integer> {

  private final List<String> input;

  public Day07(List<String> input) {
    this.input = input;
  }

  static void main() throws IOException {
    Day07 d = new Day07(FileUtil.readFile(2016, 7));
    System.out.println(d.solvePart1());
    System.out.println(d.solvePart2());
  }

  @Override
  public Integer solvePart1() {
    return Math.toIntExact(input.stream().map(IPv7::new).filter(IPv7::isTLSSupported).count());
  }

  @Override
  public Integer solvePart2() {
    return Math.toIntExact(input.stream().map(IPv7::new).filter(IPv7::isSSLSupported).count());
  }

  private static class IPv7 {
    private final String ip;

    private boolean isTLSSupported = false;

    private boolean isSSLSupported = false;

    public IPv7(String ip) {
      this.ip = ip;
      validateIp();
    }

    private void validateIp() {
      checkIsTlsSupported();
      checkIsSslSupported();
    }

    private void checkIsTlsSupported() {
      String[] parts = ip.split("[\\[\\]]");
      boolean supernetHasAbba = false;
      boolean hypernetHasAbba = false;

      for (int i = 0; i < parts.length; i++) {
        boolean hasAbba = hasABBA(parts[i]);
        if (i % 2 == 0 && hasAbba)
          supernetHasAbba = true;
        else if (i % 2 == 1 && hasAbba)
          hypernetHasAbba = true;
      }

      this.isTLSSupported = supernetHasAbba && !hypernetHasAbba;
    }

    private void checkIsSslSupported() {
      String[] parts = ip.split("[\\[\\]]");
      List<String> supernets = new ArrayList<>();
      List<String> hypernets = new ArrayList<>();
      for (int i = 0; i < parts.length; i++) {
        if (i % 2 == 0)
          supernets.add(parts[i]);
        else
          hypernets.add(parts[i]);
      }

      final List<String> abs = new ArrayList<>();
      for (String s : supernets)
        abs.addAll(getABAs(s));

      for (String hypernet : hypernets) {
        if (hasBAB(hypernet, abs)) {
          this.isSSLSupported = true;
          return;
        }
      }
    }

    public String getIp() {
      return ip;
    }

    public boolean isTLSSupported() {
      return isTLSSupported;
    }

    public boolean isSSLSupported() {
      return isSSLSupported;
    }

    private boolean hasABBA(String part) {
      for (int i = 0; i < part.length() - 3; i++) {
        char a = part.charAt(i);
        char b = part.charAt(i + 1);
        char c = part.charAt(i + 2);
        char d = part.charAt(i + 3);
        if (a == d && b == c && a != b)
          return true;
      }
      return false;
    }

    private List<String> getABAs(String part) {
      List<String> abas = new ArrayList<>();
      for (int i = 0; i < part.length() - 2; i++) {
        char a = part.charAt(i);
        char b = part.charAt(i + 1);
        char c = part.charAt(i + 2);
        if (a == c && a != b)
          abas.add(String.format("%c%c", a, b));
      }
      return abas;
    }

    private boolean hasBAB(String part, List<String> abs) {
      return abs.stream().anyMatch(
        ab -> part.contains(String.format("%c%c%c", ab.charAt(1), ab.charAt(0), ab.charAt(1))));
    }
  }
}
