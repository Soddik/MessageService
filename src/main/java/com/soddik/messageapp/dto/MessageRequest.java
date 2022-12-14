package com.soddik.messageapp.dto;

import java.util.Objects;

public record MessageRequest(String name, String content) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content);
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "owner='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
