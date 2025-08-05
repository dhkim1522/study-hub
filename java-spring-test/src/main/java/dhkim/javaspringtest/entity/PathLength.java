package dhkim.javaspringtest.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record PathLength(Integer value) {
    public PathLength {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Path length must be null or 0 or 1");
        }
    }
}
