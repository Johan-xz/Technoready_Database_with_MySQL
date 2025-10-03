# Project Overview

## Project Purpose

This project aims to streamline academic data retrieval by integrating the Google Scholar API 
into a modular, user-friendly interface. It supports researchers, consultants, and technical 
teams in automating literature reviews, citation tracking, and metadata extraction.
## Key Functionalities
- Query academic articles using structured parameters (author, title, keywords).
- Extract metadata including citation count, publication year, and journal source.
## Project Relevance
  Academic data is often siloed or manually retrieved, leading to inefficiencies in research 
  and reporting. This tool facilitates:
- Automated literature scanning for technical proposals.
- Citation tracking for grant applications or academic audits.
- Integration into consulting deliverables requiring scholarly references.

## V1.0.0 commit 3 
There's some issues with the dependencies, so I couldn't start documentation but in **HELP.md** there is some 
tutorials for the dependency 

The flow structure is:
-     ScholarController { 
  -      ScholarService {
    -      ScholarClient {
      -     ScholarArticleDto{
      -       }
      -     ScholarArticleRepo{
        -       ScholarArticle
        -       }
      -     ScholarArticle
    -     }
  -     }
-      }



