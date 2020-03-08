package y2020.analysis;

import java.util.regex.Pattern;

public class TestPattern {

  private static final Pattern PATTERN_PROCEDURE_DIVISION_START = Pattern.compile(".*PROCEDURE[ 　]*DIVISION\\..*");
  private static final Pattern PATTERN_SECTION_START = Pattern.compile(".*SECTION\\..*");

  public static void main(String[] args) {

    String testTarget = "007400 　00-SAMPLE01 SECTION.";

    //    String testTarget = "007000PROCEDURE 　DIVISION.";

    //PROCEDURE DIVISIONの判定
    if (PATTERN_SECTION_START.matcher(testTarget).matches()) {
      System.out.println("true");
    } else {
      System.out.println("false");
    }

    String[] targetArg = testTarget.split("[ |　]");
    for (String str : targetArg) {
      if("".equals(str)) {
        continue;
      }
      System.out.println(str);
    }

  }

}
