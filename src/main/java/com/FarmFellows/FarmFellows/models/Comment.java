package com.FarmFellows.FarmFellows.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private Farmer f;

    private String commenterName;

    private String text;

    private LocalDateTime commentTime;

    protected Comment() {
    }

    public Comment(Farmer f, String commenterName, String text) {
        this.f = f;
        this.commenterName = commenterName;
        this.text = text;
        this.commentTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Farmer getF() {
        return f;
    }

    public void setF(Farmer f) {
        this.f = f;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }
}
