package com.newsletter.back.service;

import com.newsletter.back.model.News;
import com.newsletter.back.repository.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsService newsService;

    private Pageable pageable;
    private Page<News> newsPage;

    private Integer id;
    private News news;

    @BeforeEach
    public void setUp() {
        pageable = PageRequest.of(0, 5);
        newsPage = mock(Page.class); // Use your actual Page implementation here

        id = 1;
        news = new News(); // Populate with actual data
    }

    @Test
    public void findPaginatedTest() {
        when(newsRepository.findAll(pageable)).thenReturn(newsPage);

        Page<News> result = newsService.findPaginated(0, 5);

        assertEquals(newsPage, result);
        verify(newsRepository, times(1)).findAll(pageable);
    }

    @Test
    public void findByIdTest_whenNewsExists() {
        when(newsRepository.findById(id)).thenReturn(Optional.of(news));

        News result = newsService.findById(id);

        assertEquals(news, result);
        verify(newsRepository, times(1)).findById(id);
    }

    @Test
    public void findByIdTest_whenNewsDoesNotExist() {
        when(newsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> newsService.findById(id));
        verify(newsRepository, times(1)).findById(id);
    }
}
