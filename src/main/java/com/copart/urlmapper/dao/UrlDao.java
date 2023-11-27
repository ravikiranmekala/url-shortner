package com.copart.urlmapper.dao;

import com.copart.urlmapper.model.Url;
import com.copart.urlmapper.model.UrlDto;

import java.util.Optional;

public interface UrlDao {

    public String daoGenerateShortLink(UrlDto urlDto);
    public Optional<Url> daoStoreUrl(Url url);
    public String daoGetEncodedUrl(String url);
}
