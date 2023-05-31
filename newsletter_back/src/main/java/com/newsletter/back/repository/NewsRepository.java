package com.newsletter.back.repository;

import com.newsletter.back.model.News;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {

    Page<News> findAll(Pageable pageable);

    Optional<News> findById(Integer id);
}
