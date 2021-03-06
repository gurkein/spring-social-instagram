package org.springframework.social.instagram.api;

import java.util.List;

public class LikesInfo {

    private int total;
    private List<InstagramProfile> list;

    public LikesInfo(final int total, final List<InstagramProfile> list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public List<InstagramProfile> getList() {
        return list;
    }

    public void setTotal(final int total) {
        this.total = total;
    }

    public void setList(final List<InstagramProfile> list) {
        this.list = list;
    }
}
