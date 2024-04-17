package com.james.api.article.repository;

import com.james.api.article.model.Article;
import com.james.api.article.model.ArticleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findAllByBoardId(Long id);
}
