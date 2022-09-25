package com.example.cft_test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RedisHash("Char")
public class Characteristics implements Serializable {
    @JsonProperty("characteristics")
    @Indexed
    private Map<String, String> chars;

    public Characteristics() {
    }

    public Characteristics(Map<String, String> chars) {
        this.chars = chars;
    }

    public Map<String, String> getChars() {
        return chars;
    }

    public void setChars(Map<String, String> chars) {
        this.chars = chars;
    }

    public void setChar(String key, String value) {
        this.chars.put(key, value);
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "chars=" + chars.toString() +
                '}';
    }
}
