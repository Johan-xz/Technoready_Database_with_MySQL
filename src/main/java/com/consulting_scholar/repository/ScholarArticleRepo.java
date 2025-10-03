package com.consulting_scholar.repository;
import com.consulting_scholar.model.ScholarArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarArticleRepo extends JpaRepository<ScholarArticle,Long>{

    List<ScholarArticle> findByAuthorsContainingIgnoreCase(String author);
}
