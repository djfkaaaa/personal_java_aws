package com.james.api.article.service;

import com.james.api.article.model.Article;
import com.james.api.article.model.ArticleDto;
import com.james.api.board.model.Board;
import com.james.api.board.repository.BoardRepository;
import com.james.api.common.command.CommandService;
import com.james.api.common.component.MessengerVo;
import com.james.api.common.query.QueryService;
import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;
import com.james.api.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {

    MessengerVo save(ArticleDto dto);

    MessengerVo modify(ArticleDto dto);

    List<ArticleDto> findAllByBoardId(Long boardId);
    default Article dtoToEntity(ArticleDto dto, BoardRepository repo){
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .board(repo.findById(dto.getBoardId()).orElse(null))
                .build();
    }

    default ArticleDto entityToDto(Article article){
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .writerId(article.getWriter().getId())
                .boardId(article.getBoard().getId())
                .build();
    }
}
