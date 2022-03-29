package y2022.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author こんぶおー🐧
 *@see https://qiita.com/opengl-8080/items/b613b9b3bc5d796c840c
 */
public class HelloWorld {
  //  String _ = "abc";//java8じゃないとだめjava11からNG
  public static void main(String[] args) throws Exception {
    exec_DtoをJacksonでシリアライズ();//Dto→String
    exec_文字列をJacksonでデシリアライズ();//String→Dto
  }

  private static void exec_文字列をJacksonでデシリアライズ() throws JsonMappingException, JsonProcessingException {
    System.out.println("exec_文字列をJacksonでデシリアライズ");
    
    String jsonString = "{\"key2\":\"value2\",\"childDto\":{\"key3\":\"value3\",\"key4\":\"\"}}";
    ObjectMapper mapper = new ObjectMapper();
    mapper = mapper.setSerializationInclusion(Include.NON_NULL);
    
    SampleDto sampleDto = mapper.readValue(jsonString, new TypeReference<SampleDto>() {});
    System.out.println(sampleDto.toString());//SampleDto [key1=null, key2=value2, childDto=ChildSampleDto [key3=value3, key4=]]　※nullと空文字も区別できるぜ
  }

  private static void exec_DtoをJacksonでシリアライズ() throws JsonProcessingException {
    System.out.println("exec_DtoをJacksonでシリアライズ");

    //出力対象のデータオブジェクトをセッティング
    SampleDto dto = new SampleDto();
    dto.setKey1(null);
    dto.setKey2("value2");

    ChildSampleDto childDto = new ChildSampleDto();
    childDto.setKey3("value3");
    childDto.setKey4("");
    dto.setChildDto(childDto);

    //Jackson出力
    ObjectMapper mapper = new ObjectMapper();
    mapper = mapper.setSerializationInclusion(Include.NON_NULL);//nullなら出力しない
    String json = mapper.writeValueAsString(dto);
    System.out.println(json);    //{"key2":"value2","childDto":{"key3":"value3","key4":""}}
  }

}
