package com.consulting_scholar.service;

import com.consulting_scholar.client.ScholarClient;
import com.consulting_scholar.model.ScholarArticle;
import com.consulting_scholar.service.ScholarService;
import com.consulting_scholar.repository.ScholarArticleRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitScholarService {
    @Test
    void testFetchAndSaveArticles_callsApiWithQuery() {
        final ScholarClient scholarClient;
        final ScholarArticleRepo repository;
        final ScholarService scholarService;
        String query = "AI";
        // Mock the API client
        when(scholarClient.search(query)).thenReturn(mockJson);

        List<ScholarArticle> result = scholarService.fetchAndSaveArticles(query);

        verify(scholarClient).search(query); // ← confirms API was called
        assertNotNull(result);
    }
}
