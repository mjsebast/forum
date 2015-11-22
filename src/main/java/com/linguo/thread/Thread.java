package com.linguo.thread;

import com.linguo.forum.Forum;
import com.linguo.users.UserAccount;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Thread {

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
    @OneToMany(mappedBy = "thread")
    private Set<ThreadContent> content;

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

    public Set<ThreadContent> getContent() {
        return content;
    }

    public void setContent(Set<ThreadContent> content) {
        this.content = content;
    }
}
