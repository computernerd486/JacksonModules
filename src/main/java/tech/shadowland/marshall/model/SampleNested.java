package tech.shadowland.marshall.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SampleNested {

    @JsonProperty("id")
    String id;

    @JsonProperty("key")
    String key;

    @JsonProperty("value")
    String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleNested that = (SampleNested) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, value);
    }
}
