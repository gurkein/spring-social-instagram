package org.springframework.social.instagram.api.impl;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Annotated mixin to add Jackson annotations to FacebookObject.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class InstagramObjectMixin {

    @JsonAnySetter
    abstract void add(String key, Object value);

    @JsonIgnore
    abstract JsonNode getRawJson();

    @JsonProperty("rawJson")
    abstract void setRawJson(JsonNode rawJson);
}
