package com.soddik.messageapp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser owner;

    public Message() {
    }

    public Message(Long id, String content, AppUser owner) {
        this.id = id;
        this.content = content;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return Objects.equals(getId(), message.getId()) && Objects.equals(getContent(), message.getContent()) && Objects.equals(getOwner(), message.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getOwner());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", owner=" + owner +
                '}';
    }
}
