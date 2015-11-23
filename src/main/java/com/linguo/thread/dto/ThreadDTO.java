package com.linguo.thread.dto;


import com.linguo.thread.model.Thread;
import com.linguo.thread.model.ThreadContent;
import com.linguo.users.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class ThreadDTO {
    private Long id;
    private String language;
    private String url;
    private Map<String,ThreadContent> content = new HashMap<String, ThreadContent>();
    private UserDTO user;

    public ThreadDTO(){}

    public ThreadDTO(Thread entity){
        this.id = entity.getId();
        this.language = entity.getLanguageId();
        this.url = entity.getUrl();
        this.user = new UserDTO(entity.getUser());

        for(ThreadContent content: entity.getContent()){
            this.content.put(content.getLanguageId(), content);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, ThreadContent> getContent() {
        return content;
    }

    public void setContent(Map<String, ThreadContent> content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
