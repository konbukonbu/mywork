package com.example.demo.controller;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class SampleUploadForm implements Serializable {
  private String key1;
  private String key2;
  private MultipartFile uploadFile;
  
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
  public MultipartFile getUploadFile() {
    return uploadFile;
  }
  public void setUploadFile(MultipartFile uploadFile) {
    this.uploadFile = uploadFile;
  }
}
