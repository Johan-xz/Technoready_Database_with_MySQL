package com.consulting_scholar.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScholarArticleDto {


    // Getters y Setters
    private String title;
    private String authors;
    private String publicationDate;
    private String abstractText;
    private String link;
    private String keywords;
    private Integer citedBy;

    // empty constructor
    public ScholarArticleDto() {}


}