package dhkim.javaspringtest.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public final class PathLength {

    private final Integer value;

    protected PathLength() {
        this.value = null; // JPA 기본 생성자 (사용하지 않음)
    }

    private PathLength(Integer value) {
        if (value != null && (value < 0 || value > 1)) {
            throw new IllegalArgumentException("Path length must be null or 0 or 1");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isRoot() {
        return this.value == null;
    }

    public boolean isSubCa1() {
        return Integer.valueOf(1).equals(this.value);
    }

    public boolean isSubCa2() {
        return Integer.valueOf(0).equals(this.value);
    }

    public static PathLength root() {
        return PathLength.of(null);
    }

    public static PathLength subCa1() {
        return PathLength.of(1);
    }

    public static PathLength subCa2() {
        return PathLength.of(0);
    }

    public static PathLength of(Integer value) {
        return new PathLength(value);
    }
}
