package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

public class SearchUser {
    @SerializedName(value="id")
    private Integer id;
    @SerializedName(value="Username")
    private String Username;
    @SerializedName(value="Name")
    private String Name;
    @SerializedName(value="Surname")
    private String Surname;

    public SearchUser(Integer id, String Username, String Name, String Surname) {
        this.id = id;
        this.Username = Username;
        this.Name = Name;
        this.Surname = Surname;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
