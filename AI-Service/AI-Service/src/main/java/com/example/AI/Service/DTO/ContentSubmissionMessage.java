package com.example.AI.Service.DTO;

import com.example.AI.Service.Enum.ContentCategory;
import com.example.AI.Service.Enum.ContentStatus;
import com.example.AI.Service.Enum.Trustworthiness;

public class ContentSubmissionMessage {
    private String username;
    private int reputationScore;
    private Trustworthiness trustworthiness;
    private ContentStatus contentStatus;
    private ContentCategory contentCategory;
    private String contentData;
    private String contentUrl;
    private final String confirmationMessage ="Content Successfully Submitted to Review ";

    public ContentSubmissionMessage(String username,
                                    int reputationScore,
                                    Trustworthiness trustworthiness,
                                    ContentStatus contentStatus,
                                    ContentCategory contentCategory,
                                    String contentData,
                                    String contentUrl) {
        this.username = username;
        this.reputationScore = reputationScore;
        this.trustworthiness = trustworthiness;
        this.contentStatus = contentStatus;
        this.contentCategory = contentCategory;
        this.contentData = contentData;
        this.contentUrl=contentUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public ContentSubmissionMessage() {
    }

    public String getUsername() {
        return username;
    }

    public int getReputationScore() {
        return reputationScore;
    }

    public Trustworthiness getTrustworthiness() {
        return trustworthiness;
    }

    public ContentStatus getContentStatus() {
        return contentStatus;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }

    public String getContentData() {
        return contentData;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public void setReputationScore(int reputationScore) {
        this.reputationScore = reputationScore;
    }

    public void setTrustworthiness(Trustworthiness trustworthiness) {
        this.trustworthiness = trustworthiness;
    }

    public void setContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    public void setContentCategory(ContentCategory contentCategory) {
        this.contentCategory = contentCategory;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }
}
