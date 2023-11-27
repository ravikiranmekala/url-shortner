package com.copart.urlmapper.dao;

import com.copart.urlmapper.model.Url;
import com.copart.urlmapper.model.UrlDto;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.Option;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("postgres")
public class UrlDaoService implements UrlDao{

    public static final String HASH_KEY ="Url";

    @Autowired
    StringRedisTemplate template;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UrlDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public String daoGenerateShortLink(UrlDto urlDto) {
        if(StringUtils.isNotEmpty(urlDto.getUrl())) {
            String encodedUrl = encodeUrl(urlDto.getUrl());
            Url urlObj = new Url();
            urlObj.setId(encodedUrl);
            urlObj.setOriginalUrl(urlDto.getUrl());
            urlObj.setShortLink(encodedUrl);
            Optional<Url> urlToReturn = daoStoreUrl(urlObj);

            return encodedUrl;

        }
        return null;
    }

    public String encodeUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }
    @Override
    public Optional<Url> daoStoreUrl(Url url) {
        if(jdbcTemplate.update(
                "INSERT INTO urltable (id, originalUrl, shortLink) VALUES (?, ?, ?)",
                url.getId(), url.getOriginalUrl(), url.getShortLink()
        ) == 1){
            System.out.println("Successfully inserted to POSTGRES");
            template.opsForHash().put(HASH_KEY,url.getShortLink(),url.getOriginalUrl());
//            return new Url(url.getId(),url.getOriginalUrl(), url.getShortLink());
        }
        return Optional.ofNullable(url);
    }

    @Override
//    @Cacheable(value="Url",key="#id")
    public String daoGetEncodedUrl(String url) {

        String res="";

        final String sql = "SELECT originalUrl,shortLink FROM urltable where id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{url},
                (resultSet, i)-> {
            String shortLink = resultSet.getString("shortLink");
            String originalLink = resultSet.getString("originalUrl");
            System.out.println("GOT THE ORIGINAL LINK"+originalLink);

            return originalLink;

                });
//        return null;
    }
}
