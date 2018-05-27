package com.pan.springboot.entity;

public class Selection {
    private Long selectionId;

    private String sname;

    private Long selectionClickCount;

    private Long selectionArticleCount;

    private Long selectionParentId;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Long getSelectionClickCount() {
        return selectionClickCount;
    }

    public void setSelectionClickCount(Long selectionClickCount) {
        this.selectionClickCount = selectionClickCount;
    }

    public Long getSelectionArticleCount() {
        return selectionArticleCount;
    }

    public void setSelectionArticleCount(Long selectionArticleCount) {
        this.selectionArticleCount = selectionArticleCount;
    }

    public Long getSelectionParentId() {
        return selectionParentId;
    }

    public void setSelectionParentId(Long selectionParentId) {
        this.selectionParentId = selectionParentId;
    }
}