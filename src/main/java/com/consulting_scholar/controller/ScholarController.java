package com.consulting_scholar.controller;

import com.consulting_scholar.model.ScholarArticle;
import com.consulting_scholar.service.ScholarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ScholarController {
    /**
     * In this class are the methods Get
     * the first one with "/" is to redirect to the search
     * the second is for make acccess to search and
     * The last two are for redirect to results with a table
     */
    private final ScholarService scholarService;

    public ScholarController(ScholarService scholarService){
        this.scholarService = scholarService;
    }
    @GetMapping("/")
    public String redirectToSearch() {
        return "redirect:/search";
    }
    @GetMapping("/search")
    public String showSearchForm(){
        return "search";
    }

    @GetMapping("/results")
    public String showResults(@RequestParam(name = "query", required = false) String query, Model model) {
        System.out.println("Received query: " + query); // Simple log
        List<ScholarArticle> articles = (query == null || query.isBlank())
                ? scholarService.getAllArticles()
                : scholarService.fetchAndSaveArticles(query);
        model.addAttribute("results", articles);
        model.addAttribute("query", query);
        return "results";
    }
    @GetMapping("/all")
    public String showAllArticles(Model model){
        List<ScholarArticle> articles = scholarService.getAllArticles();
        model.addAttribute("results", articles);
        return "results";
    }
}
