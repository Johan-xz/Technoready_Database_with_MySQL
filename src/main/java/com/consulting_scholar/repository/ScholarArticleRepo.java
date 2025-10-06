package com.consulting_scholar.repository;
import com.consulting_scholar.model.ScholarArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarArticleRepo extends JpaRepository<ScholarArticle,Long>{

    /**
     * @param author fdds
     * @return fasd
     */
    @Query("SELECT s FROM ScholarArticle s JOIN s.authors a WHERE a IN :authors")
    List<ScholarArticle> findByAuthorsContaining(String author);

    /**
     * @param topic fr
     * @return fra
     */
    List<ScholarArticle> findByTitleContainingIgnoreCase(String topic);

}
