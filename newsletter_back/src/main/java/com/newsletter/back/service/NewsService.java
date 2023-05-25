package com.newsletter.back.service;

import com.newsletter.back.model.News;
import com.newsletter.back.repository.NewsRepository;
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

    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }
}
