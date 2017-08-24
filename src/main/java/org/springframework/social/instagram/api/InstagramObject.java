package org.springframework.social.instagram.api;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public abstract class InstagramObject {
    private Map<String, Object> extraData;

    private JsonNode rawJson;

    public InstagramObject() {
        this.extraData = new HashMap<String, Object>();
    }

    /**
     * @return Any fields in response from Instagram that are otherwise not mapped to any properties.
     */
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    /**
     * {@link JsonAnySetter} hook. Called when an otherwise unmapped property is being processed during JSON deserialization.
     * @param key The property's key.
     * @param value The property's value.
     */
    protected void add(String key, Object value) {
        extraData.put(key, value);
    }

    public JsonNode getRawJson() {
        return rawJson;
    }

    public void setRawJson(JsonNode rawJson) {
        this.rawJson = rawJson;
    }
}
