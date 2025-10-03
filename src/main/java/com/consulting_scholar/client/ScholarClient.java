package com.consulting_scholar.client;

import com.consulting_scholar.dto.ScholarArticleDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScholarClient {
    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.scholar.url}")
    private String apiUrl;

    public List<ScholarArticleDto> searchArticles(String query){
        List<ScholarArticleDto> results = new ArrayList<>();

        try{
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("engine","google_scholar")
                    .queryParam("q",query)
                    .queryParam("api_key", apiKey);

            String json = restTemplate.getForObject(builder.toUriString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json).path("organic_results");

            for (JsonNode node : root){
                ScholarArticleDto dto = new ScholarArticleDto();
                dto.setTitle(node.path("title").asText());
                dto.setAuthors(node.path("authors").asText());
                dto.setPublicationDate(node.path("publication").asText());
                dto.setAbstractText(node.path("snippet").asText());
                dto.setLink(node.path("link").asText());
                dto.setKeywords("");
                dto.setCitedBy(node.path("cited_by").path("value").asInt(0));
                results.add(dto);
            }

        } catch (Exception e){
            System.err.println("Connection error with SerpApi: " + e.getMessage());
        }
        return results;
    }
}

