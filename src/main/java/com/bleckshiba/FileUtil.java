package com.bleckshiba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  private FileUtil() {}

  public static List<String> readFile(int year, int day) throws IOException {
    final List<String> content = new ArrayList<>();
    String filePath = String.format("/y%d/day%02d.txt", year, day);
    try (InputStream is = FileUtil.class.getResourceAsStream(filePath)) {
      assert is != null;
      try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
        String line;
        while ((line = br.readLine()) != null) {
          content.add(line);
        }
      }
    }
    return content;
  }
}
