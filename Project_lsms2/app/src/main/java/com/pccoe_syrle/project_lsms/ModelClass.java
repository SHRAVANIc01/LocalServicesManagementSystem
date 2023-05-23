package com.pccoe_syrle.project_lsms;

public class ModelClass {
  String name;
  Integer img;

  public ModelClass(String name, Integer img) {
    this.name = name;
    this.img = img;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getImg() {
    return img;
  }

  public void setImg(Integer img) {
    this.img = img;
  }
}
