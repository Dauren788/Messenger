package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    @SerializedName(value="jwtToken")
    private String jwtToken;
    @SerializedName(value="userData")
    private User userData;

    public LoggedInUser(String jwtToken, User userData) {
        this.jwtToken = jwtToken;
        this.userData = userData;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public User getUser() {
        return this.userData;
    }

}