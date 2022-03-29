package y2022.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorld {
  //  String _ = "abc";//java8じゃないとだめjava11からNG
  public static void main(String[] args) throws Exception {
    System.out.println("テスト！！");
    SampleDto dto = new SampleDto();
    dto.setKey1(null);
    dto.setKey2("value2");
    
    ChildSampleDto childDto = new ChildSampleDto();
    childDto.setKey3("value3");
    childDto.setKey4("");
    dto.setChildDto(childDto);
    
    ObjectMapper mapper = new ObjectMapper();
    mapper = mapper.setSerializationInclusion(Include.NON_NULL);//nullなら出力しない
    //{"key2":"value2","childDto":{"key3":"value3","key4":""}}
    
    String json = mapper.writeValueAsString(dto);
    System.out.println(json);

  }

}
