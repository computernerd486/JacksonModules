package tech.shadowland.marshall.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Sample {

    @JsonProperty("id")
    String id;

    @JsonProperty("key")
    String key;

    @JsonProperty("value")
    String value;

    @JsonProperty("nested")
    SampleNested nested;

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

    public SampleNested getNested() {
        return nested;
    }

    public void setNested(SampleNested nested) {
        this.nested = nested;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", nested=" + nested +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return Objects.equals(id, sample.id) &&
                Objects.equals(key, sample.key) &&
                Objects.equals(value, sample.value) &&
                Objects.equals(nested, sample.nested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, value, nested);
    }
}
