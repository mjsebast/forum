package com.linguo.commenttranslate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linguo.thread.model.ThreadComment;
import com.linguo.users.model.UserAccount;

import javax.persistence.*;

@Entity
public class ThreadCommentTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="comment_id", nullable=false)
    @JsonIgnore
    private ThreadComment threadComment;

    private Integer points;
    private String message;
    private String languageId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public ThreadComment getThreadComment() {
        return threadComment;
    }

    public void setThreadComment(ThreadComment threadComment) {
        this.threadComment = threadComment;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }
}
