package com.copart.urlmapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash("Url")
public class Url implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Lob
    private String originalUrl;
    private String shortLink;

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                '}';
    }

    public Url() {
    }

    public Url(@JsonProperty("id") String id, @JsonProperty("originalUrl") String originalUrl, @JsonProperty("shortLink") String shortLink) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}
