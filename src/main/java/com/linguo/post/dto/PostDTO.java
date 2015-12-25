package com.linguo.post.dto;


import com.linguo.post.model.Post;
import com.linguo.post.model.PostContent;
import com.linguo.users.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class PostDTO {
    private Long id;
    private String language;
    private String url;
    private Map<String,PostContent> content = new HashMap<String, PostContent>();
    private UserDTO user;
    private Integer userVote;
    private Integer points;

    public PostDTO(){}

    public PostDTO(Post entity){
        this.id = entity.getId();
        this.language = entity.getLanguageId();
        this.url = entity.getUrl();
        this.user = new UserDTO(entity.getUser());
        this.points = entity.getPoints();
        for(PostContent content: entity.getContent()){
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

    public Map<String, PostContent> getContent() {
        return content;
    }

    public void setContent(Map<String, PostContent> content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getUserVote() {
        return userVote;
    }

    public void setUserVote(Integer userVote) {
        this.userVote = userVote;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
