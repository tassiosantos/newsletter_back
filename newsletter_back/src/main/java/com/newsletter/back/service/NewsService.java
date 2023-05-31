package com.newsletter.back.service;

import com.newsletter.back.model.News;
import com.newsletter.back.repository.NewsRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public Page<News> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return newsRepository.findAll(pageable);
    }

    public News findById(Integer id) {
        // return newsRepository.findById(id);
        // return newsRepository.findById(id);
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            return optionalNews.get();
        } else {
            throw new NoSuchElementException("News with id " + id + " not found.");
        }
    }
}
