package y2022.jackson;

import java.io.Serializable;

public class SampleDto implements Serializable{
  private String key1;
  private String key2;
  private ChildSampleDto childDto;

  public ChildSampleDto getChildDto() {
    return childDto;
  }
  public void setChildDto(ChildSampleDto childDto) {
    this.childDto = childDto;
  }
  public String getKey1() {
    return key1;
  }
  public void setKey1(String key1) {
    this.key1 = key1;
  }
  public String getKey2() {
    return key2;
  }
  public void setKey2(String key2) {
    this.key2 = key2;
  }
}
