package y2022.text;

import java.net.InetAddress;

import org.apache.commons.text.StringSubstitutor;

public class StringSubstituteSample {

  public static void main(String[] args) throws Exception {
    // TODO 自動生成されたメソッド・スタブ
    meth01();
  }

  //https://github.com/apache/commons-text/blob/master/src/test/java/org/apache/commons/text/StringSubstitutorWithInterpolatorStringLookupTest.java
  private static void meth01() throws Exception {
    final StringSubstitutor strSubst = StringSubstitutor.createInterpolator();
    final String input = "Javaのバージョン：${java:version}、システム日付：${date:yyyy-MM-dd}";
    String ret = strSubst.replace(input);
    System.out.println(input);//Javaのバージョン：${java:version}、システム日付：${date:yyyy-MM-dd}
    System.out.println(ret);//Javaのバージョン：Java version 11.0.13、システム日付：2022-10-24
  }
  
  private static void meth02() throws Exception {
    final StringSubstitutor strSubst = StringSubstitutor.createInterpolator();
    final String hostName = InetAddress.getLocalHost().getHostName();
    final String input = "${dns:" + hostName + "}";
    String ret = strSubst.replace(input);
    System.out.println(input);//${dns:DESKTOP-DJUR1EN}
    System.out.println(ret);//192.168.11.100
  }
}
