package com.consulting_scholar.service;

import com.consulting_scholar.client.ScholarClient;
import com.consulting_scholar.dto.ScholarArticleDto;
import com.consulting_scholar.model.ScholarArticle;
import com.consulting_scholar.repository.ScholarArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScholarService {
    private final ScholarClient scholarClient;
    private final ScholarArticleRepo repository;

    public ScholarService(ScholarClient scholarClient, ScholarArticleRepo repository){
        this.scholarClient = scholarClient;
        this.repository = repository;
    }
    public List<ScholarArticle> fetchAndSaveArticles(String query){
        List<ScholarArticleDto> dto = scholarClient.searchArticles(query);
        List<ScholarArticle> entities = dto.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        return repository.saveAll(entities);
    }
    private ScholarArticle mapToEntity(ScholarArticleDto dto){
        ScholarArticle article = new ScholarArticle();
        article.setTitle(dto.getTitle());
        article.setAuthors(dto.getAuthors());
        article.setPublicationDate(dto.getPublicationDate());
        article.setAbstractText(dto.getAbstractText());
        article.setLink(dto.getLink());
        article.setKeywords(dto.getKeywords());
        article.setCitedBy(dto.getCitedBy());
        return article;

    }
    public List<ScholarArticle> getAllArticles() {
        return repository.findAll();
    }
}
