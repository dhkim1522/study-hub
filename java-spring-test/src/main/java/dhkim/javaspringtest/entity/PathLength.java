package dhkim.javaspringtest.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record PathLength(Integer value) {
    public PathLength {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Path length must be null or 0 or 1");
        }
    }
    public boolean isRoot() {
        return this.value == null;
    }
    public boolean isSubCA1() {
        return Integer.valueOf(1).equals(this.value);
    }
    public boolean isSubCA2() {
        return Integer.valueOf(0).equals(this.value);
    }
    public static PathLength root() {
        return new PathLength(null);
    }
    public static PathLength subCa1() {
        return new PathLength(1);
    }
    public static PathLength subCa2() {
        return new PathLength(0);
    }
}
