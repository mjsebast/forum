package com.linguo.thread.dto;

import com.linguo.thread.model.ThreadComment;
import com.linguo.thread.model.ThreadCommentContent;
import com.linguo.users.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;


public class ThreadCommentDTO {

    private Long id;
    private String language;
    private Map<String, ThreadCommentContent> content = new HashMap<>();
    private UserDTO user;

    public ThreadCommentDTO(ThreadComment entity){
        this.id = entity.getId();
        this.language = entity.getLanguageId();
        this.user = new UserDTO(entity.getUser());

        for(ThreadCommentContent content: entity.getContent()){
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

    public Map<String, ThreadCommentContent> getContent() {
        return content;
    }

    public void setContent(Map<String, ThreadCommentContent> content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
