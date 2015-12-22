package com.linguo.comment_translate.model;

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
    private CommentTranslation commentTranslation;

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

    public CommentTranslation getCommentTranslation() {
        return commentTranslation;
    }

    public void setCommentTranslation(CommentTranslation commentTranslation) {
        this.commentTranslation = commentTranslation;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
