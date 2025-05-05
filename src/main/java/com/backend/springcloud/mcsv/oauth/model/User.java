package com.backend.springcloud.mcsv.oauth.model;


import java.time.LocalDate;
import java.util.List;


public class User {
    private Long id;
    private String name;

    private String username;
    private String password;
    private String email;
    private Boolean enable;
    private boolean admin;
    private LocalDate createdAt;
    private List<Role> roles;

    public User() {
    }


    public User(String name, String username, String password, String email, String role, Boolean enable, LocalDate createdAt) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.createdAt = createdAt;
    }

    public User(String name, String username, String password, String email, String role, Boolean enable) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.createdAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                ", createdAt=" + createdAt +
                ", roles=" + roles +
                '}';
    }
}

