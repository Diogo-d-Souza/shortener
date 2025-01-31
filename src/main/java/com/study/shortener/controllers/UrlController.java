package com.study.shortener.controllers;

import com.study.shortener.entities.DTO.UrlRequest;
import com.study.shortener.entities.DTO.UrlResponse;
import com.study.shortener.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest urlRequest, HttpServletRequest request) {
        UrlResponse urlResponse = urlService.shortenUrl(urlRequest, request);
        return ResponseEntity.ok(urlResponse);
    }
}
