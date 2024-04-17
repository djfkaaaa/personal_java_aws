package com.james.api.board;


import com.james.api.article.model.ArticleDto;
import com.james.api.board.model.BoardDto;
import com.james.api.board.service.BoardServiceImpl;
import com.james.api.common.component.MessengerVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/boards")
@Slf4j
public class BoardController {
    private final BoardServiceImpl service;

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<MessengerVo> save(@RequestBody BoardDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody BoardDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.modify(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> findAll(){
//        log.info("입력받은 정보 : {}");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<BoardDto>> findById(@RequestParam("id") Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }

}
