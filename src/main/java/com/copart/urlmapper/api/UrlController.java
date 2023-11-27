package com.copart.urlmapper.api;

import com.copart.urlmapper.model.Url;
import com.copart.urlmapper.model.UrlDto;
import com.copart.urlmapper.model.UrlErrorResponseDto;
import com.copart.urlmapper.model.UrlResponseDto;
import com.copart.urlmapper.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/url")
@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    public String generateShortLink(@RequestBody UrlDto urldto){

        return urlService.generateShortLink(urldto);
    }

    @GetMapping("/{id}")
    public String getOriginalLink(@PathVariable("id") String url){
        return urlService.getEncodedUrl(url);
    }


}
