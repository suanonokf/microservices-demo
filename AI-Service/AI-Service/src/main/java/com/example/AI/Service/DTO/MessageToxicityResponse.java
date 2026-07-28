package com.example.AI.Service.DTO;

import com.example.AI.Service.Enum.ContentCategory;

public class MessageToxicityResponse {
    public String username;
    public Long contentId;
    public ContentCategory contentCategory;
    public int toxicityScore;
    public boolean willBePublished;

    public MessageToxicityResponse(String username, Long contentId, ContentCategory contentCategory, int toxicityScore, boolean willBePublished) {
        this.username = username;
        this.contentId = contentId;
        this.contentCategory = contentCategory;
        this.toxicityScore = toxicityScore;
        this.willBePublished = willBePublished;
    }

    public String getUsername() {
        return username;
    }

    public Long getContentId() {
        return contentId;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }

    public int getToxicityScore() {
        return toxicityScore;
    }

    public boolean isWillBePublished() {
        return willBePublished;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public void setContentCategory(ContentCategory contentCategory) {
        this.contentCategory = contentCategory;
    }

    public void setToxicityScore(int toxicityScore) {
        this.toxicityScore = toxicityScore;
    }

    public void setWillBePublished(boolean willBePublished) {
        this.willBePublished = willBePublished;
    }
}
