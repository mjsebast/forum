package com.linguo.comment.dto;

import com.linguo.comment.model.Comment;
import com.linguo.comment.model.CommentContent;
import com.linguo.users.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CommentDTO {

    private Long id;
    private String language;
    private Map<String, CommentContent> content = new HashMap<>();
    private UserDTO user;
    private Long threadId;
    private List<CommentDTO> children = new ArrayList<>();
    private Long parentId;
    private Integer points;
    private Integer userVote;

    public CommentDTO(){}

    public CommentDTO(Comment entity){
        this.id = entity.getId();
        this.language = entity.getLanguageId();
        this.user = new UserDTO(entity.getUser());
        this.threadId = entity.getThread().getId();
        this.points = entity.getPoints();
        if(entity.getParent()!=null){
            this.parentId = entity.getParent().getId();
        }
        for(CommentContent content: entity.getContent()){
            this.content.put(content.getLanguageId(), content);
        }
        if(entity.getChildren()!=null){
            this.children = entity.getChildren().stream().map(CommentDTO:: new).collect(Collectors.toList());
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

    public Map<String, CommentContent> getContent() {
        return content;
    }

    public void setContent(Map<String, CommentContent> content) {
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

    public List<CommentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CommentDTO> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getUserVote() {
        return userVote;
    }

    public void setUserVote(Integer userVote) {
        this.userVote = userVote;
    }
}
