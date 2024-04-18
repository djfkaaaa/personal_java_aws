package com.james.api.article.service;

import com.james.api.article.model.Article;
import com.james.api.article.model.ArticleDto;
import com.james.api.article.repository.ArticleRepository;
import com.james.api.board.repository.BoardRepository;
import com.james.api.common.component.MessengerVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repo;
    private final BoardRepository boardRepo;

    @Override
    public MessengerVo save(ArticleDto dto) {
        Article ent = repo.save(dtoToEntity(dto,boardRepo));
        return MessengerVo.builder()
                .message(ent instanceof Article ? "Success":"Failure")
                .build();
//        ArticleDto t = entityToDto(repo.save(dtoToEntity(dto,boardRepo)));
//        return MessengerVo.builder()
//                .message(repo.existsByTitle(dto.getTitle())?
//                        "Success"+t.getBoardId():"failure")
//                .build();
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
    public List<ArticleDto> findAllByBoardId(Long boardId) {
        return repo.findAllByBoardId(boardId).stream().map(i -> entityToDto(i)).toList();
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




