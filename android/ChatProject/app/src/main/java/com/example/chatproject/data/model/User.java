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
    @SerializedName(value="profile_image")
    private String profileImage;

    public User(Integer id, String username, String name, String surname, String email, String phone, String passwordHash, String profileImage) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.profileImage = profileImage;
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

    public void setNewProfileImage(String filename) {
        this.profileImage = filename;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLoggedUserId() {
        return id.toString();
    }

    public String getProfileImage() { return profileImage; }
}
