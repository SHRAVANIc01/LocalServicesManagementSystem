package com.pccoe_syrle.project_lsms;

import android.widget.ImageView;

public class ModelClass2 {
    Integer img;
    String name;

    public ModelClass2(Integer img, String name) {
        this.img = img;
        this.name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
