package org.springframework.social.instagram.api.impl;

import java.util.Date;

import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptors;
import org.springframework.social.instagram.api.InstagramProfile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInterceptors(beforeDeserialization = RawJsonDeserializationInterceptor.class)
abstract class CommentMixin extends InstagramObjectMixin {
	@JsonCreator
	CommentMixin(@JsonProperty("id") long id,
			@JsonProperty("created_time") @JsonDeserialize(using = InstagramDateDeserializer.class) Date createdTime,
			@JsonProperty("text") String text,
			@JsonProperty("from") InstagramProfile from) {
	}
}
