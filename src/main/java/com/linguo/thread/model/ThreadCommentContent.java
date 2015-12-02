package com.linguo.thread.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ThreadCommentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String languageId;
    private String message;

    @ManyToOne
    @JoinColumn(name="comment_id", nullable=false)
    @JsonIgnore
    private ThreadComment threadComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ThreadComment getThreadComment() {
        return threadComment;
    }

    public void setThreadComment(ThreadComment threadComment) {
        this.threadComment = threadComment;
    }
}
