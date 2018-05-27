package com.pan.springboot.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Secret {
    private Long secretId;

    @NotNull
    private String content;

    private Date timestamp;

    private String ip;

    private Long slike;

    private Long parentId;

    private List<Secret> comments;

    public List<Secret> getComments() {
        return comments;
    }

    public void setComments(List<Secret> comments) {
        this.comments = comments;
    }

    public Long getSecretId() {
        return secretId;
    }

    public void setSecretId(Long secretId) {
        this.secretId = secretId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Long getSlike() {
        return slike;
    }

    public void setSlike(Long slike) {
        this.slike = slike;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}