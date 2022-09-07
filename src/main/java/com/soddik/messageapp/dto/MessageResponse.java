package com.soddik.messageapp.dto;

import java.util.Objects;

public record MessageResponse(String name, String content) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageResponse response)) return false;
        return Objects.equals(name, response.name) && Objects.equals(content, response.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content);
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
