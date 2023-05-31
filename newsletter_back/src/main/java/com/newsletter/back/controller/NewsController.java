package com.newsletter.back.controller;

import com.newsletter.back.model.News;
import com.newsletter.back.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/noticia")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/getnews/{page}")
    public ResponseEntity<Page<News>> getNews(@PathVariable int page) {
        Page<News> news = newsService.findPaginated(page - 1, 5);
        return ResponseEntity.ok().body(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Integer id) {
        News news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }
}
