package com.pan.springboot.entity;

import java.util.Date;

public class Article {
    private Long articleId;

    private Long selectionId;

    private Long userId;

    private String title;

    private Date creatTime;

    private Long clickCount;

    private Long replayCount;

    private Date lastReplaytime;

    private Long astaus;

    private Long alike;

    private String articleAbstract;

    private String content;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public Long getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(Long replayCount) {
        this.replayCount = replayCount;
    }

    public Date getLastReplaytime() {
        return lastReplaytime;
    }

    public void setLastReplaytime(Date lastReplaytime) {
        this.lastReplaytime = lastReplaytime;
    }

    public Long getAstaus() {
        return astaus;
    }

    public void setAstaus(Long astaus) {
        this.astaus = astaus;
    }

    public Long getAlike() {
        return alike;
    }

    public void setAlike(Long alike) {
        this.alike = alike;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract == null ? null : articleAbstract.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}