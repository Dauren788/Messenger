package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

public class ProfileImage {
    @SerializedName(value="output")
    private byte [] output;

    public ProfileImage(byte [] output) {
        this.output = output;
    }

    public byte [] getOutput() {
        return this.output;
    }
}
