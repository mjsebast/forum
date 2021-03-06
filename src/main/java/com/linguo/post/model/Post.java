package com.linguo.post.model;

import com.linguo.forum.Forum;
import com.linguo.users.model.UserAccount;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="forum_id", nullable=false)
    private Forum forum;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;
    private String languageId;
    private String url;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostContent> content;
    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<PostContent> getContent() {
        return content;
    }

    public void setContent(Set<PostContent> content) {
        this.content = content;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
