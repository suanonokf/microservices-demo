package com.example.AI.Service.DTO;


import com.example.AI.Service.Enum.ContentCategory;
import com.example.AI.Service.Enum.ContentStatus;
import com.example.AI.Service.Enum.ContentType;

public class ContentRecord {
    public Long contentId;
    public String username;
    public ContentType contentType;
    public ContentCategory contentCategory;
    public ContentStatus contentStatus;
    public String textData;
    public String contentUrl;

    public ContentRecord(Long contentId, String username, ContentType contentType, ContentCategory contentCategory, ContentStatus contentStatus, String textData, String contentUrl) {
        this.contentId = contentId;
        this.username = username;
        this.contentType = contentType;
        this.contentCategory = contentCategory;
        this.contentStatus = contentStatus;
        this.textData = textData;
        this.contentUrl = contentUrl;
    }

    public ContentRecord() {
    }

    public Long getContentId() {
        return contentId;
    }

    public String getUsername() {
        return username;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }

    public ContentStatus getContentStatus() {
        return contentStatus;
    }

    public String getTextData() {
        return textData;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setContentCategory(ContentCategory contentCategory) {
        this.contentCategory = contentCategory;
    }

    public void setContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
