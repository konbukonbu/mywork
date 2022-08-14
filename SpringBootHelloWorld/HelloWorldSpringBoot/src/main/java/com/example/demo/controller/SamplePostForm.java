package com.example.demo.controller;

import java.io.Serializable;

public class SamplePostForm implements Serializable {

  private String key1;
  @Override
  public String toString() {
    return "SamplePostForm [key1=" + key1 + ", key2=" + key2 + "]";
  }

  private String key2;

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
