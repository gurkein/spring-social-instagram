package org.springframework.social.instagram.api.impl;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.instagram.api.Location;
import org.springframework.social.instagram.api.PagedMediaList;

@SuppressWarnings("unused")
public class LocationTemplateTest extends AbstractInstagramApiTest {

	@Test
	public void getLocation() {
	    mockServer.expect(requestTo("https://api.instagram.com/v1/locations/12345/?access_token=ACCESS_TOKEN"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/location.json", getClass()), responseHeaders));
		
		Location location = instagram.locationOperations().getLocation(12345);
		mockServer.verify();
	}
	
	@Test 
	public void getRecentMedia() {
		mockServer.expect(requestTo("https://api.instagram.com/v1/locations/12345/media/recent/?access_token=ACCESS_TOKEN"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/recent-media.json", getClass()), responseHeaders));
		
		PagedMediaList media = instagram.locationOperations().getRecentMedia(12345);
		assertPagedResults(media);
		mockServer.verify();
	}
	
	@Test
	public void getRecentMediaParams() {
		mockServer.expect(requestTo("https://api.instagram.com/v1/locations/12345/media/recent/?access_token=ACCESS_TOKEN&max_id=200&max_timestamp=10000&min_id=100&min_timestamp=5000"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/recent-media.json", getClass()), responseHeaders));
		
		PagedMediaList media = instagram.locationOperations().getRecentMedia(12345, 200, 100, 5000, 10000);
		assertPagedResults(media);
		mockServer.verify();
	}
	
	@Test
	public void search() {
		mockServer.expect(requestTo("https://api.instagram.com/v1/locations/search/?access_token=ACCESS_TOKEN&lng=200.0&lat=100.0"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/location-search.json", getClass()), responseHeaders));
		
		List<Location> locations = instagram.locationOperations().search(100, 200);
		mockServer.verify();
	}
	
	@Test
	public void searchWithDistance() {
		mockServer.expect(requestTo("https://api.instagram.com/v1/locations/search/?access_token=ACCESS_TOKEN&distance=2000&lng=200.0&lat=100.0"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/location-search.json", getClass()), responseHeaders));
		
		List<Location> locations = instagram.locationOperations().search(100, 200, 2000);
		mockServer.verify();
	}
	
	@Test
	public void searcgWithFourSquare() {
		mockServer.expect(requestTo("https://api.instagram.com/v1/locations/search/?access_token=ACCESS_TOKEN&foursquare_id=1000"))
			.andExpect(method(GET))
			.andRespond(withResponse(new ClassPathResource("testdata/location-search.json", getClass()), responseHeaders));
		
		List<Location> locations = instagram.locationOperations().search(1000);
		mockServer.verify();
	}
}
