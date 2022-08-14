package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloWorldController {

  //http://localhost:8080/api/hello
  @RequestMapping("hello")
  private String hello() {
    System.out.println("★HELLO WORLD★");
    return "SpringBoot!";
  }

  //http://localhost:8080/api/reqParam?paramKey=hogehgoe
  @RequestMapping("/reqParam")
  private String testRequestParam(@RequestParam("paramKey") String param) {
    System.out.println("★testRequestParam★");
    return "受け取ったパラメータ：" + param;
  }

  //http://localhost:8080/api/postReq
  @RequestMapping(value = "/postReq", method = RequestMethod.POST)
  private String testRequestBody(@RequestBody String body) {
    System.out.println("★testRequestBody★");
    return "受け取ったリクエストボディ：" + body;
  }

  //TODO デフォルトはJSONのようじゃな
  //http://localhost:8080/api/postForm
  @RequestMapping(value = "/postForm", method = RequestMethod.POST)
  private String testRequestForm(@RequestBody SamplePostForm form) {
    System.out.println("★testRequestForm★");
    return "受け取ったリクエストボディ：" + form.toString();
  }

  //Multi-Partを扱うファイルアップロード処理
  //https://m-shige1979.hatenablog.com/entry/2016/12/28/080000
  //Windowsからcurlを打つ
  //https://zenn.dev/yu1low/articles/f559f35c7087ef
  //curl.exe -i -X POST "http://localhost:8080/api/upload" -F "uploadFile=@./testFile.txt" -F  "key1=value1" -F "key2=value2"
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String upload(SampleUploadForm form) {
    System.out.println(form.getKey1() + "," + form.getKey2());

    //TODO ファイルの中身を取り出す処理

    //    String retString = "{'処理結果':'SUCCESS'," + "\r\n";
    //    retString = retString + "'送ったファイル名':'" + form.getUploadFile().getOriginalFilename() + "',\r\n'送ったファイルサイズ':'"
    //        + form.getUploadFile().getSize() + "'}";

    String retString = "処理結果=SUCCESS" + "\r\n";
    retString = retString + "送ったファイル名=" + form.getUploadFile().getOriginalFilename() + "\r\n送ったファイルサイズ="
        + form.getUploadFile().getSize();

    return retString;
    //実行結果のイメージ
    //    PS C:\workspace\temp\20220815> curl.exe -i -X POST "http://localhost:8080/api/upload" -F "uploadFile=@./testFile.txt" -F  "key1=value1" -F "key2=value2"
    //    HTTP/1.1 200
    //    Content-Type: text/plain;charset=UTF-8
    //    Content-Length: 110
    //    Date: Sun, 14 Aug 2022 22:24:58 GMT
    //
    //    {'処理結果':'SUCCESS',
    //    '送ったファイル名':'testFile.txt',
    //    '送ったファイルサイズ':'47'}
    //
    //    PS C:\workspace\temp\20220815> curl.exe -i -X POST "http://localhost:8080/api/upload" -F "uploadFile=@./testFile.txt" -F  "key1=value1" -F "key2=value2"
    //    HTTP/1.1 200
    //    Content-Type: text/plain;charset=UTF-8
    //    Content-Length: 94
    //    Date: Sun, 14 Aug 2022 22:25:51 GMT
    //
    //    処理結果=SUCCESS
    //    送ったファイル名=testFile.txt
    //    送ったファイルサイズ=47
  }
}
