package com.study.shortener.services;

import com.study.shortener.entities.DTO.UrlRequest;
import com.study.shortener.entities.DTO.UrlResponse;
import com.study.shortener.entities.models.Url;
import com.study.shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse shortenUrl(UrlRequest urlRequest, HttpServletRequest request) {
        String id = genIdAndSave(urlRequest.url());
        String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);
        return new UrlResponse(redirectUrl);
    }

    public ResponseEntity getOriginalUrl(String id) {
        Optional<Url> url = urlRepository.findById(id);
        if (url.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(url.get().getUrl()));
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        }
        return  ResponseEntity.notFound().build();
    }

    private String genIdAndSave(String url) {
        String id;
        Url newUrl = new Url();

        do {
            id = RandomStringUtils.randomAlphanumeric(10);
        } while (urlRepository.existsById(id));

        newUrl.setId(id);
        newUrl.setUrl(url);
        newUrl.setExpiresAt(LocalDateTime.now().plusHours(24));
        urlRepository.save(newUrl);

        return id;
    }
}
