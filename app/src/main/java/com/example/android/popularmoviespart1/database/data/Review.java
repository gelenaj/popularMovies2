package com.example.android.popularmoviespart1.database.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("url")
        @Expose
        private String url;

        public Review(String author, String content, String url){
            this.author = author;
            this.content = content;
            this.url = url;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
