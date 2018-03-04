package com.spider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
  Created by seven on 2016/11/30.
 */
@Data
@NoArgsConstructor
public class UserInfo {
    private String weibo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 住址
     */
    private String address;
    /**
     * 行业
     */
    private String business;
    /**
     * 教育水平
     */
    private String education;
    /**
     * 公司
     */
    private String company;
    /**
     * 工作
     */
    private String job;
    /**
     * 签名
     */
    private String headline;
    /**
     * 系统ID
     */
    private String id;

    /**
     * 简介
     */
    private String content;
    /**
     * 问题总数
     */
    private String answer;
    /**
     * 回答总数
     */
    private String question;
    /**
     * 文章总数
     */
    private String article;
    /**
     * 收藏总数
     */
    private String favorite;
    /**
     * 得赞总数
     */
    private String agree;
    /**
     * 公共编辑
     */
    private String edit;
    /**
     * 被感谢总数
     */
    private String thanked;

    /**
     * 关注数
     */
    private String following;
    /**
     * 粉丝数
     */
    private String followers;
    /**
     * 关注话题数
     */
    private String topic;
    /**
     * 关注专栏数
     */
    private String columns;
    /**
     * 性别
     */
    private String sex;
    /**
     * token
     */
    private String token;
    /**
     * z主页
     */
    private String index_url;

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getThanked() {
        return thanked;
    }

    public void setThanked(String thanked) {
        this.thanked = thanked;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIndex_url() {
        return index_url;
    }

    public void setIndex_url(String index_url) {
        this.index_url = index_url;
    }
}
