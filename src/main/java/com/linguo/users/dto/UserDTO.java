package com.linguo.users.dto;

import com.linguo.users.model.UserAccount;


public class UserDTO {
    private Long id;
    private String email;
    private String username;

    public UserDTO(UserAccount entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.username = entity.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
