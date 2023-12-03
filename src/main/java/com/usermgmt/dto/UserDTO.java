package com.usermgmt.dto;

public class UserDTO {
    private int userId;
    private String username;
    private String password;
    private String pno;
    private String role;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPno() {
        return pno;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
