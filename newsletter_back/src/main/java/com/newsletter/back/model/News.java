package com.newsletter.back.model;

import javax.persistence.*;

@Entity
@Table(name = "newsletter")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private String summary;
    private String content;

    // Getters e Setters
}
