package com.linguo.comment.model;

import com.linguo.thread.model.Thread;
import com.linguo.users.model.UserAccount;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name="thread_id", nullable=false)
    private com.linguo.thread.model.Thread thread;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<CommentContent> content;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Comment> children;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Comment parent;

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

    public Set<CommentContent> getContent() {
        return content;
    }

    public void setContent(Set<CommentContent> content) {
        this.content = content;
    }

    public Set<Comment> getChildren() {
        return children;
    }

    public void setChildren(Set<Comment> children) {
        this.children = children;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
