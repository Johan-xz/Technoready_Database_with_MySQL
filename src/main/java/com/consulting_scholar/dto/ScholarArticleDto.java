package com.consulting_scholar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ScholarArticleDto {
    /**
     * With the methods Getter and setters I save all the connection with the variables for the table.
     */


    // Getters y Setters
    private String title;
    private List<String> authors;
    private String publicationDate;
    private String abstractText;
    private String link;
    private String keywords;
    private Integer citedBy;

    // empty constructor
    public ScholarArticleDto() {}


}