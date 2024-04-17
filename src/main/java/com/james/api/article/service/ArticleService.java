package com.james.api.article.service;

import com.james.api.article.model.Article;
import com.james.api.article.model.ArticleDto;
import com.james.api.board.model.Board;
import com.james.api.common.command.CommandService;
import com.james.api.common.component.MessengerVo;
import com.james.api.common.query.QueryService;
import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {

    MessengerVo modify(ArticleDto dto);

    List<ArticleDto> findAllByBoardId(Long id);
    default Article dtoToEntity(ArticleDto dto){
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(User.builder().id(dto.getWriterId()).build())
                .board(Board.builder().id(dto.getBoardId()).build())
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
