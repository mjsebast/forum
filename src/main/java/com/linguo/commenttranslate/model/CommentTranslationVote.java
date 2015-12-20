package com.linguo.commenttranslate.model;

import com.linguo.users.model.UserAccount;

import javax.persistence.*;

@Entity
public class CommentTranslationVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="comment_translation_id", nullable=false)
    private ThreadCommentTranslation threadCommentTranslation;

    private Integer vote;

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

    public ThreadCommentTranslation getThreadCommentTranslation() {
        return threadCommentTranslation;
    }

    public void setThreadCommentTranslation(ThreadCommentTranslation threadCommentTranslation) {
        this.threadCommentTranslation = threadCommentTranslation;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
