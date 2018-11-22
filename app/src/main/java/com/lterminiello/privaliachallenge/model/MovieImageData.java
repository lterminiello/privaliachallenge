package com.lterminiello.privaliachallenge.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MovieImageData implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
