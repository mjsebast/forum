package com.linguo.thread.dto;

import com.linguo.thread.model.ThreadComment;
import com.linguo.thread.model.ThreadCommentContent;
import com.linguo.users.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ThreadCommentDTO {

    private Long id;
    private String language;
    private Map<String, ThreadCommentContent> content = new HashMap<>();
    private UserDTO user;
    private Long threadId;
    private List<ThreadCommentDTO> children = new ArrayList<>();
    private Long parentId;

    public ThreadCommentDTO(){}

    public ThreadCommentDTO(ThreadComment entity){
        this.id = entity.getId();
        this.language = entity.getLanguageId();
        this.user = new UserDTO(entity.getUser());
        this.threadId = entity.getThread().getId();

        for(ThreadCommentContent content: entity.getContent()){
            this.content.put(content.getLanguageId(), content);
        }
        if(entity.getChildren()!=null){
            this.children = entity.getChildren().stream().map(ThreadCommentDTO:: new).collect(Collectors.toList());
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

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public List<ThreadCommentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ThreadCommentDTO> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
