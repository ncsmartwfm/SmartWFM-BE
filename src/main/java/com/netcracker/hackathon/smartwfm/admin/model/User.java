package com.netcracker.hackathon.smartwfm.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.hackathon.smartwfm.admin.service.AttributeEncryptor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    @Convert(converter = AttributeEncryptor.class)
    private String password;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("roles")
    @NotNull(message = "Role cannot be empty for a user")
    private List<String> role;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
