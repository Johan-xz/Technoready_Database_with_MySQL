package com.consulting_scholar.client;

import com.consulting_scholar.dto.ScholarArticleDto;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ScholarClient {
    /**
     * Here are the two values for the apikey and the apiurl
     * @value apikey is set in application.properties and given by SerpAPI platform.
     * @value api url is set in application.properties.
     */
    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.scholar.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ScholarClient(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public List<ScholarArticleDto> fetchScholarArticles(String query){
        List<ScholarArticleDto> results = new ArrayList<>();
        String url =UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("engine","google_scholar")
                .queryParam("q",query)
                .queryParam("api_key", apiKey)
                .toUriString();
        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode root = response.getBody().path("organic_results");

            if (!root.isArray()) return List.of();

            for (JsonNode node : root){
                ScholarArticleDto dto = new ScholarArticleDto();
                dto.setTitle(node.path("title").asText());
                dto.setPublicationDate(node.path("publication").asText());
                dto.setAbstractText(node.path("snippet").asText());
                dto.setLink(node.path("link").asText());
                dto.setKeywords("");
                dto.setCitedBy(node.path("cited_by").path("value").asInt(0));
                JsonNode authorsNode = node.path("authors");
                List<String> authors = new ArrayList<>();
                if(authorsNode.isArray()){
                    for(JsonNode author : authorsNode){
                        authors.add(author.asText());
                    }
                } else{
                    String raw = authorsNode.asText();
                    if(!raw.isBlank()){
                        authors.addAll(Arrays.stream(raw.split(","))
                                .map(String::trim)
                                .toList());
                    }
                }
                dto.setAuthors(authors);
            }
        } catch (Exception e){
            System.err.println("Connection error with SerpApi: " + e.getMessage());
            return List.of();
        }
        return results;
    }
}

