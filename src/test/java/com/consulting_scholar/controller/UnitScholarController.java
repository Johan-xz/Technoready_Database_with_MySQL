package com.consulting_scholar.controller;

import com.consulting_scholar.model.ScholarArticle;
import com.consulting_scholar.service.ScholarService;
import org.springframework.ui.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitScholarController {}
@ExtendWith(MockitoExtension.class)
class ScholarControllerTest {

    @InjectMocks
    private ScholarController scholarController;

    @Mock
    private ScholarService scholarService;

    @Mock
    private Model model;

    @Test
    void testShowResults_withQuery() {
        String query = "AI";
        List<ScholarArticle> mockArticles = List.of(new ScholarArticle());
        when(scholarService.fetchAndSaveArticles(query)).thenReturn(mockArticles);

        String view = scholarController.showResults(query, model);

        verify(scholarService).fetchAndSaveArticles(query);
        verify(model).addAttribute("results", mockArticles);
        verify(model).addAttribute("query", query);
        assertEquals("results", view);
    }

    @Test
    void testShowResults_withoutQuery() {
        String query = "";
        List<ScholarArticle> mockArticles = List.of(new ScholarArticle());
        when(scholarService.getAllArticles()).thenReturn(mockArticles);

        String view = scholarController.showResults(query, model);

        verify(scholarService).getAllArticles();
        verify(model).addAttribute("results", mockArticles);
        verify(model).addAttribute("query", query);
        assertEquals("results", view);
    }
}
