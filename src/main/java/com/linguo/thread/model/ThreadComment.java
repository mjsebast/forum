package com.linguo.thread.model;

import com.linguo.users.model.UserAccount;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ThreadComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="thread_id", nullable=false)
    private Thread thread;

    @OneToMany(mappedBy = "threadComment", cascade = CascadeType.ALL)
    private Set<ThreadCommentContent> content;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<ThreadComment> children;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private ThreadComment parent;

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

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public Set<ThreadCommentContent> getContent() {
        return content;
    }

    public void setContent(Set<ThreadCommentContent> content) {
        this.content = content;
    }

    public Set<ThreadComment> getChildren() {
        return children;
    }

    public void setChildren(Set<ThreadComment> children) {
        this.children = children;
    }

    public ThreadComment getParent() {
        return parent;
    }

    public void setParent(ThreadComment parent) {
        this.parent = parent;
    }
}
