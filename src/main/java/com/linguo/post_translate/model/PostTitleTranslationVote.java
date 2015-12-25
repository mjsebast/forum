package com.linguo.post_translate.model;

import com.linguo.users.model.UserAccount;

import javax.persistence.*;

@Entity
public class PostTitleTranslationVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="post_title_translation_id", nullable=false)
    private PostTitleTranslation postTitleTranslation;

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

    public PostTitleTranslation getPostTitleTranslation() {
        return postTitleTranslation;
    }

    public void setPostTitleTranslation(PostTitleTranslation postTitleTranslation) {
        this.postTitleTranslation = postTitleTranslation;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
