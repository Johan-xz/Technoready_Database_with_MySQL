package com.consulting_scholar.service;

import com.consulting_scholar.client.ScholarClient;
import com.consulting_scholar.dto.ScholarArticleDto;
import com.consulting_scholar.model.ScholarArticle;
import com.consulting_scholar.repository.ScholarArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class ScholarService {

    private final ScholarClient scholarClient;
    private final ScholarArticleRepo repository;

    public ScholarService(ScholarClient scholarArticleClient, ScholarArticleRepo repository) {
        this.scholarClient = scholarArticleClient;
        this.repository = repository;
    }

    //
    public List<ScholarArticle> searchbyauthor(String authorsname) {
        List<ScholarArticleDto> dtos = scholarClient.fetchScholarArticles("author:" + authorsname);
        List<ScholarArticle> articles = dtos.stream()
                .map(this::mapToEntity)
                .limit(3)
                .collect(Collectors.toList());
        repository.saveAll(articles);
        return articles;
    }

    // 📚 search by topics and extract frequent authors
    public List<ScholarArticle> searchbytopics(String tema) {
        List<ScholarArticleDto> dtos = scholarClient.fetchScholarArticles(tema);
        List<ScholarArticle> articles = dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        List<String> frequentsauthors = extractfrequentauthors(articles);

        List<ScholarArticle> filteredarticles = articles.stream()
                .filter(a -> frequentsauthors.stream().anyMatch(autor -> a.getAuthors().contains(autor)))
                .limit(3)
                .collect(Collectors.toList());

        repository.saveAll(filteredarticles);
        return filteredarticles;
    }

    //  Extraer los autores más frecuentes
    private List<String> extractfrequentauthors(List<ScholarArticle> articles) {
        return articles.stream()
                .flatMap(a -> a.getAuthors().stream())
                .collect(Collectors.groupingBy(author -> author, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // DTO mapping to entity
    private ScholarArticle mapToEntity(ScholarArticleDto dto) {
        ScholarArticle article = new ScholarArticle();
        article.setTitle(dto.getTitle());
        article.setAuthors(dto.getAuthors());
        article.setPublicationDate(dto.getPublicationDate());
        article.setAbstractText(dto.getAbstractText());
        article.setLink(dto.getLink());
        article.setKeywords(dto.getKeywords());
        return article;
    }

    // Obtain all the articles
    public List<ScholarArticle> getAllArticles() {
        return repository.findAll();
    }
    public List<ScholarArticle> fetchAndSaveArticles(String query) {
        List<ScholarArticleDto> dtos = scholarClient.fetchScholarArticles(query);
        List<ScholarArticle> articles = dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        repository.saveAll(articles);
        return articles;
    }
}
