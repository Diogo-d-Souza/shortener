package com.study.shortener.services;

import com.study.shortener.entities.DTO.UrlRequest;
import com.study.shortener.entities.DTO.UrlResponse;
import com.study.shortener.entities.models.Url;
import com.study.shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse shortenUrl(UrlRequest urlRequest, HttpServletRequest request) {
        String id = genId(urlRequest.url());
        String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);
        return new UrlResponse(redirectUrl);
    }

    private String genId(String url) {
        String id;
        Url newUrl = new Url();

        do {
            id = RandomStringUtils.randomAlphanumeric(10);
        } while (urlRepository.existsById(id));

        newUrl.setId(id);
        newUrl.setUrl(url);
        newUrl.setExpiresAt(LocalDateTime.now().plusMinutes(1));
        urlRepository.save(newUrl);

        return id;
    }
}
