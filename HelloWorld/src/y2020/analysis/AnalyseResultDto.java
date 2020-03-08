package y2020.analysis;

public class AnalyseResultDto {

  private int 行番号;


  private boolean 流用可否 = false;

  private String ソースコード = "";

  private String 変換パターン名 = "";

  private boolean コメントフラグ = false;

  private String DIVISION名 = "";

  private String SECTION名 = "";

  private boolean DBアクセスフラグ = false;


  public int get行番号() {
    return 行番号;
  }

  public void set行番号(int 行番号) {
    this.行番号 = 行番号;
  }

  public boolean is流用可否() {
    return 流用可否;
  }

  public void set流用可否(boolean 流用可否) {
    this.流用可否 = 流用可否;
  }

  public String getソースコード() {
    return ソースコード;
  }

  public void setソースコード(String ソースコード) {
    this.ソースコード = ソースコード;
  }

  public String get変換パターン名() {
    return 変換パターン名;
  }

  public void set変換パターン名(String 変換パターン名) {
    this.変換パターン名 = 変換パターン名;
  }

  public boolean isコメントフラグ() {
    return コメントフラグ;
  }

  public void setコメントフラグ(boolean コメントフラグ) {
    this.コメントフラグ = コメントフラグ;
  }

  public String getDIVISION名() {
    return DIVISION名;
  }

  public void setDIVISION名(String dIVISION名) {
    DIVISION名 = dIVISION名;
  }

  public String getSECTION名() {
    return SECTION名;
  }

  public void setSECTION名(String sECTION名) {
    SECTION名 = sECTION名;
  }

  public boolean isDBアクセスフラグ() {
    return DBアクセスフラグ;
  }

  public void setDBアクセスフラグ(boolean dBアクセスフラグ) {
    DBアクセスフラグ = dBアクセスフラグ;
  }
}
