package com.linguo.comment_translate.dto;


import com.linguo.comment_translate.model.CommentTranslation;
import com.linguo.users.dto.UserDTO;

public class CommentTranslationDTO {
    private Long id;
    private Long commentId;
    private String language;
    private UserDTO user;
    private String message;
    private Integer points;
    private Integer userVote;

    public CommentTranslationDTO(){}

    public CommentTranslationDTO(CommentTranslation entity){
        this.id = entity.getId();
        this.commentId = entity.getComment().getId();
        this.language = entity.getLanguageId();
        this.user = new UserDTO(entity.getUser());
        this.message = entity.getMessage();
        this.points = entity.getPoints();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
