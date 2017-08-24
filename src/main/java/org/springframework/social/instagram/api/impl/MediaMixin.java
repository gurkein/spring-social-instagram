package org.springframework.social.instagram.api.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptors;
import org.springframework.social.instagram.api.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInterceptors(beforeDeserialization = RawJsonDeserializationInterceptor.class)
abstract class MediaMixin extends InstagramObjectMixin {
	@JsonCreator
	public MediaMixin(@JsonProperty("id") String id,
			@JsonProperty("filter") String filter,
			@JsonProperty("link") String link,
			@JsonProperty("caption") Caption caption,
			@JsonProperty("user") InstagramProfile user,
			@JsonProperty("location") Location location,
			@JsonProperty("created_time") @JsonDeserialize(using = InstagramDateDeserializer.class) Date createdTime,
			@JsonProperty("user_has_liked") boolean userHasLiked,
			@JsonProperty("images") Map<String, Image> images,
			@JsonProperty("videos") Map<String, Video> videos,
			@JsonProperty("tags") List<String> tags,
			@JsonProperty("likes") LikesInfo likes,
			@JsonProperty("comments") CommentsInfo comments) {
	}

}
