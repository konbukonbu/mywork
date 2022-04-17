package y2022.csv;

public class SampleDataEntity {

  /** ファイル区分*/
  private String fileKbn = "";

  /** 商品コード*/
  private String shohinCode = "";
  
  /** 商品名*/
  private String shohinName = "";
  
  /** 商品価格*/
  private String shohinPrice = "";

  public String getFileKbn() {
    return fileKbn;
  }

  public void setFileKbn(String fileKbn) {
    this.fileKbn = fileKbn;
  }

  public String getShohinCode() {
    return shohinCode;
  }

  public void setShohinCode(String shohinCode) {
    this.shohinCode = shohinCode;
  }

  public String getShohinName() {
    return shohinName;
  }

  public void setShohinName(String shohinName) {
    this.shohinName = shohinName;
  }

  public String getShohinPrice() {
    return shohinPrice;
  }

  public void setShohinPrice(String shohinPrice) {
    this.shohinPrice = shohinPrice;
  }

}
