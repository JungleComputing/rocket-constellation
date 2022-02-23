package nl.esciencecenter.common_source_identification;

import nl.esciencecenter.rocket.types.HashableKey;

import java.util.Objects;

public class ImageIdentifier implements HashableKey {
    private static final long serialVersionUID = -2985516246452858565L;

    private int index;
    private String path;

    public ImageIdentifier(int index, String path) {
        this.index = index;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageIdentifier that = (ImageIdentifier) o;
        return Objects.equals(path, that.path) && index == that.index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public int compareTo(HashableKey that) {
        if (that == null || getClass() != that.getClass()) return 0;
        return this.path.compareTo(((ImageIdentifier)that).path);
    }
}
