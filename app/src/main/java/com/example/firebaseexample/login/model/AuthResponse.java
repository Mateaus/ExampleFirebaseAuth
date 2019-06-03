package com.example.firebaseexample.login.model;

public class AuthResponse {

    private String responseMessage;
    private Boolean isAuthenticated;

    public AuthResponse(String responseMessage, Boolean isAuthenticated) {
        this.responseMessage = responseMessage;
        this.isAuthenticated = isAuthenticated;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
