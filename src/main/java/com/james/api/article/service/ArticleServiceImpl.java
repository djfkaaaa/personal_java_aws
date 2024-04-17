package com.james.api.article.service;

import com.james.api.article.model.ArticleDto;
import com.james.api.article.repository.ArticleRepository;
import com.james.api.article.service.ArticleService;
import com.james.api.board.model.Board;
import com.james.api.common.component.MessengerVo;
import com.james.api.common.component.PageRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repo;


    @Override
    public MessengerVo save(ArticleDto dto) {
        entityToDto(repo.save(dtoToEntity(dto)));
        return new MessengerVo();
    }

    @Override
    public MessengerVo deleteById(Long id) {
        repo.deleteById(id);
        return new MessengerVo();
    }

    @Override
    public MessengerVo modify(ArticleDto dto) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public List<ArticleDto> findAllByBoardId(Long id) {
        return repo.findAllByBoardId(id).stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public List<ArticleDto> findAll() {
        return repo.findAll().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return repo.findById(id).map(i->entityToDto(i));
    }

    @Override
    public Long count() {
        return repo.count();
    }



//    @Override
//    public boolean existsById(Long id) {
//        return repo.existsById(id);
//    }
}




