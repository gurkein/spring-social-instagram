package org.springframework.social.instagram.api.impl;

import java.util.Arrays;
import java.util.List;

import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptorModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.social.instagram.api.LocationOperations;
import org.springframework.social.instagram.api.MediaOperations;
import org.springframework.social.instagram.api.TagOperations;
import org.springframework.social.instagram.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the central class for interacting with Instagram.
 * <p>
 * Not all operations through Instagram require OAuth 2-based authentication. However, to perform authenticated operations, such as commenting and liking,
 * InstagramTemplate must be constructed with a valid access token.
 * </p>
 * <p>
 * There are quite a few operations that do not require OAuth authentication. In those cases, you may use a {@link InstagramTemplate} that is created through
 * the default constructor. Attempts to perform secured operations through such an instance, however, will result in {@link IllegalStateException} being thrown.
 * </p>
 */
public class InstagramTemplate extends AbstractOAuth2ApiBinding implements Instagram {

	private final String accessToken;
	private final String clientId;
	private final TagOperations tagOperations;
	private final LocationOperations locationOperations;
	private final MediaOperations mediaOperations;
	private final UserOperations userOperations;

	/**
	 * Create a new instance of InstagramTemplate. This constructor creates a new InstagramTemplate able to perform unauthenticated operations against
	 * Instagram's API. Some operations do not require OAuth authentication. A TwitterTemplate created with this constructor will support those operations.
	 * Those operations requiring authentication will throw {@link BadCredentialsException}.
	 * 
	 * @param clientId the application's client ID
	 */
	public InstagramTemplate(String clientId) {
		this(clientId, null, false);
	}

	/**
	 * Create a new instance of InstagramTemplate.
	 * 
	 * @param clientId
	 *            the application's client ID
	 * @param accessToken
	 *            an access token acquired through OAuth authentication with Instagram
	 */
	public InstagramTemplate(String clientId, String accessToken) {
		this(clientId, accessToken, true);
	}

	private InstagramTemplate(String clientId, String accessToken, boolean isAuthorizedForUser) {
		super(accessToken);
		this.clientId = clientId;
		this.accessToken = accessToken;

		tagOperations = new TagTemplate(this, isAuthorizedForUser);
		locationOperations = new LocationTemplate(this, isAuthorizedForUser);
		mediaOperations = new MediaTemplate(this, isAuthorizedForUser);
		userOperations = new UserTemplate(this, isAuthorizedForUser);
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new InstagramModule());
		objectMapper.registerModule(new JsonInterceptorModule());
		converter.setObjectMapper(objectMapper);
		// converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json")));
		return converter;
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new InstagramErrorHandler());
	}

	public URIBuilder withAccessToken(String uri) {
		return (accessToken == null) ? URIBuilder.fromUri(uri).queryParam("client_id", clientId) : URIBuilder.fromUri(uri).queryParam("access_token",
				accessToken);
	}

	@Override
  public TagOperations tagOperations() {
		return tagOperations;
	}

	@Override
  public LocationOperations locationOperations() {
		return locationOperations;
	}

	@Override
  public MediaOperations mediaOperations() {
		return mediaOperations;
	}

	@Override
  public UserOperations userOperations() {
		return userOperations;
	}

}
