package com.james.api.board.service;

import com.james.api.board.model.BoardDto;
import com.james.api.board.repository.BoardRepository;
import com.james.api.board.service.BoardService;
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
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repo;


    @Override
    public MessengerVo save(BoardDto dto) {
        entityToDto(repo.save(dtoToEntity(dto)));
        return new MessengerVo();
    }

    @Override
    public MessengerVo deleteById(Long id) {
        repo.deleteById(id);
        return new MessengerVo();
    }

    @Override
    public MessengerVo modify(BoardDto dto) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public List<BoardDto> findAll() {
        return repo.findAll().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        return repo.findById(id).map(i->entityToDto(i));
    }

    @Override
    public Long count() {
        return repo.count();
    }
}
