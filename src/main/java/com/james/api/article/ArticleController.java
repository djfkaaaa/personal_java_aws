package com.james.api.article;

import com.james.api.article.model.ArticleDto;
import com.james.api.article.service.ArticleServiceImpl;
import com.james.api.board.model.Board;
import com.james.api.common.component.MessengerVo;
import com.james.api.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/articles")
@Slf4j
public class ArticleController {
    private final ArticleServiceImpl service;

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<MessengerVo> save(@RequestBody ArticleDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody ArticleDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.modify(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> findAll(){
//        log.info("입력받은 정보 : {}");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<ArticleDto>> findById(@RequestParam("id") Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleDto>>findAllByBoardId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.findAllByBoardId(id));
    }

}
