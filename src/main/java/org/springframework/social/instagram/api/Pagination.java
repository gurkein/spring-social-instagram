package org.springframework.social.instagram.api;

public class Pagination {

    private String nextUrl;
    private String nextMaxId;
    private String nextMinId;

    public Pagination(String nextUrl, String nextMaxId, String nextMinId) {
        this.nextUrl = nextUrl;
        this.nextMaxId = nextMaxId;
        this.nextMinId = nextMinId;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getNextMaxId() {
        return nextMaxId;
    }

    public String getNextMinId() {
        return nextMinId;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public void setNextMaxId(String nextMaxId) {
        this.nextMaxId = nextMaxId;
    }

    public void setNextMinId(String nextMinId) {
        this.nextMinId = nextMinId;
    }

}
