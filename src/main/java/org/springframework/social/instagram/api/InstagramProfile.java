package org.springframework.social.instagram.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class InstagramProfile extends InstagramObject {

    private long id;
    private String profilePictureUrl;
    private String username;
    private Map<String, Integer> counts;
    private String fullName;
    private String bio;
    private String website;
    private String firstName;
    private String lastName;

    public InstagramProfile(final long id, final String username, final String fullName, final String profilePictureUrl, final Map<String, Integer> counts) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.profilePictureUrl = profilePictureUrl;
        this.counts = counts;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getCounts() {
        return counts;
    }

    // do not serialize, can cause NPE
    @JsonIgnore
    public int getMediaCount() {
        return counts.get("media");
    }

    // do not serialize, can cause NPE
    @JsonIgnore
    public int getFollowsCount() {
        return counts.get("follows");
    }

    // do not serialize, can cause NPE
    @JsonIgnore
    public int getFollowedBy() {
        return counts.get("followed_by");
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

}
