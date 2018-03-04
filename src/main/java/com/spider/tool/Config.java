package com.spider.tool;

import lombok.Data;

import java.util.Properties;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/12/5.
 */
@Data
public class Config {
    private String db_url;
    private String db_name;
    private String db_user_name;
    private String db_user_pass;
    private String thread_max_active;
    private String user_info_size;
    private String star_token;
    private String yzm_path;
    private String cookie_path;
    private String zhihu_name;
    private String zhihu_pass;
    private Boolean isOnlyParser;
    private static Config INSTANCES;

    static {
        INSTANCES = new Config();
    }

    private Config() {
        try {
            Properties p = new Properties();
            p.load(Config.class.getResourceAsStream("/config.properties"));
            this.db_url = p.getProperty("db_url");
            this.db_name = p.getProperty("db_name");
            this.db_user_name = p.getProperty("db_user_name");
            this.db_user_pass = p.getProperty("db_user_pass");
            this.thread_max_active = p.getProperty("thread_max_active");
            this.star_token = p.getProperty("star_token");
            this.yzm_path = p.getProperty("yzm_path");
            this.cookie_path = p.getProperty("cookie_path");
            this.zhihu_name = p.getProperty("zhihu_name");
            this.zhihu_pass = p.getProperty("zhihu_pass");
            this.isOnlyParser=Boolean.valueOf(p.getProperty("isOnlyParser"));
            this.user_info_size=p.getProperty("user_info_size");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Config INSTANCES() {
        return INSTANCES;
    }

    public static void main(String[] args) {
        System.out.println(Config.INSTANCES());
    }

}
