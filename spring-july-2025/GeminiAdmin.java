package com.airline.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeminiAdmin {
    @JsonProperty("id")
    private String id;

    public GeminiAdmin() {}

    public GeminiAdmin(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GeminiAdmin{" +
                "id='" + id + '\'' +
                '}';
    }
}
