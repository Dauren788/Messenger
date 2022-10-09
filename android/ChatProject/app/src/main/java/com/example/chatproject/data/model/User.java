package com.example.chatproject.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName(value="id")
    private Integer id;
    @SerializedName(value="username")
    private String username;
    @SerializedName(value="name")
    private String name;
    @SerializedName(value="surname")
    private String surname;
    @SerializedName(value="email")
    private String email;
    @SerializedName(value="phone")
    private String phone;
    @SerializedName(value="password_hash")
    private String passwordHash;

    public User(Integer id, String username, String name, String surname, String email, String phone, String passwordHash) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    public String getLoggedUserId() {
        return id.toString();
    }
}
