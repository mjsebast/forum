package com.linguo.post_translate.model;

import com.linguo.users.model.UserAccount;

import javax.persistence.*;

@Entity
public class PostContentTranslationVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="post_content_translation_id", nullable=false)
    private PostContentTranslation postContentTranslation;

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

    public PostContentTranslation getPostContentTranslation() {
        return postContentTranslation;
    }

    public void setPostContentTranslation(PostContentTranslation postContentTranslation) {
        this.postContentTranslation = postContentTranslation;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
