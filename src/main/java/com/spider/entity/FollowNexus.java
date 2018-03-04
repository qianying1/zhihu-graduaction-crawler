package com.spider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
@Data
@AllArgsConstructor
public class FollowNexus {
    //Nothing
    private String token_id;
    //Nothing
    private String token_flower;
    //Nothing
    private String flower_name;
    //Nothing
    private String name;

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getToken_flower() {
        return token_flower;
    }

    public void setToken_flower(String token_flower) {
        this.token_flower = token_flower;
    }

    public String getFlower_name() {
        return flower_name;
    }

    public void setFlower_name(String flower_name) {
        this.flower_name = flower_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
