package com.copart.urlmapper.service;

import com.copart.urlmapper.dao.UrlDao;
import com.copart.urlmapper.model.Url;
import com.copart.urlmapper.model.UrlDto;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService{

    private final UrlDao urlDao;

    @Autowired
    public UrlService(@Qualifier("postgres") UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    public String generateShortLink(UrlDto urlDto) {

        return urlDao.daoGenerateShortLink(urlDto);
    }


    public Optional<Url> storeUrl(Url url) {
        return urlDao.daoStoreUrl(url);
    }



    public String getEncodedUrl(String url) {
        return urlDao.daoGetEncodedUrl(url);
    }
}
