package com.study.shortener.repository;

import com.study.shortener.entities.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UrlRepository extends MongoRepository<Url, String> {
}
